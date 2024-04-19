package community.flock.kotlinx.rgxgen.visitors

import community.flock.kotlinx.rgxgen.config.RgxGenOption
import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.nodes.Choice
import community.flock.kotlinx.rgxgen.nodes.FinalSymbol
import community.flock.kotlinx.rgxgen.nodes.Group
import community.flock.kotlinx.rgxgen.nodes.GroupRef
import community.flock.kotlinx.rgxgen.nodes.Node
import community.flock.kotlinx.rgxgen.nodes.NotSymbol
import community.flock.kotlinx.rgxgen.nodes.Repeat
import community.flock.kotlinx.rgxgen.nodes.Sequence
import community.flock.kotlinx.rgxgen.nodes.SymbolSet
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.LONG_ONE
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.LONG_ZERO
import community.flock.kotlinx.rgxgen.util.Util.countCaseInsensitiveVariations
import community.flock.kotlinx.rgxgen.util.Util.pow

/* **************************************************************************
  Copyright 2019 Vladislavs Varslavans

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
/ * **************************************************************************/


class UniqueValuesCountingVisitor(private val aParentNode: Node?, private val aProperties: RgxGenProperties<*>?) :
    NodeVisitor {
    /**
     * Provides an estimation of number of unique values that can be generated using pattern.
     *
     * @return unique values estimation or empty, if infinite
     */
    // This value is returned to user later
    var estimation: Long? = LONG_ZERO
        private set

    constructor(properties: RgxGenProperties<*>?) : this(null, properties)

    private fun applyOrSkip(func: (Long) -> Long?) {
        estimation = estimation?.let { func(it) }
    }

    override fun visit(node: SymbolSet) {
        applyOrSkip { v: Long ->
            val size = if (RgxGenOption.CASE_INSENSITIVE.getFromProperties(aProperties)!!
            ) node.caseInsensitiveSymbolSetIndexer!!.size()
            else node.symbolSetIndexer!!.size()
            v + size.toLong()
        }
    }

    override fun visit(node: Choice) {
        for (child_node in node.nodes) {
            applyOrSkip { v: Long ->
                countSeparately(
                    node,
                    child_node,
                    aProperties
                )?.let { `val` -> v + `val` }
            }
        }
    }

    override fun visit(node: FinalSymbol) {
        if (RgxGenOption.CASE_INSENSITIVE.getFromProperties(aProperties)!!) {
            applyOrSkip { v: Long -> v + countCaseInsensitiveVariations(node.value) }
        } else {
            applyOrSkip { v: Long -> v + LONG_ONE }
        }
    }

    override fun visit(node: Repeat) {
        if (estimation != null) {
            val countingVisitor = UniqueValuesCountingVisitor(node, aProperties)
            node.node
                .visit(countingVisitor)

            if (node.max < 0 || countingVisitor.estimation == null) {
                estimation = null
            } else {
                var currentValue = estimation ?: error("")
                val nodesValue = countingVisitor.estimation ?: error("")
                for (i in node.min..node.max) {
                    currentValue = currentValue + nodesValue.pow(i)
                }
                estimation = currentValue
            }
        }
    }

    override fun visit(node: Sequence) {
        if (estimation != null) {
            for (child_node in node.nodes) {
                val count = countSeparately(node, child_node, aProperties)
                applyOrSkip { v: Long ->
                    if (count == null) {
                        return@applyOrSkip null
                    }
                    if (v == LONG_ZERO) {
                        return@applyOrSkip count
                    }

                    val subCount = count
                    if (subCount == LONG_ZERO) v else v * subCount
                }
            }
        }
    }

    override fun visit(node: NotSymbol) {
        estimation = null
    }

    override fun visit(node: GroupRef) {
        if (aParentNode != null
            && (aParentNode is Repeat || aParentNode is Choice)
        ) {
            // When repeated multiple times - it adds as much unique values as it is repeated. So we should add 1 (it will be used in Repeat for calculation).
            // E.g. (a|b)\1{2,3} - captured value of group is repeated either 2 or 3 times - it gives 2 unique values.
            estimation = estimation?.let { v: Long -> v + LONG_ONE }
        }
        //else
        // Do nothing. It does not add new unique values apart from above mentioned cases
    }

    override fun visit(node: Group) {
        node.node
            .visit(this)
    }

    companion object {
        private fun countSeparately(
            parentNode: Node,
            node: Node,
            properties: RgxGenProperties<*>?
        ): Long? {
            val countingVisitor = UniqueValuesCountingVisitor(parentNode, properties)
            node.visit(countingVisitor)
            return countingVisitor.estimation
        }
    }
}
