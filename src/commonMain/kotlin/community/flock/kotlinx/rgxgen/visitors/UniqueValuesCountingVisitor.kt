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
import community.flock.kotlinx.rgxgen.util.Util.countCaseInsensitiveVariations
import java.math.BigInteger
import java.util.*
import java.util.function.Function

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
    var estimation: Optional<BigInteger> = Optional.of(BigInteger.ZERO)
        private set

    constructor(properties: RgxGenProperties<*>?) : this(null, properties)

    private fun applyOrSkip(func: Function<BigInteger, Optional<BigInteger>>) {
        estimation = estimation.flatMap(func)
    }

    override fun visit(node: SymbolSet) {
        applyOrSkip { v: BigInteger ->
            val size = if (RgxGenOption.CASE_INSENSITIVE.getFromProperties(aProperties)!!
            ) node.caseInsensitiveSymbolSetIndexer!!.size()
            else node.symbolSetIndexer!!.size()
            Optional.of(v.add(BigInteger.valueOf(size.toLong())))
        }
    }

    override fun visit(node: Choice) {
        for (child_node in node.nodes) {
            applyOrSkip { v: BigInteger ->
                countSeparately(
                    node,
                    child_node,
                    aProperties
                ).map { `val`: BigInteger? -> v.add(`val`) }
            }
        }
    }

    override fun visit(node: FinalSymbol) {
        if (RgxGenOption.CASE_INSENSITIVE.getFromProperties(aProperties)!!) {
            applyOrSkip { v: BigInteger -> Optional.of(v.add(countCaseInsensitiveVariations(node.value))) }
        } else {
            applyOrSkip { v: BigInteger -> Optional.of(v.add(BigInteger.ONE)) }
        }
    }

    override fun visit(node: Repeat) {
        if (estimation.isPresent) {
            val countingVisitor = UniqueValuesCountingVisitor(node, aProperties)
            node.node
                .visit(countingVisitor)

            if (node.max < 0 || !countingVisitor.estimation.isPresent) {
                estimation = Optional.empty()
            } else {
                var currentValue = estimation.get()
                val nodesValue = countingVisitor.estimation.get()
                for (i in node.min..node.max) {
                    currentValue = currentValue.add(nodesValue.pow(i))
                }
                estimation = Optional.of(currentValue)
            }
        }
    }

    override fun visit(node: Sequence) {
        if (estimation.isPresent) {
            for (child_node in node.nodes) {
                val count = countSeparately(node, child_node, aProperties)
                applyOrSkip { v: BigInteger ->
                    if (!count.isPresent) {
                        return@applyOrSkip Optional.empty<BigInteger>()
                    }
                    if (v == BigInteger.ZERO) {
                        return@applyOrSkip count
                    }

                    val subCount = count.get()
                    Optional.of(if (subCount == BigInteger.ZERO) v else v.multiply(subCount))
                }
            }
        }
    }

    override fun visit(node: NotSymbol) {
        estimation = Optional.empty()
    }

    override fun visit(node: GroupRef) {
        if (aParentNode != null
            && (aParentNode is Repeat || aParentNode is Choice)
        ) {
            // When repeated multiple times - it adds as much unique values as it is repeated. So we should add 1 (it will be used in Repeat for calculation).
            // E.g. (a|b)\1{2,3} - captured value of group is repeated either 2 or 3 times - it gives 2 unique values.
            estimation = estimation.map { v: BigInteger -> v.add(BigInteger.ONE) }
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
        ): Optional<BigInteger> {
            val countingVisitor = UniqueValuesCountingVisitor(parentNode, properties)
            node.visit(countingVisitor)
            return countingVisitor.estimation
        }
    }
}
