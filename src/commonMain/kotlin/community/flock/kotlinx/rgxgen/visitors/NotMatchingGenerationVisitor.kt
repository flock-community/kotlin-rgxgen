package community.flock.kotlinx.rgxgen.visitors

import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.model.SymbolRange
import community.flock.kotlinx.rgxgen.nodes.Choice
import community.flock.kotlinx.rgxgen.nodes.FinalSymbol
import community.flock.kotlinx.rgxgen.nodes.GroupRef
import community.flock.kotlinx.rgxgen.nodes.Node
import community.flock.kotlinx.rgxgen.nodes.NotSymbol
import community.flock.kotlinx.rgxgen.nodes.Repeat
import community.flock.kotlinx.rgxgen.nodes.SymbolSet
import community.flock.kotlinx.rgxgen.parsing.NodeTreeBuilder
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.ASCII_SYMBOL_RANGE
import community.flock.kotlinx.rgxgen.parsing.dflt.DefaultTreeBuilder
import community.flock.kotlinx.rgxgen.visitors.helpers.SymbolSetIndexer
import kotlin.jvm.JvmStatic
import kotlin.random.Random

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

open class NotMatchingGenerationVisitor(
    random: Random,
    groupValues: MutableMap<Int, String>,
    properties: RgxGenProperties<*>?
) : GenerationVisitor(random, groupValues, properties) {
    override fun visit(node: SymbolSet) {
        visitSymbolSet(node, SymbolSet::symbolSetIndexer)
    }

    protected fun visitSymbolSet(node: SymbolSet, indexerFunction: (SymbolSet) -> SymbolSetIndexer?) {
        val invertedNode = node.invertedNode
        val indexer = indexerFunction(invertedNode)
        // There is only one way to generate not matching for any character - is to not generate anything
        if (indexer != null && indexer.size() != 0) {
            val idx = aRandom.nextInt(indexer.size())
            aStringBuilder.append(indexer.get(idx))
        }
    }

    override fun visit(node: Choice) {
        val groupsBuilder = StringBuilder()
        val valuePrefixBuilder = StringBuilder()
        buildGroupsStringAndValuePrefix(groupsBuilder, valuePrefixBuilder)

        // Add groups values to pattern - in case there are group refs used inside the node.getPattern()
        val pattern = (groupsBuilder.toString() + node.pattern).toRegex()
        val pos = aStringBuilder.length
        val nodes: Array<out Node> = node.nodes
        do {
            aStringBuilder.deleteRange(pos, Int.MAX_VALUE)
            val i = aRandom.nextInt(nodes.size)
            nodes[i].visit(this)
            // To match group values along with generated values - we need to prepend groups values before the generated
        } while (pattern.matches(valuePrefixBuilder.toString() + aStringBuilder.substring(pos)))
    }

    /**
     * Need to add existing group values, so that we could later use it in a matching pattern
     *
     * @param groupsBuilder
     * @param valuePrefixBuilder
     */
    private fun buildGroupsStringAndValuePrefix(groupsBuilder: StringBuilder, valuePrefixBuilder: StringBuilder) {
        var groupValuesUsed = 0
        var i = 1
        while (groupValuesUsed < aGroupValues.size) {
            val s = aGroupValues[i]
            groupsBuilder.append('(')
            // In complex expressions we might skip some groups (due to inlined choices/groups/whatever).
            // But still we should properly generate this test
            if (s != null) {
                groupsBuilder.append(Regex.escape(s))
                ++groupValuesUsed
                valuePrefixBuilder.append(s)
            }
            groupsBuilder.append(')')
            i++
        }
    }

    override fun visit(node: FinalSymbol) {
        val nodeValue = node.value
        if (nodeValue.isEmpty()) {
            aStringBuilder.append(getRandomCharacter(aRandom.nextInt(ALL_SYMBOLS.size())))
        } else {
            val builder = StringBuilder(nodeValue.length)
            do {
                builder.deleteRange(0, Int.MAX_VALUE)
                nodeValue.toList()
                    .map { getRandomCharacter(aRandom.nextInt(ALL_SYMBOLS.size())).code }
                    .forEach { c: Int -> builder.append(c.toChar()) }
            } while (equalsFinalSymbolRandomString(nodeValue, builder.toString()))
            aStringBuilder.append(builder)
        }
    }

    protected open fun equalsFinalSymbolRandomString(s1: String, s2: String): Boolean {
        return s1 == s2
    }

    override fun visit(node: Repeat) {
        // Zero length repeat will match pattern despite what node is repeated.
        if (node.min == 0) {
            super.visit(Repeat(node.pattern, node.node, 1, node.max))
        } else {
            super.visit(node)
        }
    }

    override fun visit(node: NotSymbol) {
        val builder: NodeTreeBuilder = DefaultTreeBuilder(node.pattern, properties)
        val subNode = builder.get()
        val generationVisitor = GenerationVisitor(aRandom, aGroupValues, properties)
        subNode!!.visit(generationVisitor)
        aStringBuilder.append(generationVisitor.string)
    }

    override fun visit(node: GroupRef) {
        // Note: How will this work if we will change only some of the nodes???
        val finalSymbol = FinalSymbol(aGroupValues[node.index]!!)
        visit(finalSymbol)
    }

    companion object {
        @JvmStatic
        fun builder(): GenerationVisitorBuilder {
            return GenerationVisitorBuilder(false)
        }

        private val ALL_SYMBOLS: SymbolRange = ASCII_SYMBOL_RANGE

        private fun getRandomCharacter(index: Int): Char {
            return (ALL_SYMBOLS.from + index).toChar()
        }
    }
}
