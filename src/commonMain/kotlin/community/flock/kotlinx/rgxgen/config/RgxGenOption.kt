package community.flock.kotlinx.rgxgen.config

import community.flock.kotlinx.rgxgen.model.RgxGenCharsDefinition
import community.flock.kotlinx.rgxgen.model.WhitespaceChar
import kotlin.jvm.JvmField

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
 * Enum of keys for available configuration options.
 */
class RgxGenOption<T>
/**
 * Create an option with specific key and default value
 *
 * @param key          unique identifier of the option
 * @param defaultValue default value
 */(
    /**
     * Get unique identifier of the property
     *
     * @return unique key
     */
    val key: String,
    /**
     * Get default value associated with the option
     *
     * @return default value
     */
    val default: T
) {
    /**
     * Get value from the properties object.
     *
     * @param properties object to get value from
     * @return value from properties, if present. Default otherwise.
     */
    fun getFromProperties(properties: RgxGenProperties<*>?): T? {
        return properties
            .let { props -> props?.get(key) as T }
            ?: default
    }

    /**
     * Associates `value` for this option in the properties
     *
     * @param properties properties to add to
     * @param value      a value
     */
    fun setInProperties(properties: RgxGenProperties<Any>, value: Any) {
        properties.put(key, value)
    }

    override fun toString(): String {
        return key
    }

    companion object {
        /**
         * For infinite patterns, such as `a+`, `a*` and `a{n,}`, defines limit for the repetitions.
         *
         * @defaultValue 100
         */
        @JvmField
        val INFINITE_PATTERN_REPETITION: RgxGenOption<Int> = RgxGenOption("generation.infinite.repeat", 100)

        /**
         * Flag to use case-insensitive matching.
         *
         * @defaultValue false
         */
        @JvmField
        val CASE_INSENSITIVE: RgxGenOption<Boolean> = RgxGenOption("matching.case.insensitive", false)

        /**
         * Choose which characters dot pattern could generate.
         *
         * @defaultValue null
         */
        @JvmField
        val DOT_MATCHES_ONLY: RgxGenOption<RgxGenCharsDefinition?> = RgxGenOption("dot.matches.only", null)

        /**
         * Choose which characters \s pattern could generate.
         *
         * @defaultValue SPACE, TAB
         */
        @JvmField
        val WHITESPACE_DEFINITION: RgxGenOption<List<WhitespaceChar>> =
            RgxGenOption("whitespace.matches", listOf(WhitespaceChar.SPACE, WhitespaceChar.TAB))
    }
}
