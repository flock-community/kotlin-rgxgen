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

open class GenerationVisitor(
    protected val aRandom: Random,
    protected val aGroupValues: MutableMap<Int, String>,
    protected val properties: RgxGenProperties<*>?
) : NodeVisitor {
    protected val aStringBuilder: StringBuilder = StringBuilder()

    override fun visit(node: SymbolSet) {
        val indexer = node.symbolSetIndexer
        val idx = aRandom.nextInt(indexer!!.size())
        aStringBuilder.append(indexer.get(idx))
    }

    override fun visit(node: Choice) {
        val nodes: Array<out Node> = node.nodes
        val idx = aRandom.nextInt(nodes.size)
        nodes[idx].visit(this)
    }

    override fun visit(node: FinalSymbol) {
        aStringBuilder.append(node.value)
    }

    override fun visit(node: Repeat) {
        val max =
            if (node.max == -1) RgxGenOption.INFINITE_PATTERN_REPETITION.getFromProperties(properties)!! else node.max
        val repeat = if (node.min >= max) node.min else node.min + aRandom.nextInt(max + 1 - node.min)

        for (i in 0 until repeat) {
            node.node.visit(this)
        }
    }

    override fun visit(node: Sequence) {
        for (n in node.nodes) {
            n.visit(this)
        }
    }

    override fun visit(node: NotSymbol) {
        val nmgv: GenerationVisitor = NotMatchingGenerationVisitor(aRandom, aGroupValues, properties)
        node.node.visit(nmgv)
        aStringBuilder.append(nmgv.aStringBuilder)
    }

    override fun visit(node: GroupRef) {
        aStringBuilder.append(aGroupValues.getOrDefault(node.index, ""))
    }

    override fun visit(node: Group) {
        val start = aStringBuilder.length
        node.node.visit(this)
        aGroupValues[node.index] = aStringBuilder.substring(start)
    }

    val string: String
        get() = aStringBuilder.toString()

    companion object {
        @JvmStatic
        fun builder(): GenerationVisitorBuilder {
            return GenerationVisitorBuilder(true)
        }
    }
}
