package community.flock.kotlinx.rgxgen

import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.iterators.StringIterator
import community.flock.kotlinx.rgxgen.nodes.Node
import community.flock.kotlinx.rgxgen.parsing.dflt.DefaultTreeBuilder
import community.flock.kotlinx.rgxgen.visitors.GenerationVisitor
import community.flock.kotlinx.rgxgen.visitors.NotMatchingGenerationVisitor
import community.flock.kotlinx.rgxgen.visitors.UniqueGenerationVisitor
import community.flock.kotlinx.rgxgen.visitors.UniqueValuesCountingVisitor
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

/**
 * String values generator based on regular expression pattern
 */
class RgxGen private constructor(private val properties: RgxGenProperties<*>?, pattern: String?) {
    private val node: Node?

    init {
        val defaultTreeBuilder = DefaultTreeBuilder(pattern!!, this.properties)
        node = defaultTreeBuilder.get()
    }

    val uniqueEstimation: Long?
        /**
         * Returns estimation of unique values that can be generated with the pattern.
         *
         * @return number of unique values or null, if infinite
         * @apiNote This might not be accurate! For example the pattern "(a{0,2}|b{0,2})" will estimate to 6,
         * though actual count is only 5, because right and left part of group can yield same value
         */
        get() {
            val v = UniqueValuesCountingVisitor(properties)
            node!!.visit(v)
            return v.estimation
        }

    /**
     * Creates infinite stream of randomly generated values.
     *
     * @return stream of randomly generated strings
     * @see RgxGen.generate
     */
    fun stream(): Iterator<String> {
        return object : Iterator<String> {
            override fun hasNext(): Boolean {
                return true
            }

            override fun next(): String {
                return this@RgxGen.generate()
            }
        }
    }

    /**
     * Creates iterator over unique values.
     *
     * @return Iterator over unique values
     */
    fun iterateUnique(): StringIterator? {
        val ugv = UniqueGenerationVisitor(properties)
        node!!.visit(ugv)
        return ugv.uniqueStrings
    }

    /**
     * Generate random string from the pattern.
     * Random initialized with same seed will produce same results.
     *
     * @param random random to use for the generation.
     * @return generated string.
     */
    /**
     * Generate random string from the pattern.
     *
     * @return matching random string
     */
    @JvmOverloads
    fun generate(random: Random? = Random.Default): String {
        val gv = GenerationVisitor.builder()
            .withRandom(random)
            .withProperties(properties)
            .get()
        node!!.visit(gv)
        return gv.string
    }

    /**
     * Generate random string that does not match a pattern.
     * Random initialized with same seed will produce same results.
     *
     * @param random random to use for the generation.
     * @return generated string.
     */
    /**
     * Generate random string that does not match a pattern.
     *
     * @return not matching random string.
     */
    @JvmOverloads
    fun generateNotMatching(random: Random? = Random.Default): String {
        val nmgv = NotMatchingGenerationVisitor.builder()
            .withRandom(random)
            .get()
        node!!.visit(nmgv)
        return nmgv.string
    }

    companion object {
        /**
         * Parse pattern using DefaultTreeBuilder.
         *
         * @param pattern regex pattern for values generation
         */
        @JvmStatic
        fun parse(pattern: String?): RgxGen {
            return parse(null, pattern)
        }

        /**
         * Set properties to be used in parsing and generation.
         *
         * @param rgxGenProperties configuration properties.
         * @param pattern          regex pattern for values generation
         * @see community.flock.kotlinx.rgxgen.config.RgxGenOption
         */
        @JvmStatic
        fun parse(rgxGenProperties: RgxGenProperties<*>?, pattern: String?): RgxGen {
            return RgxGen(rgxGenProperties, pattern)
        }
    }
}
