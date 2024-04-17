package community.flock.kotlinx.rgxgen.iterators /* **************************************************************************
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

interface StringIterator : Iterator<String?> {
    /**
     * Reset the iterator to the initial position.
     * After reset it will start iterating from the first value.
     *
     *
     * Can be used to restart iterator that returns `false` when `hasNext()` is called.
     */
    fun reset()

    /**
     * Return same value as last call to `next()`.
     * Behavior is not defined if method is called before `next()`
     *
     * @return Value returned by last call to `next()`.
     */
    fun current(): String?
}
