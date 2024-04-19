package community.flock.kotlinx.rgxgen.visitors

import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.nodes.FinalSymbol
import community.flock.kotlinx.rgxgen.nodes.SymbolSet
import community.flock.kotlinx.rgxgen.util.Util.randomlyChangeCase
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

class GenerationVisitorCaseInsensitive(
    random: Random,
    groupValues: MutableMap<Int, String>,
    properties: RgxGenProperties<*>?
) : GenerationVisitor(random, groupValues, properties) {
    override fun visit(node: SymbolSet) {
        val symbolSetIndexer = node.caseInsensitiveSymbolSetIndexer
        val idx = aRandom.nextInt(symbolSetIndexer!!.size())
        aStringBuilder.append(symbolSetIndexer.get(idx))
    }

    override fun visit(node: FinalSymbol) {
        val original = node.value
        aStringBuilder.append(randomlyChangeCase(aRandom, original))
    }
}
