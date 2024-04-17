package community.flock.kotlinx.rgxgen.visitors

import community.flock.kotlinx.rgxgen.config.RgxGenOption
import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import java.util.*

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

class GenerationVisitorBuilder(private val aGenerateMatching: Boolean) {
    private var aRandom: Random? = null
    private var aGroupsValues: MutableMap<Int, String>? = null
    private var aProperties: RgxGenProperties<*>? = null


    fun withRandom(random: Random?): GenerationVisitorBuilder {
        aRandom = random
        return this
    }

    fun get(): GenerationVisitor {
        if (aRandom == null) {
            aRandom = Random()
        }

        if (aGroupsValues == null) {
            aGroupsValues = HashMap()
        }

        return if (aGenerateMatching) {
            if (RgxGenOption.CASE_INSENSITIVE.getFromProperties(aProperties)!!) {
                GenerationVisitorCaseInsensitive(aRandom!!, aGroupsValues!!, aProperties)
            } else {
                GenerationVisitor(aRandom!!, aGroupsValues!!, aProperties)
            }
        } else {
            if (RgxGenOption.CASE_INSENSITIVE.getFromProperties(aProperties)!!) {
                NotMatchingCaseInsensitiveGenerationVisitor(aRandom!!, aGroupsValues!!, aProperties)
            } else {
                NotMatchingGenerationVisitor(aRandom!!, aGroupsValues!!, aProperties)
            }
        }
    }

    fun withProperties(properties: RgxGenProperties<*>?): GenerationVisitorBuilder {
        aProperties = properties
        return this
    }
}
