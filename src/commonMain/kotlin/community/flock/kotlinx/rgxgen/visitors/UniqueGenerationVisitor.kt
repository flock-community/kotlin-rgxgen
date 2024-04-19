package community.flock.kotlinx.rgxgen.visitors

import community.flock.kotlinx.rgxgen.config.RgxGenOption
import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.iterators.ReferenceIterator
import community.flock.kotlinx.rgxgen.iterators.StringIterator
import community.flock.kotlinx.rgxgen.iterators.suppliers.ArrayIteratorSupplier
import community.flock.kotlinx.rgxgen.iterators.suppliers.ChoiceIteratorSupplier
import community.flock.kotlinx.rgxgen.iterators.suppliers.GroupIteratorSupplier
import community.flock.kotlinx.rgxgen.iterators.suppliers.IncrementalLengthIteratorSupplier
import community.flock.kotlinx.rgxgen.iterators.suppliers.IndexIteratorSupplier
import community.flock.kotlinx.rgxgen.iterators.suppliers.NegativeIteratorSupplier
import community.flock.kotlinx.rgxgen.iterators.suppliers.PermutationsIteratorSupplier
import community.flock.kotlinx.rgxgen.iterators.suppliers.ReferenceIteratorSupplier
import community.flock.kotlinx.rgxgen.iterators.suppliers.SingleCaseInsensitiveValueIteratorSupplier
import community.flock.kotlinx.rgxgen.iterators.suppliers.SingleValueIteratorSupplier
import community.flock.kotlinx.rgxgen.iterators.suppliers.Supplier
import community.flock.kotlinx.rgxgen.nodes.Choice
import community.flock.kotlinx.rgxgen.nodes.FinalSymbol
import community.flock.kotlinx.rgxgen.nodes.Group
import community.flock.kotlinx.rgxgen.nodes.GroupRef
import community.flock.kotlinx.rgxgen.nodes.NotSymbol
import community.flock.kotlinx.rgxgen.nodes.Repeat
import community.flock.kotlinx.rgxgen.nodes.Sequence
import community.flock.kotlinx.rgxgen.nodes.SymbolSet
import community.flock.kotlinx.rgxgen.parsing.dflt.ConstantsProvider.makeAsciiCharacterArray

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

class UniqueGenerationVisitor(
    private val aReferenceIteratorMap: MutableMap<Int, MutableList<ReferenceIterator>>,
    private val aGroupIterators: MutableMap<Int, StringIterator>,
    private val aProperties: RgxGenProperties<*>?
) : NodeVisitor {
    private val aIterators: MutableList<Supplier<StringIterator>> = ArrayList()

    constructor(properties: RgxGenProperties<*>?) : this(
        HashMap<Int, MutableList<ReferenceIterator>>(),
        HashMap<Int, StringIterator>(),
        properties
    )

    override fun visit(node: SymbolSet) {
        if (RgxGenOption.CASE_INSENSITIVE.getFromProperties(aProperties)!!) {
            aIterators.add(IndexIteratorSupplier(node.caseInsensitiveSymbolSetIndexer!!))
        } else {
            aIterators.add(IndexIteratorSupplier(node.symbolSetIndexer!!))
        }
    }

    override fun visit(node: Choice) {
        val nodeIterators: MutableList<MutableList<Supplier<StringIterator>>> = ArrayList(node.nodes.size)
        for (n in node.nodes) {
            val v = UniqueGenerationVisitor(aReferenceIteratorMap, aGroupIterators, aProperties)
            n.visit(v)
            nodeIterators.add(v.aIterators)
        }

        aIterators.add(ChoiceIteratorSupplier(nodeIterators))
    }

    override fun visit(node: FinalSymbol) {
        if (RgxGenOption.CASE_INSENSITIVE.getFromProperties(aProperties)!!) {
            aIterators.add(SingleCaseInsensitiveValueIteratorSupplier(node.value))
        } else {
            aIterators.add(SingleValueIteratorSupplier(node.value))
        }
    }

    override fun visit(node: Repeat) {
        // Getting all possible sub node contents
        val v = UniqueGenerationVisitor(aReferenceIteratorMap, aGroupIterators, aProperties)
        node.node
            .visit(v)
        aIterators.add(
            IncrementalLengthIteratorSupplier(
                PermutationsIteratorSupplier(v.aIterators),
                node.min,
                node.max
            )
        )
    }

    override fun visit(node: Sequence) {
        val v = UniqueGenerationVisitor(aReferenceIteratorMap, aGroupIterators, aProperties)
        for (n in node.nodes) {
            n.visit(v)
        }
        aIterators.add(PermutationsIteratorSupplier(v.aIterators))
    }

    override fun visit(node: NotSymbol) {
        aIterators.add(
            NegativeIteratorSupplier(
                node.pattern,
                IncrementalLengthIteratorSupplier(ArrayIteratorSupplier(makeAsciiCharacterArray()), 0, -1)
            )
        )
    }

    override fun visit(node: GroupRef) {
        aIterators.add(ReferenceIteratorSupplier(aReferenceIteratorMap, aGroupIterators, node.index))
    }

    override fun visit(node: Group) {
        val v = UniqueGenerationVisitor(aReferenceIteratorMap, aGroupIterators, aProperties)
        node.node
            .visit(v)

        aIterators.add(
            GroupIteratorSupplier(
                PermutationsIteratorSupplier(v.aIterators),
                aReferenceIteratorMap,
                aGroupIterators,
                node.index
            )
        )
    }

    val uniqueStrings: StringIterator
        get() = aIterators[0].get()
}
