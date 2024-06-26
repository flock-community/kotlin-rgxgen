package community.flock.kotlinx.rgxgen.model

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

enum class WhitespaceChar(private val c: Char) {
    SPACE(' '),
    TAB('\t'),
    CARRIAGE_RETUR('\r'),
    LINE_FEED('\n'),
    VERTICAL_TAB('\u000B'),
    FORM_FEED('\u000C');

    fun get(): Char {
        return c
    }
}
