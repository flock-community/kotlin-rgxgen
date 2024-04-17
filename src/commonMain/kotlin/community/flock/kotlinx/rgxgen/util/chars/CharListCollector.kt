package community.flock.kotlinx.rgxgen.util.chars

import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector

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

class CharListCollector : Collector<Char, CharList, CharList> {
    override fun supplier(): Supplier<CharList> {
        return Supplier<CharList> { CharList.Companion.empty() }
    }

    override fun accumulator(): BiConsumer<CharList, Char> {
        return BiConsumer { obj: CharList, c: Char -> obj.add(c.code) }
    }

    override fun combiner(): BinaryOperator<CharList> {
        return BinaryOperator { l: CharList, r: CharList ->
            l.addAll(r)
            l
        }
    }

    override fun finisher(): Function<CharList, CharList> {
        return Function.identity()
    }

    override fun characteristics(): Set<Collector.Characteristics> {
        return setOf(Collector.Characteristics.IDENTITY_FINISH)
    }
}
