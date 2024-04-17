package community.flock.kotlinx.rgxgen.iterators.suppliers

import community.flock.kotlinx.rgxgen.iterators.SingleValueIterator
import community.flock.kotlinx.rgxgen.iterators.StringIterator
import java.util.function.Supplier

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

open class SingleValueIteratorSupplier(protected val aValue: String?) : Supplier<StringIterator> {
    override fun get(): StringIterator {
        return SingleValueIterator(aValue!!)
    }
}
