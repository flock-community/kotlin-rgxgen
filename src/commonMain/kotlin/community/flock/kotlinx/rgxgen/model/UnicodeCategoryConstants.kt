package community.flock.kotlinx.rgxgen.model

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

object UnicodeCategoryConstants {
    val BASIC_LATIN_ASCII_PUNCTUATION_AND_SYMBOLS_TO_ASCII_PUNCTUATION_AND_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range(' ', '') // 0x20 - 0x7f
    val BASIC_LATIN_ASCII_PUNCTUATION_AND_SYMBOLS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('!', '#') // 0x21 - 0x23
    val BASIC_LATIN_ASCII_PUNCTUATION_AND_SYMBOLS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('%', '\'') // 0x25 - 0x27
    val BASIC_LATIN_ASCII_PUNCTUATION_AND_SYMBOLS_SUBSET_3: SymbolRange =
        SymbolRange.Companion.range('%', '*') // 0x25 - 0x2a
    val BASIC_LATIN_ASCII_PUNCTUATION: SymbolRange = SymbolRange.Companion.range(',', '/') // 0x2c - 0x2f
    val BASIC_LATIN_ASCII_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('.', '/') // 0x2e - 0x2f
    val BASIC_LATIN_ASCII_DIGITS: SymbolRange = SymbolRange.Companion.range('0', '9') // 0x30 - 0x39
    val BASIC_LATIN_ASCII_PUNCTUATION_1: SymbolRange = SymbolRange.Companion.range(':', ';') // 0x3a - 0x3b
    val BASIC_LATIN_ASCII_MATHEMATICAL_OPERATORS: SymbolRange = SymbolRange.Companion.range('<', '>') // 0x3c - 0x3e
    val BASIC_LATIN_ASCII_PUNCTUATION_2: SymbolRange = SymbolRange.Companion.range('?', '@') // 0x3f - 0x40
    val BASIC_LATIN_UPPERCASE_LATIN_ALPHABET: SymbolRange = SymbolRange.Companion.range('A', 'Z') // 0x41 - 0x5a
    val BASIC_LATIN_ASCII_PUNCTUATION_AND_SYMBOLS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('[', ']') // 0x5b - 0x5d
    @JvmField
    val BASIC_LATIN_LOWERCASE_LATIN_ALPHABET: SymbolRange = SymbolRange.Companion.range('a', 'z') // 0x61 - 0x7a
    val BASIC_LATIN_TO_LATIN_1_SUPPLEMENT: SymbolRange = SymbolRange.Companion.range('', '') // 0x7f - 0x9f
    val LATIN_1_SUPPLEMENT_LATIN_1_PUNCTUATION_AND_SYMBOLS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('¢', '¥') // 0xa2 - 0xa5
    val LATIN_1_SUPPLEMENT_LATIN_1_PUNCTUATION_AND_SYMBOLS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('¢', '¦') // 0xa2 - 0xa6
    val LATIN_1_SUPPLEMENT_LATIN_1_PUNCTUATION_AND_SYMBOLS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('¨', '©') // 0xa8 - 0xa9
    val LATIN_1_SUPPLEMENT_LATIN_1_PUNCTUATION_AND_SYMBOLS_SUBSET_3: SymbolRange =
        SymbolRange.Companion.range('®', '±') // 0xae - 0xb1
    val LATIN_1_SUPPLEMENT_LATIN_1_PUNCTUATION_AND_SYMBOLS_SUBSET_4: SymbolRange =
        SymbolRange.Companion.range('²', '³') // 0xb2 - 0xb3
    val LATIN_1_SUPPLEMENT_LATIN_1_PUNCTUATION_AND_SYMBOLS_SUBSET_5: SymbolRange =
        SymbolRange.Companion.range('¶', '·') // 0xb6 - 0xb7
    val LATIN_1_SUPPLEMENT_VULGAR_FRACTIONS: SymbolRange = SymbolRange.Companion.range('¼', '¾') // 0xbc - 0xbe
    val LATIN_1_SUPPLEMENT_LETTERS: SymbolRange = SymbolRange.Companion.range('À', 'Ö') // 0xc0 - 0xd6
    val LATIN_1_SUPPLEMENT_UPPERCASE_LETTERS: SymbolRange = SymbolRange.Companion.range('Ø', 'Þ') // 0xd8 - 0xde
    val LATIN_1_SUPPLEMENT_UPPERCASE_LETTERS_TO_LOWERCASE_LETTERS: SymbolRange =
        SymbolRange.Companion.range('Ø', 'ö') // 0xd8 - 0xf6
    val LATIN_1_SUPPLEMENT_LOWERCASE_LETTERS: SymbolRange = SymbolRange.Companion.range('ß', 'ö') // 0xdf - 0xf6
    val LATIN_1_SUPPLEMENT_TO_SPACING_MODIFIER_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ø', 'ˁ') // 0xf8 - 0x2c1
    val LATIN_1_SUPPLEMENT_LETTERS_1: SymbolRange = SymbolRange.Companion.range('ø', 'ÿ') // 0xf8 - 0xff
    val LATIN_EXTENDED_A_EUROPEAN_LATIN_SUBSET: SymbolRange = SymbolRange.Companion.range('ķ', 'ĸ') // 0x137 - 0x138
    val LATIN_EXTENDED_A_EUROPEAN_LATIN_TO_EUROPEAN_LATIN: SymbolRange =
        SymbolRange.Companion.range('ň', 'ŉ') // 0x148 - 0x149
    val LATIN_EXTENDED_A_EUROPEAN_LATIN_SUBSET_1: SymbolRange = SymbolRange.Companion.range('Ÿ', 'Ź') // 0x178 - 0x179
    val LATIN_EXTENDED_A_EUROPEAN_LATIN_TO_EUROPEAN_LATIN_1: SymbolRange =
        SymbolRange.Companion.range('ž', 'ƀ') // 0x17e - 0x180
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET: SymbolRange =
        SymbolRange.Companion.range('Ɓ', 'Ƃ') // 0x181 - 0x182
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('Ɔ', 'Ƈ') // 0x186 - 0x187
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('Ɖ', 'Ƌ') // 0x189 - 0x18b
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_3: SymbolRange =
        SymbolRange.Companion.range('ƌ', 'ƍ') // 0x18c - 0x18d
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_4: SymbolRange =
        SymbolRange.Companion.range('Ǝ', 'Ƒ') // 0x18e - 0x191
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_5: SymbolRange =
        SymbolRange.Companion.range('Ɠ', 'Ɣ') // 0x193 - 0x194
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_6: SymbolRange =
        SymbolRange.Companion.range('Ɩ', 'Ƙ') // 0x196 - 0x198
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_7: SymbolRange =
        SymbolRange.Companion.range('ƙ', 'ƛ') // 0x199 - 0x19b
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_8: SymbolRange =
        SymbolRange.Companion.range('Ɯ', 'Ɲ') // 0x19c - 0x19d
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_9: SymbolRange =
        SymbolRange.Companion.range('Ɵ', 'Ơ') // 0x19f - 0x1a0
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_10: SymbolRange =
        SymbolRange.Companion.range('Ʀ', 'Ƨ') // 0x1a6 - 0x1a7
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_11: SymbolRange =
        SymbolRange.Companion.range('ƪ', 'ƫ') // 0x1aa - 0x1ab
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_12: SymbolRange =
        SymbolRange.Companion.range('Ʈ', 'Ư') // 0x1ae - 0x1af
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_13: SymbolRange =
        SymbolRange.Companion.range('Ʊ', 'Ƴ') // 0x1b1 - 0x1b3
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_14: SymbolRange =
        SymbolRange.Companion.range('Ʒ', 'Ƹ') // 0x1b7 - 0x1b8
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_15: SymbolRange =
        SymbolRange.Companion.range('ƹ', 'ƺ') // 0x1b9 - 0x1ba
    val LATIN_EXTENDED_B_NON_EUROPEAN_AND_HISTORIC_LATIN_SUBSET_16: SymbolRange =
        SymbolRange.Companion.range('ƽ', 'ƿ') // 0x1bd - 0x1bf
    val LATIN_EXTENDED_B_AFRICAN_LETTERS_FOR_CLICKS: SymbolRange =
        SymbolRange.Companion.range('ǀ', 'ǃ') // 0x1c0 - 0x1c3
    val LATIN_EXTENDED_B_PINYIN_DIACRITIC_VOWEL_COMBINATIONS_TO_PINYIN_DIACRITIC_VOWEL_COMBINATIONS: SymbolRange =
        SymbolRange.Companion.range('ǜ', 'ǝ') // 0x1dc - 0x1dd
    val LATIN_EXTENDED_B_PHONETIC_AND_HISTORIC_LETTERS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ǯ', 'ǰ') // 0x1ef - 0x1f0
    val LATIN_EXTENDED_B_PHONETIC_AND_HISTORIC_LETTERS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('Ƕ', 'Ǹ') // 0x1f6 - 0x1f8
    val LATIN_EXTENDED_B_ADDITIONS_FOR_LIVONIAN_TO_ADDITIONS_FOR_AFRICANIST_LINGUISTICS: SymbolRange =
        SymbolRange.Companion.range('ȳ', 'ȹ') // 0x233 - 0x239
    val LATIN_EXTENDED_B_ADDITIONS_FOR_SENCOTEN_SUBSET: SymbolRange =
        SymbolRange.Companion.range('Ⱥ', 'Ȼ') // 0x23a - 0x23b
    val LATIN_EXTENDED_B_ADDITIONS_FOR_SENCOTEN_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('Ƚ', 'Ⱦ') // 0x23d - 0x23e
    val LATIN_EXTENDED_B_ADDITIONS_FOR_AFRICANIST_LINGUISTICS_1: SymbolRange =
        SymbolRange.Companion.range('ȿ', 'ɀ') // 0x23f - 0x240
    val LATIN_EXTENDED_B_MISCELLANEOUS_ADDITIONS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('Ƀ', 'Ɇ') // 0x243 - 0x246
    val LATIN_EXTENDED_B_TO_IPA_EXTENSIONS: SymbolRange = SymbolRange.Companion.range('ɏ', 'ʓ') // 0x24f - 0x293
    val IPA_EXTENSIONS_IPA_EXTENSIONS_TO_ADDITIONS_FOR_SINOLOGY: SymbolRange =
        SymbolRange.Companion.range('ɐ', 'ʯ') // 0x250 - 0x2af
    val IPA_EXTENSIONS_IPA_EXTENSIONS_TO_ADDITIONS_FOR_SINOLOGY_1: SymbolRange =
        SymbolRange.Companion.range('ʕ', 'ʯ') // 0x295 - 0x2af
    val SPACING_MODIFIER_LETTERS_LATIN_SUPERSCRIPT_MODIFIER_LETTERS_TO_MISCELLANEOUS_PHONETIC_MODIFIERS: SymbolRange =
        SymbolRange.Companion.range('ʰ', 'ˁ') // 0x2b0 - 0x2c1
    val SPACING_MODIFIER_LETTERS_LATIN_SUPERSCRIPT_MODIFIER_LETTERS_TO_UPA_MODIFIERS: SymbolRange =
        SymbolRange.Companion.range('ʰ', '˿') // 0x2b0 - 0x2ff
    val SPACING_MODIFIER_LETTERS_MISCELLANEOUS_PHONETIC_MODIFIERS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('˂', '˅') // 0x2c2 - 0x2c5
    val SPACING_MODIFIER_LETTERS_MISCELLANEOUS_PHONETIC_MODIFIERS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ˆ', 'ˑ') // 0x2c6 - 0x2d1
    val SPACING_MODIFIER_LETTERS_MISCELLANEOUS_PHONETIC_MODIFIERS_TO_ADDITIONS_BASED_ON_1989_IPA: SymbolRange =
        SymbolRange.Companion.range('˒', '˟') // 0x2d2 - 0x2df
    val SPACING_MODIFIER_LETTERS_ADDITIONS_BASED_ON_1989_IPA_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ˠ', 'ˤ') // 0x2e0 - 0x2e4
    val SPACING_MODIFIER_LETTERS_TONE_LETTERS_TO_EXTENDED_BOPOMOFO_TONE_MARKS: SymbolRange =
        SymbolRange.Companion.range('˥', '˫') // 0x2e5 - 0x2eb
    val SPACING_MODIFIER_LETTERS_UPA_MODIFIERS: SymbolRange = SymbolRange.Companion.range('˯', '˿') // 0x2ef - 0x2ff
    val COMBINING_DIACRITICAL_MARKS_ORDINARY_DIACRITICS_TO_MEDIEVAL_SUPERSCRIPT_LETTER_DIACRITICS: SymbolRange =
        SymbolRange.Companion.range('̀', 'ͯ') // 0x300 - 0x36f
    val GREEK_AND_COPTIC_ARCHAIC_LETTERS_TO_ARCHAIC_LETTERS: SymbolRange =
        SymbolRange.Companion.range('Ͱ', 'ʹ') // 0x370 - 0x374
    val GREEK_AND_COPTIC_ARCHAIC_LETTERS_SUBSET: SymbolRange = SymbolRange.Companion.range('Ͷ', 'ͷ') // 0x376 - 0x377
    val GREEK_AND_COPTIC_IOTA_SUBSCRIPT_TO_LOWERCASE_OF_EDITORIAL_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('ͺ', 'ͽ') // 0x37a - 0x37d
    val GREEK_AND_COPTIC_LOWERCASE_OF_EDITORIAL_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('ͻ', 'ͽ') // 0x37b - 0x37d
    val GREEK_AND_COPTIC_SPACING_ACCENT_MARKS: SymbolRange = SymbolRange.Companion.range('΄', '΅') // 0x384 - 0x385
    val GREEK_AND_COPTIC_LETTERS_SUBSET: SymbolRange = SymbolRange.Companion.range('Έ', 'Ί') // 0x388 - 0x38a
    val GREEK_AND_COPTIC_LETTERS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('Ύ', 'Ώ') // 0x38e - 0x38f
    val GREEK_AND_COPTIC_LETTERS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('Ύ', 'Ρ') // 0x38e - 0x3a1
    val GREEK_AND_COPTIC_LETTERS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('Α', 'Ρ') // 0x391 - 0x3a1
    val GREEK_AND_COPTIC_LETTERS_SUBSET_4: SymbolRange = SymbolRange.Companion.range('Σ', 'Ϋ') // 0x3a3 - 0x3ab
    val GREEK_AND_COPTIC_LETTERS_TO_VARIANT_LETTERFORMS_AND_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('Σ', 'ϵ') // 0x3a3 - 0x3f5
    val GREEK_AND_COPTIC_LETTERS_SUBSET_5: SymbolRange = SymbolRange.Companion.range('ά', 'ώ') // 0x3ac - 0x3ce
    val GREEK_AND_COPTIC_VARIANT_LETTERFORMS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ϐ', 'ϑ') // 0x3d0 - 0x3d1
    val GREEK_AND_COPTIC_VARIANT_LETTERFORMS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ϒ', 'ϔ') // 0x3d2 - 0x3d4
    val GREEK_AND_COPTIC_VARIANT_LETTERFORMS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('ϕ', 'ϗ') // 0x3d5 - 0x3d7
    val GREEK_AND_COPTIC_COPTIC_LETTERS_DERIVED_FROM_DEMOTIC_TO_VARIANT_LETTERFORMS: SymbolRange =
        SymbolRange.Companion.range('ϯ', 'ϳ') // 0x3ef - 0x3f3
    val GREEK_AND_COPTIC_TO_CYRILLIC: SymbolRange = SymbolRange.Companion.range('Ϸ', 'ҁ') // 0x3f7 - 0x481
    val GREEK_AND_COPTIC_VARIANT_LETTERFORM_TO_VARIANT_LETTERFORM: SymbolRange =
        SymbolRange.Companion.range('Ϲ', 'Ϻ') // 0x3f9 - 0x3fa
    val GREEK_AND_COPTIC_ARCHAIC_LETTERS_TO_ARCHAIC_LETTERS_1: SymbolRange =
        SymbolRange.Companion.range('ϻ', 'ϼ') // 0x3fb - 0x3fc
    val GREEK_AND_COPTIC_TO_CYRILLIC_1: SymbolRange = SymbolRange.Companion.range('Ͻ', 'Я') // 0x3fd - 0x42f
    val CYRILLIC_CYRILLIC_EXTENSIONS_TO_ADDITIONS_FOR_NIVKH: SymbolRange =
        SymbolRange.Companion.range('Ѐ', 'ӿ') // 0x400 - 0x4ff
    val CYRILLIC_BASIC_RUSSIAN_ALPHABET_TO_CYRILLIC_EXTENSIONS: SymbolRange =
        SymbolRange.Companion.range('а', 'џ') // 0x430 - 0x45f
    val CYRILLIC_HISTORIC_MISCELLANEOUS_SUBSET: SymbolRange = SymbolRange.Companion.range('҃', '҇') // 0x483 - 0x487
    val CYRILLIC_HISTORIC_MISCELLANEOUS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('҃', '҉') // 0x483 - 0x489
    val CYRILLIC_HISTORIC_MISCELLANEOUS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('҈', '҉') // 0x488 - 0x489
    val CYRILLIC_TO_CYRILLIC_SUPPLEMENT: SymbolRange = SymbolRange.Companion.range('Ҋ', 'ԯ') // 0x48a - 0x52f
    val CYRILLIC_EXTENDED_CYRILLIC_SUBSET: SymbolRange = SymbolRange.Companion.range('Ӏ', 'Ӂ') // 0x4c0 - 0x4c1
    val CYRILLIC_EXTENDED_CYRILLIC_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ӎ', 'ӏ') // 0x4ce - 0x4cf
    val CYRILLIC_SUPPLEMENT_KOMI_LETTERS_TO_KHANTY_LETTERS: SymbolRange =
        SymbolRange.Companion.range('Ԁ', 'ԯ') // 0x500 - 0x52f
    val CYRILLIC_SUPPLEMENT_ARMENIAN_TO_RELIGIOUS_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('԰', '֏') // 0x530 - 0x58f
    val CYRILLIC_SUPPLEMENT_UPPERCASE_LETTERS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('Ա', 'Ֆ') // 0x531 - 0x556
    val CYRILLIC_SUPPLEMENT_MODIFIER_LETTERS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('՚', '՟') // 0x55a - 0x55f
    val CYRILLIC_SUPPLEMENT_LOWERCASE_LETTERS: SymbolRange = SymbolRange.Companion.range('ՠ', 'ֈ') // 0x560 - 0x588
    val CYRILLIC_SUPPLEMENT_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('։', '֊') // 0x589 - 0x58a
    val CYRILLIC_SUPPLEMENT_RELIGIOUS_SYMBOLS: SymbolRange = SymbolRange.Companion.range('֍', '֎') // 0x58d - 0x58e
    val CYRILLIC_SUPPLEMENT_RELIGIOUS_SYMBOLS_TO_RELIGIOUS_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('֍', '֏') // 0x58d - 0x58f
    val CYRILLIC_SUPPLEMENT_HEBREW_TO_ADDITIONAL_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('֐', '׿') // 0x590 - 0x5ff
    val CYRILLIC_SUPPLEMENT_CANTILLATION_MARKS_TO_POINTS_AND_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('֑', 'ֽ') // 0x591 - 0x5bd
    val CYRILLIC_SUPPLEMENT_POINTS_AND_PUNCTUATION_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ׁ', 'ׂ') // 0x5c1 - 0x5c2
    val CYRILLIC_SUPPLEMENT_PUNCTA_EXTRAORDINARIA: SymbolRange = SymbolRange.Companion.range('ׄ', 'ׅ') // 0x5c4 - 0x5c5
    val CYRILLIC_SUPPLEMENT_BASED_ON_ISO_8859_8_SUBSET: SymbolRange =
        SymbolRange.Companion.range('א', 'ת') // 0x5d0 - 0x5ea
    val CYRILLIC_SUPPLEMENT_SIGN_TO_YIDDISH_DIGRAPHS: SymbolRange =
        SymbolRange.Companion.range('ׯ', 'ײ') // 0x5ef - 0x5f2
    val CYRILLIC_SUPPLEMENT_ADDITIONAL_PUNCTUATION_SUBSET: SymbolRange =
        SymbolRange.Companion.range('׳', '״') // 0x5f3 - 0x5f4
    val ARABIC_SUBTENDING_MARKS_TO_SUBTENDING_MARKS: SymbolRange =
        SymbolRange.Companion.range('؀', '؅') // 0x600 - 0x605
    val ARABIC_SUBTENDING_MARKS_TO_SIGNS_FOR_SINDHI: SymbolRange =
        SymbolRange.Companion.range('؀', 'ۿ') // 0x600 - 0x6ff
    val ARABIC_RADIX_SYMBOLS_TO_RADIX_SYMBOLS: SymbolRange = SymbolRange.Companion.range('؆', '؈') // 0x606 - 0x608
    val ARABIC_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('؉', '؊') // 0x609 - 0x60a
    val ARABIC_PUNCTUATION_1: SymbolRange = SymbolRange.Companion.range('،', '؍') // 0x60c - 0x60d
    val ARABIC_POETIC_MARKS: SymbolRange = SymbolRange.Companion.range('؎', '؏') // 0x60e - 0x60f
    val ARABIC_HONORIFICS_TO_QURANIC_ANNOTATION_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ؐ', 'ؚ') // 0x610 - 0x61a
    val ARABIC_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('؞', '؟') // 0x61e - 0x61f
    val ARABIC_ADDITION_FOR_KASHMIRI_TO_ADDITIONS_FOR_EARLY_PERSIAN_AND_AZERBAIJANI: SymbolRange =
        SymbolRange.Companion.range('ؠ', 'ؿ') // 0x620 - 0x63f
    val ARABIC_ADDITION_FOR_KASHMIRI_TO_BASED_ON_ISO_8859_6: SymbolRange =
        SymbolRange.Companion.range('ؠ', 'ي') // 0x620 - 0x64a
    val ARABIC_BASED_ON_ISO_8859_6_SUBSET: SymbolRange = SymbolRange.Companion.range('ف', 'ي') // 0x641 - 0x64a
    val ARABIC_TASHKIL_FROM_ISO_8859_6_TO_OTHER_COMBINING_MARKS: SymbolRange =
        SymbolRange.Companion.range('ً', 'ٟ') // 0x64b - 0x65f
    val ARABIC_ARABIC_INDIC_DIGITS: SymbolRange = SymbolRange.Companion.range('٠', '٩') // 0x660 - 0x669
    val ARABIC_PUNCTUATION_4: SymbolRange = SymbolRange.Companion.range('٪', '٭') // 0x66a - 0x66d
    val ARABIC_ARCHAIC_LETTERS: SymbolRange = SymbolRange.Companion.range('ٮ', 'ٯ') // 0x66e - 0x66f
    val ARABIC_EXTENDED_ARABIC_LETTERS_TO_EXTENDED_ARABIC_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ٱ', 'ۓ') // 0x671 - 0x6d3
    val ARABIC_QURANIC_ANNOTATION_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ۖ', 'ۜ') // 0x6d6 - 0x6dc
    val ARABIC_QURANIC_ANNOTATION_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('۟', 'ۤ') // 0x6df - 0x6e4
    val ARABIC_QURANIC_ANNOTATION_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ۥ', 'ۦ') // 0x6e5 - 0x6e6
    val ARABIC_QURANIC_ANNOTATION_SIGNS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ۧ', 'ۨ') // 0x6e7 - 0x6e8
    val ARABIC_QURANIC_ANNOTATION_SIGNS_SUBSET_4: SymbolRange = SymbolRange.Companion.range('۪', 'ۭ') // 0x6ea - 0x6ed
    val ARABIC_EXTENDED_ARABIC_LETTERS_FOR_PARKARI: SymbolRange = SymbolRange.Companion.range('ۮ', 'ۯ') // 0x6ee - 0x6ef
    val ARABIC_EASTERN_ARABIC_INDIC_DIGITS: SymbolRange = SymbolRange.Companion.range('۰', '۹') // 0x6f0 - 0x6f9
    val ARABIC_EXTENDED_ARABIC_LETTERS_2: SymbolRange = SymbolRange.Companion.range('ۺ', 'ۼ') // 0x6fa - 0x6fc
    val ARABIC_SIGNS_FOR_SINDHI: SymbolRange = SymbolRange.Companion.range('۽', '۾') // 0x6fd - 0x6fe
    val SYRIAC_SYRIAC_PUNCTUATION_AND_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('܀', '܍') // 0x700 - 0x70d
    val SYRIAC_SYRIAC_PUNCTUATION_AND_SIGNS_TO_SOGDIAN_LETTERS: SymbolRange =
        SymbolRange.Companion.range('܀', 'ݏ') // 0x700 - 0x74f
    val SYRIAC_SYRIAC_LETTERS_TO_PERSIAN_LETTERS: SymbolRange = SymbolRange.Companion.range('ܒ', 'ܯ') // 0x712 - 0x72f
    val SYRIAC_SYRIAC_POINTS_VOWELS_TO_SYRIAC_MARKS: SymbolRange =
        SymbolRange.Companion.range('ܰ', '݊') // 0x730 - 0x74a
    val SYRIAC_TO_THAANA: SymbolRange = SymbolRange.Companion.range('ݍ', 'ޥ') // 0x74d - 0x7a5
    val THAANA_BASIC_CONSONANTS_TO_CONSONANT_FOR_ADDU_DIALECT: SymbolRange =
        SymbolRange.Companion.range('ހ', '޿') // 0x780 - 0x7bf
    val THAANA_VOWELS: SymbolRange = SymbolRange.Companion.range('ަ', 'ް') // 0x7a6 - 0x7b0
    val NKO_DIGITS: SymbolRange = SymbolRange.Companion.range('߀', '߉') // 0x7c0 - 0x7c9
    val NKO_LETTERS_TO_ARCHAIC_LETTERS: SymbolRange = SymbolRange.Companion.range('ߊ', 'ߪ') // 0x7ca - 0x7ea
    val NKO_TONE_MARKS_TO_OTHER_DIACRITICS: SymbolRange = SymbolRange.Companion.range('߫', '߳') // 0x7eb - 0x7f3
    val NKO_TONAL_APOSTROPHES: SymbolRange = SymbolRange.Companion.range('ߴ', 'ߵ') // 0x7f4 - 0x7f5
    val NKO_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('߷', '߹') // 0x7f7 - 0x7f9
    val NKO_CURRENCY_SYMBOLS: SymbolRange = SymbolRange.Companion.range('߾', '߿') // 0x7fe - 0x7ff
    val SAMARITAN_LETTERS: SymbolRange = SymbolRange.Companion.range('ࠀ', 'ࠕ') // 0x800 - 0x815
    val SAMARITAN_CONSONANT_MODIFIERS_SUBSET: SymbolRange = SymbolRange.Companion.range('ࠖ', '࠙') // 0x816 - 0x819
    val SAMARITAN_CONSONANT_MODIFIERS_TO_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ࠛ', 'ࠣ') // 0x81b - 0x823
    val SAMARITAN_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ࠥ', 'ࠧ') // 0x825 - 0x827
    val SAMARITAN_VOWEL_SIGNS_TO_VOWEL_SIGNS: SymbolRange = SymbolRange.Companion.range('ࠩ', '࠭') // 0x829 - 0x82d
    val SAMARITAN_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('࠰', '࠾') // 0x830 - 0x83e
    val MANDAIC_LETTERS: SymbolRange = SymbolRange.Companion.range('ࡀ', 'ࡘ') // 0x840 - 0x858
    val MANDAIC_DIACRITICS_SUBSET: SymbolRange = SymbolRange.Companion.range('࡙', '࡛') // 0x859 - 0x85b
    val SYRIAC_SUPPLEMENT_SYRIAC_LETTERS_SUBSET: SymbolRange = SymbolRange.Companion.range('ࡠ', 'ࡪ') // 0x860 - 0x86a
    val ARABIC_EXTENDED_A_ARABIC_LETTERS_FOR_AFRICAN_LANGUAGES_TO_ARABIC_LETTERS_FOR_ARWI: SymbolRange =
        SymbolRange.Companion.range('ࢠ', 'ࢴ') // 0x8a0 - 0x8b4
    val ARABIC_EXTENDED_A_ARABIC_LETTERS_FOR_BRAVANESE_TO_ARABIC_LETTERS_FOR_HAUSA_WOLOF_AND_OTHER_AFRICAN_ORTHOGRAPHIES: SymbolRange =
        SymbolRange.Companion.range('ࢶ', 'ࣇ') // 0x8b6 - 0x8c7
    val ARABIC_EXTENDED_A_QURANIC_ANNOTATION_SIGNS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('࣓', '࣡') // 0x8d3 - 0x8e1
    val ARABIC_EXTENDED_A_TO_DEVANAGARI: SymbolRange = SymbolRange.Companion.range('ࣣ', 'ं') // 0x8e3 - 0x902
    val ARABIC_EXTENDED_A_TO_DEVANAGARI_1: SymbolRange = SymbolRange.Companion.range('ࣣ', 'ः') // 0x8e3 - 0x903
    val DEVANAGARI_VARIOUS_SIGNS_TO_SINDHI_IMPLOSIVES: SymbolRange =
        SymbolRange.Companion.range('ऀ', 'ॿ') // 0x900 - 0x97f
    val DEVANAGARI_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange =
        SymbolRange.Companion.range('ऄ', 'ह') // 0x904 - 0x939
    val DEVANAGARI_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ऺ', '़') // 0x93a - 0x93c
    val DEVANAGARI_DEPENDENT_VOWEL_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ा', 'ी') // 0x93e - 0x940
    val DEVANAGARI_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS_1: SymbolRange =
        SymbolRange.Companion.range('ा', 'ॏ') // 0x93e - 0x94f
    val DEVANAGARI_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ु', 'ै') // 0x941 - 0x948
    val DEVANAGARI_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ॉ', 'ौ') // 0x949 - 0x94c
    val DEVANAGARI_DEPENDENT_VOWEL_SIGNS_2: SymbolRange = SymbolRange.Companion.range('ॎ', 'ॏ') // 0x94e - 0x94f
    val DEVANAGARI_VEDIC_TONE_MARKS_TO_DEPENDENT_VOWEL_SIGNS_FOR_KASHMIRI: SymbolRange =
        SymbolRange.Companion.range('॑', 'ॗ') // 0x951 - 0x957
    val DEVANAGARI_ADDITIONAL_CONSONANTS_TO_ADDITIONAL_VOWELS_FOR_SANSKRIT: SymbolRange =
        SymbolRange.Companion.range('क़', 'ॡ') // 0x958 - 0x961
    val DEVANAGARI_ADDITIONAL_VOWELS_FOR_SANSKRIT_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ॢ', 'ॣ') // 0x962 - 0x963
    val DEVANAGARI_GENERIC_PUNCTUATION_FOR_SCRIPTS_OF_INDIA: SymbolRange =
        SymbolRange.Companion.range('।', '॥') // 0x964 - 0x965
    val DEVANAGARI_DIGITS: SymbolRange = SymbolRange.Companion.range('०', '९') // 0x966 - 0x96f
    val DEVANAGARI_ADDITIONAL_SIGNS_TO_SINDHI_IMPLOSIVES: SymbolRange =
        SymbolRange.Companion.range('ॱ', 'ঀ') // 0x971 - 0x980
    val DEVANAGARI_INDEPENDENT_VOWEL_FOR_MARATHI_TO_SINDHI_IMPLOSIVES: SymbolRange =
        SymbolRange.Companion.range('ॲ', 'ঀ') // 0x972 - 0x980
    val BENGALI_VARIOUS_SIGNS_TO_SIGNS: SymbolRange = SymbolRange.Companion.range('ঀ', '৿') // 0x980 - 0x9ff
    val BENGALI_VARIOUS_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ঁ', 'ঃ') // 0x981 - 0x983
    val BENGALI_VARIOUS_SIGNS_SUBSET_6: SymbolRange = SymbolRange.Companion.range('ং', 'ঃ') // 0x982 - 0x983
    val BENGALI_INDEPENDENT_VOWELS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('অ', 'ঌ') // 0x985 - 0x98c
    val BENGALI_INDEPENDENT_VOWELS_SUBSET_6: SymbolRange = SymbolRange.Companion.range('এ', 'ঐ') // 0x98f - 0x990
    val BENGALI_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ও', 'ন') // 0x993 - 0x9a8
    val BENGALI_CONSONANTS_SUBSET_4: SymbolRange = SymbolRange.Companion.range('প', 'র') // 0x9aa - 0x9b0
    val BENGALI_CONSONANTS_SUBSET_15: SymbolRange = SymbolRange.Companion.range('শ', 'হ') // 0x9b6 - 0x9b9
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_13: SymbolRange = SymbolRange.Companion.range('া', 'ী') // 0x9be - 0x9c0
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_19: SymbolRange = SymbolRange.Companion.range('া', 'ৄ') // 0x9be - 0x9c4
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ু', 'ৄ') // 0x9c1 - 0x9c4
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_6: SymbolRange = SymbolRange.Companion.range('ে', 'ৈ') // 0x9c7 - 0x9c8
    val BENGALI_TWO_PART_DEPENDENT_VOWEL_SIGNS: SymbolRange = SymbolRange.Companion.range('ো', 'ৌ') // 0x9cb - 0x9cc
    val BENGALI_TWO_PART_DEPENDENT_VOWEL_SIGNS_TO_TWO_PART_DEPENDENT_VOWEL_SIGNS_1: SymbolRange =
        SymbolRange.Companion.range('ো', '্') // 0x9cb - 0x9cd
    val BENGALI_ADDITIONAL_CONSONANTS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ড়', 'ঢ়') // 0x9dc - 0x9dd
    val BENGALI_ADDITIONAL_CONSONANTS_TO_ADDITIONAL_VOWELS_FOR_SANSKRIT: SymbolRange =
        SymbolRange.Companion.range('য়', 'ৡ') // 0x9df - 0x9e1
    val BENGALI_ADDITIONAL_VOWELS_FOR_SANSKRIT_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ৢ', 'ৣ') // 0x9e2 - 0x9e3
    val BENGALI_DIGITS: SymbolRange = SymbolRange.Companion.range('০', '৯') // 0x9e6 - 0x9ef
    val BENGALI_ADDITIONS_FOR_ASSAMESE: SymbolRange = SymbolRange.Companion.range('ৰ', 'ৱ') // 0x9f0 - 0x9f1
    val BENGALI_CURRENCY_SYMBOLS: SymbolRange = SymbolRange.Companion.range('৲', '৳') // 0x9f2 - 0x9f3
    val BENGALI_HISTORIC_SYMBOLS_FOR_FRACTIONAL_VALUES: SymbolRange =
        SymbolRange.Companion.range('৴', '৹') // 0x9f4 - 0x9f9
    val BENGALI_SIGN_TO_SIGN: SymbolRange = SymbolRange.Companion.range('৺', '৻') // 0x9fa - 0x9fb
    val BENGALI_GURMUKHI_TO_SIGNS: SymbolRange = SymbolRange.Companion.range('਀', '੿') // 0xa00 - 0xa7f
    val BENGALI_VARIOUS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ਁ', 'ਂ') // 0xa01 - 0xa02
    val BENGALI_VARIOUS_SIGNS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ਁ', 'ਃ') // 0xa01 - 0xa03
    val BENGALI_INDEPENDENT_VOWELS_SUBSET: SymbolRange = SymbolRange.Companion.range('ਅ', 'ਊ') // 0xa05 - 0xa0a
    val BENGALI_INDEPENDENT_VOWELS_SUBSET_7: SymbolRange = SymbolRange.Companion.range('ਏ', 'ਐ') // 0xa0f - 0xa10
    val BENGALI_INDEPENDENT_VOWELS_TO_CONSONANTS_1: SymbolRange = SymbolRange.Companion.range('ਓ', 'ਨ') // 0xa13 - 0xa28
    val BENGALI_CONSONANTS_SUBSET_5: SymbolRange = SymbolRange.Companion.range('ਪ', 'ਰ') // 0xa2a - 0xa30
    val BENGALI_CONSONANTS_SUBSET_10: SymbolRange = SymbolRange.Companion.range('ਲ', 'ਲ਼') // 0xa32 - 0xa33
    val BENGALI_CONSONANTS_SUBSET_12: SymbolRange = SymbolRange.Companion.range('ਵ', 'ਸ਼') // 0xa35 - 0xa36
    val BENGALI_CONSONANTS_SUBSET_16: SymbolRange = SymbolRange.Companion.range('ਸ', 'ਹ') // 0xa38 - 0xa39
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_14: SymbolRange = SymbolRange.Companion.range('ਾ', 'ੀ') // 0xa3e - 0xa40
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_16: SymbolRange = SymbolRange.Companion.range('ਾ', 'ੂ') // 0xa3e - 0xa42
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ੁ', 'ੂ') // 0xa41 - 0xa42
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_7: SymbolRange = SymbolRange.Companion.range('ੇ', 'ੈ') // 0xa47 - 0xa48
    val BENGALI_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ੋ', '੍') // 0xa4b - 0xa4d
    val BENGALI_ADDITIONAL_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ਖ਼', 'ੜ') // 0xa59 - 0xa5c
    val BENGALI_DIGITS_1: SymbolRange = SymbolRange.Companion.range('੦', '੯') // 0xa66 - 0xa6f
    val BENGALI_SIGNS_1: SymbolRange = SymbolRange.Companion.range('ੰ', 'ੱ') // 0xa70 - 0xa71
    val BENGALI_VOWEL_BASES_TO_VOWEL_BASES: SymbolRange = SymbolRange.Companion.range('ੲ', 'ੴ') // 0xa72 - 0xa74
    val BENGALI_GUJARATI_TO_TRANSLITERATION_SIGNS: SymbolRange = SymbolRange.Companion.range('઀', '૿') // 0xa80 - 0xaff
    val BENGALI_VARIOUS_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ઁ', 'ં') // 0xa81 - 0xa82
    val BENGALI_VARIOUS_SIGNS_SUBSET_4: SymbolRange = SymbolRange.Companion.range('ઁ', 'ઃ') // 0xa81 - 0xa83
    val BENGALI_INDEPENDENT_VOWELS_SUBSET_4: SymbolRange = SymbolRange.Companion.range('અ', 'ઍ') // 0xa85 - 0xa8d
    val BENGALI_INDEPENDENT_VOWELS_SUBSET_9: SymbolRange = SymbolRange.Companion.range('એ', 'ઑ') // 0xa8f - 0xa91
    val BENGALI_INDEPENDENT_VOWELS_TO_CONSONANTS_2: SymbolRange = SymbolRange.Companion.range('ઓ', 'ન') // 0xa93 - 0xaa8
    val BENGALI_CONSONANTS_SUBSET_6: SymbolRange = SymbolRange.Companion.range('પ', 'ર') // 0xaaa - 0xab0
    val BENGALI_CONSONANTS_SUBSET_9: SymbolRange = SymbolRange.Companion.range('લ', 'ળ') // 0xab2 - 0xab3
    val BENGALI_CONSONANTS_SUBSET_13: SymbolRange = SymbolRange.Companion.range('વ', 'હ') // 0xab5 - 0xab9
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_15: SymbolRange = SymbolRange.Companion.range('ા', 'ી') // 0xabe - 0xac0
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_18: SymbolRange = SymbolRange.Companion.range('ા', 'ૅ') // 0xabe - 0xac5
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_4: SymbolRange = SymbolRange.Companion.range('ુ', 'ૅ') // 0xac1 - 0xac5
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_9: SymbolRange = SymbolRange.Companion.range('ે', 'ૈ') // 0xac7 - 0xac8
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_10: SymbolRange = SymbolRange.Companion.range('ે', 'ૉ') // 0xac7 - 0xac9
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_11: SymbolRange = SymbolRange.Companion.range('ો', 'ૌ') // 0xacb - 0xacc
    val BENGALI_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS_1: SymbolRange =
        SymbolRange.Companion.range('ો', '્') // 0xacb - 0xacd
    val BENGALI_ADDITIONAL_VOWELS_FOR_SANSKRIT_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ૠ', 'ૡ') // 0xae0 - 0xae1
    val BENGALI_ADDITIONAL_VOWELS_FOR_SANSKRIT_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('ૢ', 'ૣ') // 0xae2 - 0xae3
    val BENGALI_DIGITS_2: SymbolRange = SymbolRange.Companion.range('૦', '૯') // 0xae6 - 0xaef
    val BENGALI_TRANSLITERATION_SIGNS: SymbolRange = SymbolRange.Companion.range('ૺ', '૿') // 0xafa - 0xaff
    val BENGALI_ORIYA_TO_FRACTION_SIGNS: SymbolRange = SymbolRange.Companion.range('଀', '୿') // 0xb00 - 0xb7f
    val BENGALI_VARIOUS_SIGNS_SUBSET_5: SymbolRange = SymbolRange.Companion.range('ଁ', 'ଃ') // 0xb01 - 0xb03
    val BENGALI_VARIOUS_SIGNS_SUBSET_7: SymbolRange = SymbolRange.Companion.range('ଂ', 'ଃ') // 0xb02 - 0xb03
    val BENGALI_INDEPENDENT_VOWELS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ଅ', 'ଌ') // 0xb05 - 0xb0c
    val BENGALI_INDEPENDENT_VOWELS_SUBSET_8: SymbolRange = SymbolRange.Companion.range('ଏ', 'ଐ') // 0xb0f - 0xb10
    val BENGALI_INDEPENDENT_VOWELS_TO_CONSONANTS_3: SymbolRange = SymbolRange.Companion.range('ଓ', 'ନ') // 0xb13 - 0xb28
    val BENGALI_CONSONANTS_SUBSET_7: SymbolRange = SymbolRange.Companion.range('ପ', 'ର') // 0xb2a - 0xb30
    val BENGALI_CONSONANTS_SUBSET_11: SymbolRange = SymbolRange.Companion.range('ଲ', 'ଳ') // 0xb32 - 0xb33
    val BENGALI_CONSONANTS_SUBSET_14: SymbolRange = SymbolRange.Companion.range('ଵ', 'ହ') // 0xb35 - 0xb39
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_20: SymbolRange = SymbolRange.Companion.range('ା', 'ୄ') // 0xb3e - 0xb44
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ୁ', 'ୄ') // 0xb41 - 0xb44
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_8: SymbolRange = SymbolRange.Companion.range('େ', 'ୈ') // 0xb47 - 0xb48
    val BENGALI_TWO_PART_DEPENDENT_VOWEL_SIGNS_1: SymbolRange = SymbolRange.Companion.range('ୋ', 'ୌ') // 0xb4b - 0xb4c
    val BENGALI_TWO_PART_DEPENDENT_VOWEL_SIGNS_TO_TWO_PART_DEPENDENT_VOWEL_SIGNS_2: SymbolRange =
        SymbolRange.Companion.range('ୋ', '୍') // 0xb4b - 0xb4d
    val BENGALI_VARIOUS_SIGNS_SUBSET_8: SymbolRange = SymbolRange.Companion.range('୕', 'ୖ') // 0xb55 - 0xb56
    val BENGALI_VARIOUS_SIGNS_SUBSET_9: SymbolRange = SymbolRange.Companion.range('୕', 'ୗ') // 0xb55 - 0xb57
    val BENGALI_ADDITIONAL_CONSONANTS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ଡ଼', 'ଢ଼') // 0xb5c - 0xb5d
    val BENGALI_ADDITIONAL_CONSONANTS_TO_ADDITIONAL_VOWELS_FOR_SANSKRIT_1: SymbolRange =
        SymbolRange.Companion.range('ୟ', 'ୡ') // 0xb5f - 0xb61
    val BENGALI_DEPENDENT_VOWELS: SymbolRange = SymbolRange.Companion.range('ୢ', 'ୣ') // 0xb62 - 0xb63
    val BENGALI_DIGITS_3: SymbolRange = SymbolRange.Companion.range('୦', '୯') // 0xb66 - 0xb6f
    val BENGALI_FRACTION_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('୲', '୷') // 0xb72 - 0xb77
    val BENGALI_TAMIL_TO_TAMIL_CLERICAL_SYMBOL: SymbolRange = SymbolRange.Companion.range('஀', '௿') // 0xb80 - 0xbff
    val BENGALI_INDEPENDENT_VOWELS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('அ', 'ஊ') // 0xb85 - 0xb8a
    val BENGALI_INDEPENDENT_VOWELS_SUBSET_5: SymbolRange = SymbolRange.Companion.range('எ', 'ஐ') // 0xb8e - 0xb90
    val BENGALI_INDEPENDENT_VOWELS_TO_INDEPENDENT_VOWELS: SymbolRange =
        SymbolRange.Companion.range('ஒ', 'க') // 0xb92 - 0xb95
    val BENGALI_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ங', 'ச') // 0xb99 - 0xb9a
    val BENGALI_CONSONANTS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ஞ', 'ட') // 0xb9e - 0xb9f
    val BENGALI_CONSONANTS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ண', 'த') // 0xba3 - 0xba4
    val BENGALI_CONSONANTS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ந', 'ப') // 0xba8 - 0xbaa
    val BENGALI_CONSONANTS_SUBSET_8: SymbolRange = SymbolRange.Companion.range('ம', 'ஹ') // 0xbae - 0xbb9
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_12: SymbolRange = SymbolRange.Companion.range('ா', 'ி') // 0xbbe - 0xbbf
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_17: SymbolRange = SymbolRange.Companion.range('ா', 'ூ') // 0xbbe - 0xbc2
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ு', 'ூ') // 0xbc1 - 0xbc2
    val BENGALI_DEPENDENT_VOWEL_SIGNS_SUBSET_5: SymbolRange = SymbolRange.Companion.range('ெ', 'ை') // 0xbc6 - 0xbc8
    val BENGALI_TWO_PART_DEPENDENT_VOWEL_SIGNS_TO_TWO_PART_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ொ', '்') // 0xbca - 0xbcd
    val BENGALI_TWO_PART_DEPENDENT_VOWEL_SIGNS_2: SymbolRange = SymbolRange.Companion.range('ொ', 'ௌ') // 0xbca - 0xbcc
    val BENGALI_DIGITS_4: SymbolRange = SymbolRange.Companion.range('௦', '௯') // 0xbe6 - 0xbef
    val BENGALI_DIGITS_TO_TAMIL_NUMERICS: SymbolRange = SymbolRange.Companion.range('௦', '௲') // 0xbe6 - 0xbf2
    val BENGALI_TAMIL_NUMERICS: SymbolRange = SymbolRange.Companion.range('௰', '௲') // 0xbf0 - 0xbf2
    val BENGALI_TAMIL_CALENDRICAL_SYMBOLS_TO_TAMIL_CLERICAL_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('௳', '௸') // 0xbf3 - 0xbf8
    val BENGALI_TAMIL_CALENDRICAL_SYMBOLS_TO_CURRENCY_SYMBOL: SymbolRange =
        SymbolRange.Companion.range('௳', '௺') // 0xbf3 - 0xbfa
    val TELUGU_VARIOUS_SIGNS: SymbolRange = SymbolRange.Companion.range('ఀ', 'ఄ') // 0xc00 - 0xc04
    val TELUGU_VARIOUS_SIGNS_TO_TELUGU_FRACTIONS_AND_WEIGHTS: SymbolRange =
        SymbolRange.Companion.range('ఀ', '౿') // 0xc00 - 0xc7f
    val TELUGU_VARIOUS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ఁ', 'ః') // 0xc01 - 0xc03
    val TELUGU_INDEPENDENT_VOWELS_SUBSET: SymbolRange = SymbolRange.Companion.range('అ', 'ఌ') // 0xc05 - 0xc0c
    val TELUGU_INDEPENDENT_VOWELS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ఎ', 'ఐ') // 0xc0e - 0xc10
    val TELUGU_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ఒ', 'న') // 0xc12 - 0xc28
    val TELUGU_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ప', 'హ') // 0xc2a - 0xc39
    val TELUGU_DEPENDENT_VOWEL_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ా', 'ీ') // 0xc3e - 0xc40
    val TELUGU_DEPENDENT_VOWEL_SIGNS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ా', 'ౄ') // 0xc3e - 0xc44
    val TELUGU_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ు', 'ౄ') // 0xc41 - 0xc44
    val TELUGU_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ె', 'ై') // 0xc46 - 0xc48
    val TELUGU_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ొ', '్') // 0xc4a - 0xc4d
    val TELUGU_VARIOUS_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ౕ', 'ౖ') // 0xc55 - 0xc56
    val TELUGU_HISTORIC_PHONETIC_VARIANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ౘ', 'ౚ') // 0xc58 - 0xc5a
    val TELUGU_ADDITIONAL_VOWELS_FOR_SANSKRIT: SymbolRange = SymbolRange.Companion.range('ౠ', 'ౡ') // 0xc60 - 0xc61
    val TELUGU_DEPENDENT_VOWELS: SymbolRange = SymbolRange.Companion.range('ౢ', 'ౣ') // 0xc62 - 0xc63
    val TELUGU_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('౦', '౯') // 0xc66 - 0xc6f
    val TELUGU_TELUGU_FRACTIONS_AND_WEIGHTS_SUBSET: SymbolRange = SymbolRange.Companion.range('౸', '౾') // 0xc78 - 0xc7e
    val KANNADA_VARIOUS_SIGNS_TO_SIGNS_USED_IN_SANSKRIT: SymbolRange =
        SymbolRange.Companion.range('ಀ', '೿') // 0xc80 - 0xcff
    val KANNADA_VARIOUS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ಁ', 'ಃ') // 0xc81 - 0xc83
    val KANNADA_VARIOUS_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ಂ', 'ಃ') // 0xc82 - 0xc83
    val KANNADA_INDEPENDENT_VOWELS_SUBSET: SymbolRange = SymbolRange.Companion.range('ಅ', 'ಌ') // 0xc85 - 0xc8c
    val KANNADA_INDEPENDENT_VOWELS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ಎ', 'ಐ') // 0xc8e - 0xc90
    val KANNADA_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ಒ', 'ನ') // 0xc92 - 0xca8
    val KANNADA_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ಪ', 'ಳ') // 0xcaa - 0xcb3
    val KANNADA_CONSONANTS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ವ', 'ಹ') // 0xcb5 - 0xcb9
    val KANNADA_DEPENDENT_VOWEL_SIGNS_SUBSET_4: SymbolRange = SymbolRange.Companion.range('ಾ', 'ೄ') // 0xcbe - 0xcc4
    val KANNADA_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ೀ', 'ೄ') // 0xcc0 - 0xcc4
    val KANNADA_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ೆ', 'ೈ') // 0xcc6 - 0xcc8
    val KANNADA_DEPENDENT_VOWEL_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ೇ', 'ೈ') // 0xcc7 - 0xcc8
    val KANNADA_DEPENDENT_VOWEL_SIGNS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ೊ', 'ೋ') // 0xcca - 0xccb
    val KANNADA_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ೊ', '್') // 0xcca - 0xccd
    val KANNADA_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS_1: SymbolRange =
        SymbolRange.Companion.range('ೌ', '್') // 0xccc - 0xccd
    val KANNADA_VARIOUS_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ೕ', 'ೖ') // 0xcd5 - 0xcd6
    val KANNADA_ADDITIONAL_VOWELS_FOR_SANSKRIT: SymbolRange = SymbolRange.Companion.range('ೠ', 'ೡ') // 0xce0 - 0xce1
    val KANNADA_DEPENDENT_VOWELS: SymbolRange = SymbolRange.Companion.range('ೢ', 'ೣ') // 0xce2 - 0xce3
    val KANNADA_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('೦', '೯') // 0xce6 - 0xcef
    val KANNADA_SIGNS_USED_IN_SANSKRIT_SUBSET: SymbolRange = SymbolRange.Companion.range('ೱ', 'ೲ') // 0xcf1 - 0xcf2
    val MALAYALAM_VARIOUS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ഀ', 'ഁ') // 0xd00 - 0xd01
    val MALAYALAM_VARIOUS_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ഀ', 'ഃ') // 0xd00 - 0xd03
    val MALAYALAM_VARIOUS_SIGNS_TO_CHILLU_LETTERS: SymbolRange = SymbolRange.Companion.range('ഀ', 'ൿ') // 0xd00 - 0xd7f
    val MALAYALAM_VARIOUS_SIGNS_SUBSET_4: SymbolRange = SymbolRange.Companion.range('ം', 'ഃ') // 0xd02 - 0xd03
    val MALAYALAM_VARIOUS_SIGNS_TO_INDEPENDENT_VOWELS: SymbolRange =
        SymbolRange.Companion.range('ഄ', 'ഌ') // 0xd04 - 0xd0c
    val MALAYALAM_INDEPENDENT_VOWELS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('എ', 'ഐ') // 0xd0e - 0xd10
    val MALAYALAM_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ഒ', 'ഺ') // 0xd12 - 0xd3a
    val MALAYALAM_VARIANT_SHAPE_VIRAMAS: SymbolRange = SymbolRange.Companion.range('഻', '഼') // 0xd3b - 0xd3c
    val MALAYALAM_DEPENDENT_VOWEL_SIGNS_SUBSET_5: SymbolRange = SymbolRange.Companion.range('ാ', 'ീ') // 0xd3e - 0xd40
    val MALAYALAM_DEPENDENT_VOWEL_SIGNS_SUBSET_6: SymbolRange = SymbolRange.Companion.range('ാ', 'ൄ') // 0xd3e - 0xd44
    val MALAYALAM_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ു', 'ൄ') // 0xd41 - 0xd44
    val MALAYALAM_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('െ', 'ൈ') // 0xd46 - 0xd48
    val MALAYALAM_TWO_PART_DEPENDENT_VOWEL_SIGNS_TO_TWO_PART_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ൊ', '്') // 0xd4a - 0xd4d
    val MALAYALAM_TWO_PART_DEPENDENT_VOWEL_SIGNS: SymbolRange = SymbolRange.Companion.range('ൊ', 'ൌ') // 0xd4a - 0xd4c
    val MALAYALAM_ADDITIONAL_HISTORIC_CHILLU_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ൔ', 'ൖ') // 0xd54 - 0xd56
    val MALAYALAM_MINOR_FRACTIONS: SymbolRange = SymbolRange.Companion.range('൘', '൞') // 0xd58 - 0xd5e
    val MALAYALAM_ADDITIONAL_HISTORIC_VOWEL_TO_ADDITIONAL_VOWELS_FOR_SANSKRIT: SymbolRange =
        SymbolRange.Companion.range('ൟ', 'ൡ') // 0xd5f - 0xd61
    val MALAYALAM_DEPENDENT_VOWELS: SymbolRange = SymbolRange.Companion.range('ൢ', 'ൣ') // 0xd62 - 0xd63
    val MALAYALAM_DIGITS: SymbolRange = SymbolRange.Companion.range('൦', '൯') // 0xd66 - 0xd6f
    val MALAYALAM_DIGITS_TO_FRACTIONS: SymbolRange = SymbolRange.Companion.range('൦', '൸') // 0xd66 - 0xd78
    val MALAYALAM_MALAYALAM_NUMERICS_TO_FRACTIONS: SymbolRange = SymbolRange.Companion.range('൰', '൸') // 0xd70 - 0xd78
    val MALAYALAM_CHILLU_LETTERS: SymbolRange = SymbolRange.Companion.range('ൺ', 'ൿ') // 0xd7a - 0xd7f
    val MALAYALAM_SINHALA_TO_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('඀', '෿') // 0xd80 - 0xdff
    val MALAYALAM_VARIOUS_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ඁ', 'ඃ') // 0xd81 - 0xd83
    val MALAYALAM_VARIOUS_SIGNS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ං', 'ඃ') // 0xd82 - 0xd83
    val MALAYALAM_INDEPENDENT_VOWELS_SUBSET: SymbolRange = SymbolRange.Companion.range('අ', 'ඖ') // 0xd85 - 0xd96
    val MALAYALAM_CONSONANTS_SUBSET_4: SymbolRange = SymbolRange.Companion.range('ක', 'න') // 0xd9a - 0xdb1
    val MALAYALAM_CONSONANTS_SUBSET_5: SymbolRange = SymbolRange.Companion.range('ඳ', 'ර') // 0xdb3 - 0xdbb
    val MALAYALAM_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ව', 'ෆ') // 0xdc0 - 0xdc6
    val MALAYALAM_DEPENDENT_VOWEL_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ා', 'ෑ') // 0xdcf - 0xdd1
    val MALAYALAM_DEPENDENT_VOWEL_SIGNS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ා', 'ු') // 0xdcf - 0xdd4
    val MALAYALAM_DEPENDENT_VOWEL_SIGNS_SUBSET_4: SymbolRange = SymbolRange.Companion.range('ි', 'ු') // 0xdd2 - 0xdd4
    val MALAYALAM_DEPENDENT_VOWEL_SIGNS_TO_TWO_PART_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ෘ', 'ෟ') // 0xdd8 - 0xddf
    val MALAYALAM_ASTROLOGICAL_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('෦', '෯') // 0xde6 - 0xdef
    val MALAYALAM_ADDITIONAL_DEPENDENT_VOWEL_SIGNS: SymbolRange = SymbolRange.Companion.range('ෲ', 'ෳ') // 0xdf2 - 0xdf3
    val MALAYALAM_THAI_TO_SIGNS: SymbolRange = SymbolRange.Companion.range('฀', '๿') // 0xe00 - 0xe7f
    val MALAYALAM_CONSONANTS_TO_SIGN: SymbolRange = SymbolRange.Companion.range('ก', 'ะ') // 0xe01 - 0xe30
    val MALAYALAM_VOWELS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('า', 'ำ') // 0xe32 - 0xe33
    val MALAYALAM_VOWELS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ิ', 'ฺ') // 0xe34 - 0xe3a
    val MALAYALAM_VOWELS_TO_VOWELS: SymbolRange = SymbolRange.Companion.range('เ', 'ๅ') // 0xe40 - 0xe45
    val MALAYALAM_VOWELS_TO_VOWEL_LENGTH_SIGN: SymbolRange = SymbolRange.Companion.range('เ', 'ๆ') // 0xe40 - 0xe46
    val MALAYALAM_VOWEL_TO_SIGNS: SymbolRange = SymbolRange.Companion.range('็', '๎') // 0xe47 - 0xe4e
    val MALAYALAM_DIGITS_1: SymbolRange = SymbolRange.Companion.range('๐', '๙') // 0xe50 - 0xe59
    val MALAYALAM_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('๚', '๛') // 0xe5a - 0xe5b
    val MALAYALAM_LAO_TO_CONSONANTS_FOR_KHMU: SymbolRange = SymbolRange.Companion.range('຀', '໿') // 0xe80 - 0xeff
    val MALAYALAM_CONSONANTS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ກ', 'ຂ') // 0xe81 - 0xe82
    val MALAYALAM_CONSONANTS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ຆ', 'ຊ') // 0xe86 - 0xe8a
    val MALAYALAM_CONSONANTS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ຌ', 'ຣ') // 0xe8c - 0xea3
    val MALAYALAM_CONSONANTS_TO_SIGN_1: SymbolRange = SymbolRange.Companion.range('ວ', 'ະ') // 0xea7 - 0xeb0
    val MALAYALAM_VOWELS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('າ', 'ຳ') // 0xeb2 - 0xeb3
    val MALAYALAM_VOWELS_TO_VOWEL: SymbolRange = SymbolRange.Companion.range('ິ', 'ຼ') // 0xeb4 - 0xebc
    val MALAYALAM_VOWELS_SUBSET: SymbolRange = SymbolRange.Companion.range('ເ', 'ໄ') // 0xec0 - 0xec4
    val MALAYALAM_TONE_MARKS_TO_SIGNS: SymbolRange = SymbolRange.Companion.range('່', 'ໍ') // 0xec8 - 0xecd
    val MALAYALAM_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('໐', '໙') // 0xed0 - 0xed9
    val MALAYALAM_DIGRAPHS_TO_CONSONANTS_FOR_KHMU: SymbolRange = SymbolRange.Companion.range('ໜ', 'ໟ') // 0xedc - 0xedf
    val TIBETAN_SYLLABLE_TO_ANNOTATION_MARKS: SymbolRange = SymbolRange.Companion.range('ༀ', '࿿') // 0xf00 - 0xfff
    val TIBETAN_HEAD_MARKS_SUBSET: SymbolRange = SymbolRange.Companion.range('༁', '༃') // 0xf01 - 0xf03
    val TIBETAN_HEAD_MARKS_TO_MARKS_AND_SIGNS: SymbolRange = SymbolRange.Companion.range('༄', '༒') // 0xf04 - 0xf12
    val TIBETAN_ASTROLOGICAL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('༕', '༗') // 0xf15 - 0xf17
    val TIBETAN_ASTROLOGICAL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('༘', '༙') // 0xf18 - 0xf19
    val TIBETAN_ASTROLOGICAL_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('༚', '༟') // 0xf1a - 0xf1f
    val TIBETAN_DIGITS: SymbolRange = SymbolRange.Companion.range('༠', '༩') // 0xf20 - 0xf29
    val TIBETAN_DIGITS_TO_DIGITS_MINUS_HALF: SymbolRange = SymbolRange.Companion.range('༠', '༳') // 0xf20 - 0xf33
    val TIBETAN_DIGITS_MINUS_HALF: SymbolRange = SymbolRange.Companion.range('༪', '༳') // 0xf2a - 0xf33
    val TIBETAN_PAIRED_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('༺', '༽') // 0xf3a - 0xf3d
    val TIBETAN_ASTROLOGICAL_SIGNS_1: SymbolRange = SymbolRange.Companion.range('༾', '༿') // 0xf3e - 0xf3f
    val TIBETAN_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ཀ', 'ཇ') // 0xf40 - 0xf47
    val TIBETAN_CONSONANTS_TO_EXTENSIONS_FOR_BALTI: SymbolRange = SymbolRange.Companion.range('ཉ', 'ཬ') // 0xf49 - 0xf6c
    val TIBETAN_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ཱ', 'ཾ') // 0xf71 - 0xf7e
    val TIBETAN_DEPENDENT_VOWEL_SIGNS_TO_MARKS_AND_SIGNS_1: SymbolRange =
        SymbolRange.Companion.range('ཱ', '྄') // 0xf71 - 0xf84
    val TIBETAN_DEPENDENT_VOWEL_SIGNS_TO_MARKS_AND_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ྀ', '྄') // 0xf80 - 0xf84
    val TIBETAN_MARKS_AND_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('྆', '྇') // 0xf86 - 0xf87
    val TIBETAN_TRANSLITERATION_HEAD_LETTERS: SymbolRange = SymbolRange.Companion.range('ྈ', 'ྌ') // 0xf88 - 0xf8c
    val TIBETAN_TRANSLITERATION_SUBJOINED_SIGNS_TO_SUBJOINED_CONSONANTS: SymbolRange =
        SymbolRange.Companion.range('ྍ', 'ྗ') // 0xf8d - 0xf97
    val TIBETAN_SUBJOINED_CONSONANTS_TO_FIXED_FORM_SUBJOINED_CONSONANTS: SymbolRange =
        SymbolRange.Companion.range('ྙ', 'ྼ') // 0xf99 - 0xfbc
    val TIBETAN_SIGNS_TO_SYMBOLS: SymbolRange = SymbolRange.Companion.range('྾', '࿅') // 0xfbe - 0xfc5
    val TIBETAN_SYMBOLS_SUBSET: SymbolRange = SymbolRange.Companion.range('࿇', '࿌') // 0xfc7 - 0xfcc
    val TIBETAN_ASTROLOGICAL_SIGNS_2: SymbolRange = SymbolRange.Companion.range('࿎', '࿏') // 0xfce - 0xfcf
    val TIBETAN_MARKS_TO_HEAD_MARKS: SymbolRange = SymbolRange.Companion.range('࿐', '࿔') // 0xfd0 - 0xfd4
    val TIBETAN_RELIGIOUS_SYMBOLS: SymbolRange = SymbolRange.Companion.range('࿕', '࿘') // 0xfd5 - 0xfd8
    val TIBETAN_ANNOTATION_MARKS_SUBSET: SymbolRange = SymbolRange.Companion.range('࿙', '࿚') // 0xfd9 - 0xfda
    val MYANMAR_CONSONANTS_TO_INDEPENDENT_VOWELS: SymbolRange = SymbolRange.Companion.range('က', 'ဪ') // 0x1000 - 0x102a
    val MYANMAR_CONSONANTS_TO_SHAN_SYMBOLS: SymbolRange = SymbolRange.Companion.range('က', '႟') // 0x1000 - 0x109f
    val MYANMAR_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ါ', 'ာ') // 0x102b - 0x102c
    val MYANMAR_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_CONSONANT_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ါ', 'ှ') // 0x102b - 0x103e
    val MYANMAR_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ိ', 'ူ') // 0x102d - 0x1030
    val MYANMAR_DEPENDENT_VOWEL_SIGNS_TO_VARIOUS_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ဲ', '့') // 0x1032 - 0x1037
    val MYANMAR_VIRAMA_AND_KILLER: SymbolRange = SymbolRange.Companion.range('္', '်') // 0x1039 - 0x103a
    val MYANMAR_DEPENDENT_CONSONANT_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ျ', 'ြ') // 0x103b - 0x103c
    val MYANMAR_DEPENDENT_CONSONANT_SIGNS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ွ', 'ှ') // 0x103d - 0x103e
    val MYANMAR_DIGITS: SymbolRange = SymbolRange.Companion.range('၀', '၉') // 0x1040 - 0x1049
    val MYANMAR_PUNCTUATION_TO_VARIOUS_SIGNS: SymbolRange = SymbolRange.Companion.range('၊', '၏') // 0x104a - 0x104f
    val MYANMAR_PALI_AND_SANSKRIT_EXTENSIONS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ၐ', 'ၕ') // 0x1050 - 0x1055
    val MYANMAR_PALI_AND_SANSKRIT_EXTENSIONS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ၖ', 'ၙ') // 0x1056 - 0x1059
    val MYANMAR_PALI_AND_SANSKRIT_EXTENSIONS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('ၖ', 'ၗ') // 0x1056 - 0x1057
    val MYANMAR_PALI_AND_SANSKRIT_EXTENSIONS_SUBSET_3: SymbolRange =
        SymbolRange.Companion.range('ၘ', 'ၙ') // 0x1058 - 0x1059
    val MYANMAR_EXTENSIONS_FOR_MON_SUBSET: SymbolRange = SymbolRange.Companion.range('ၚ', 'ၝ') // 0x105a - 0x105d
    val MYANMAR_EXTENSIONS_FOR_MON_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ၞ', 'ၠ') // 0x105e - 0x1060
    val MYANMAR_EXTENSIONS_FOR_SGAW_KAREN_SUBSET: SymbolRange = SymbolRange.Companion.range('ၢ', 'ၤ') // 0x1062 - 0x1064
    val MYANMAR_EXTENSIONS_FOR_WESTERN_PWO_KAREN_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ၥ', 'ၦ') // 0x1065 - 0x1066
    val MYANMAR_EXTENSIONS_FOR_WESTERN_PWO_KAREN_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ၧ', 'ၭ') // 0x1067 - 0x106d
    val MYANMAR_EXTENSIONS_FOR_EASTERN_PWO_KAREN: SymbolRange = SymbolRange.Companion.range('ၮ', 'ၰ') // 0x106e - 0x1070
    val MYANMAR_EXTENSION_FOR_GEBA_KAREN_TO_EXTENSIONS_FOR_KAYAH: SymbolRange =
        SymbolRange.Companion.range('ၱ', 'ၴ') // 0x1071 - 0x1074
    val MYANMAR_EXTENSIONS_FOR_SHAN_SUBSET_4: SymbolRange = SymbolRange.Companion.range('ၵ', 'ႁ') // 0x1075 - 0x1081
    val MYANMAR_EXTENSIONS_FOR_SHAN_SUBSET: SymbolRange = SymbolRange.Companion.range('ႂ', 'ႍ') // 0x1082 - 0x108d
    val MYANMAR_EXTENSIONS_FOR_SHAN_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ႃ', 'ႄ') // 0x1083 - 0x1084
    val MYANMAR_EXTENSIONS_FOR_SHAN_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ႅ', 'ႆ') // 0x1085 - 0x1086
    val MYANMAR_EXTENSIONS_FOR_SHAN_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ႇ', 'ႌ') // 0x1087 - 0x108c
    val MYANMAR_SHAN_DIGITS: SymbolRange = SymbolRange.Companion.range('႐', '႙') // 0x1090 - 0x1099
    val MYANMAR_EXTENSIONS_FOR_KHAMTI_SHAN_TO_EXTENSIONS_FOR_KHAMTI_SHAN: SymbolRange =
        SymbolRange.Companion.range('ႚ', 'ႜ') // 0x109a - 0x109c
    val MYANMAR_EXTENSIONS_FOR_KHAMTI_SHAN_TO_EXTENSIONS_FOR_AITON_AND_PHAKE: SymbolRange =
        SymbolRange.Companion.range('ႚ', 'ႝ') // 0x109a - 0x109d
    val MYANMAR_SHAN_SYMBOLS: SymbolRange = SymbolRange.Companion.range('႞', '႟') // 0x109e - 0x109f
    val GEORGIAN_CAPITAL_LETTERS_KHUTSURI_SUBSET: SymbolRange = SymbolRange.Companion.range('Ⴀ', 'Ⴥ') // 0x10a0 - 0x10c5
    val GEORGIAN_CAPITAL_LETTERS_KHUTSURI_TO_ADDITIONAL_LETTERS_FOR_OSSETIAN_AND_ABKHAZ: SymbolRange =
        SymbolRange.Companion.range('Ⴀ', 'ჿ') // 0x10a0 - 0x10ff
    val GEORGIAN_MKHEDRULI_TO_ADDITIONAL_LETTERS: SymbolRange = SymbolRange.Companion.range('ა', 'ჺ') // 0x10d0 - 0x10fa
    val GEORGIAN_TO_ETHIOPIC: SymbolRange = SymbolRange.Companion.range('ჼ', 'ቈ') // 0x10fc - 0x1248
    val GEORGIAN_ADDITIONAL_LETTERS_FOR_OSSETIAN_AND_ABKHAZ: SymbolRange =
        SymbolRange.Companion.range('ჽ', 'ჿ') // 0x10fd - 0x10ff
    val HANGUL_JAMO_INITIAL_CONSONANTS_TO_OLD_FINAL_CONSONANTS: SymbolRange =
        SymbolRange.Companion.range('ᄀ', 'ᇿ') // 0x1100 - 0x11ff
    val HANGUL_JAMO_TO_ETHIOPIC: SymbolRange = SymbolRange.Companion.range('ᄀ', 'ቈ') // 0x1100 - 0x1248
    val ETHIOPIC_SYLLABLES_TO_NUMBERS: SymbolRange = SymbolRange.Companion.range('ሀ', '፿') // 0x1200 - 0x137f
    val ETHIOPIC_SYLLABLES_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ቊ', 'ቍ') // 0x124a - 0x124d
    val ETHIOPIC_SYLLABLES_SUBSET_4: SymbolRange = SymbolRange.Companion.range('ቐ', 'ቖ') // 0x1250 - 0x1256
    val ETHIOPIC_SYLLABLES_SUBSET_9: SymbolRange = SymbolRange.Companion.range('ቚ', 'ቝ') // 0x125a - 0x125d
    val ETHIOPIC_SYLLABLES_SUBSET_10: SymbolRange = SymbolRange.Companion.range('በ', 'ኈ') // 0x1260 - 0x1288
    val ETHIOPIC_SYLLABLES_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ኊ', 'ኍ') // 0x128a - 0x128d
    val ETHIOPIC_SYLLABLES_SUBSET_5: SymbolRange = SymbolRange.Companion.range('ነ', 'ኰ') // 0x1290 - 0x12b0
    val ETHIOPIC_SYLLABLES_SUBSET_11: SymbolRange = SymbolRange.Companion.range('ኲ', 'ኵ') // 0x12b2 - 0x12b5
    val ETHIOPIC_SYLLABLES_SUBSET_12: SymbolRange = SymbolRange.Companion.range('ኸ', 'ኾ') // 0x12b8 - 0x12be
    val ETHIOPIC_SYLLABLES_SUBSET: SymbolRange = SymbolRange.Companion.range('ዂ', 'ዅ') // 0x12c2 - 0x12c5
    val ETHIOPIC_SYLLABLES_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ወ', 'ዖ') // 0x12c8 - 0x12d6
    val ETHIOPIC_SYLLABLES_SUBSET_7: SymbolRange = SymbolRange.Companion.range('ዘ', 'ጐ') // 0x12d8 - 0x1310
    val ETHIOPIC_SYLLABLES_SUBSET_6: SymbolRange = SymbolRange.Companion.range('ጒ', 'ጕ') // 0x1312 - 0x1315
    val ETHIOPIC_SYLLABLES_SUBSET_8: SymbolRange = SymbolRange.Companion.range('ጘ', 'ፚ') // 0x1318 - 0x135a
    val ETHIOPIC_COMBINING_MARKS: SymbolRange = SymbolRange.Companion.range('፝', '፟') // 0x135d - 0x135f
    val ETHIOPIC_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('፠', '፨') // 0x1360 - 0x1368
    val ETHIOPIC_DIGITS_TO_NUMBERS: SymbolRange = SymbolRange.Companion.range('፩', '፼') // 0x1369 - 0x137c
    val ETHIOPIC_SUPPLEMENT_SYLLABLES_FOR_SEBATBEIT: SymbolRange =
        SymbolRange.Companion.range('ᎀ', 'ᎏ') // 0x1380 - 0x138f
    val ETHIOPIC_SUPPLEMENT_TONAL_MARKS_SUBSET: SymbolRange = SymbolRange.Companion.range('᎐', '᎙') // 0x1390 - 0x1399
    val CHEROKEE_UPPERCASE_SYLLABLES_TO_UPPERCASE_SYLLABLES: SymbolRange =
        SymbolRange.Companion.range('Ꭰ', 'Ᏽ') // 0x13a0 - 0x13f5
    val CHEROKEE_UPPERCASE_SYLLABLES_TO_ARCHAIC_LOWERCASE_SYLLABLE: SymbolRange =
        SymbolRange.Companion.range('Ꭰ', '᏿') // 0x13a0 - 0x13ff
    val CHEROKEE_LOWERCASE_SYLLABLES_TO_LOWERCASE_SYLLABLES: SymbolRange =
        SymbolRange.Companion.range('ᏸ', 'ᏽ') // 0x13f8 - 0x13fd
    val UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_PUNCTUATION_TO_SYLLABLES: SymbolRange =
        SymbolRange.Companion.range('᐀', 'ᙿ') // 0x1400 - 0x167f
    val UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_SYLLABLES_TO_SYLLABLES_FOR_CARRIER: SymbolRange =
        SymbolRange.Companion.range('ᐁ', 'ᙬ') // 0x1401 - 0x166c
    val UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_SYLLABLES_1: SymbolRange =
        SymbolRange.Companion.range('ᙯ', 'ᙿ') // 0x166f - 0x167f
    val OGHAM_SPACE_TO_PUNCTUATION: SymbolRange = SymbolRange.Companion.range(' ', '᚟') // 0x1680 - 0x169f
    val OGHAM_TRADITIONAL_LETTERS_TO_FORFEDA_SUPPLEMENTARY_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ᚁ', 'ᚚ') // 0x1681 - 0x169a
    val OGHAM_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('᚛', '᚜') // 0x169b - 0x169c
    val RUNIC_LETTERS: SymbolRange = SymbolRange.Companion.range('ᚠ', 'ᛪ') // 0x16a0 - 0x16ea
    val RUNIC_LETTERS_TO_CRYPTOGRAMMIC_LETTERS: SymbolRange = SymbolRange.Companion.range('ᚠ', '᛿') // 0x16a0 - 0x16ff
    val RUNIC_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('᛫', '᛭') // 0x16eb - 0x16ed
    val RUNIC_GOLDEN_NUMBER_RUNES: SymbolRange = SymbolRange.Companion.range('ᛮ', 'ᛰ') // 0x16ee - 0x16f0
    val RUNIC_TOLKIENIAN_EXTENSIONS_TO_CRYPTOGRAMMIC_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ᛱ', 'ᛸ') // 0x16f1 - 0x16f8
    val TAGALOG_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ᜀ', 'ᜌ') // 0x1700 - 0x170c
    val TAGALOG_INDEPENDENT_VOWELS_TO_VIRAMAS: SymbolRange = SymbolRange.Companion.range('ᜀ', 'ᜟ') // 0x1700 - 0x171f
    val TAGALOG_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᜎ', 'ᜑ') // 0x170e - 0x1711
    val TAGALOG_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ᜒ', '᜔') // 0x1712 - 0x1714
    val HANUNOO_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ᜠ', 'ᜱ') // 0x1720 - 0x1731
    val HANUNOO_INDEPENDENT_VOWELS_TO_GENERIC_PUNCTUATION_FOR_PHILIPPINE_SCRIPTS: SymbolRange =
        SymbolRange.Companion.range('ᜠ', '᜿') // 0x1720 - 0x173f
    val HANUNOO_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ᜲ', '᜴') // 0x1732 - 0x1734
    val HANUNOO_GENERIC_PUNCTUATION_FOR_PHILIPPINE_SCRIPTS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('᜵', '᜶') // 0x1735 - 0x1736
    val BUHID_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ᝀ', 'ᝑ') // 0x1740 - 0x1751
    val BUHID_INDEPENDENT_VOWELS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ᝀ', '᝟') // 0x1740 - 0x175f
    val BUHID_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᝒ', 'ᝓ') // 0x1752 - 0x1753
    val TAGBANWA_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange =
        SymbolRange.Companion.range('ᝠ', 'ᝬ') // 0x1760 - 0x176c
    val TAGBANWA_INDEPENDENT_VOWELS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ᝠ', '᝿') // 0x1760 - 0x177f
    val TAGBANWA_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᝮ', 'ᝰ') // 0x176e - 0x1770
    val TAGBANWA_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᝲ', 'ᝳ') // 0x1772 - 0x1773
    val KHMER_CONSONANTS_TO_INDEPENDENT_VOWELS: SymbolRange = SymbolRange.Companion.range('ក', 'ឳ') // 0x1780 - 0x17b3
    val KHMER_CONSONANTS_TO_NUMERIC_SYMBOLS_FOR_DIVINATION_LORE: SymbolRange =
        SymbolRange.Companion.range('ក', '៿') // 0x1780 - 0x17ff
    val KHMER_INHERENT_VOWELS: SymbolRange = SymbolRange.Companion.range('឴', '឵') // 0x17b4 - 0x17b5
    val KHMER_INHERENT_VOWELS_TO_VARIOUS_SIGNS: SymbolRange = SymbolRange.Companion.range('឴', '៓') // 0x17b4 - 0x17d3
    val KHMER_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ិ', 'ួ') // 0x17b7 - 0x17bd
    val KHMER_TWO_PART_DEPENDENT_VOWEL_SIGNS_TO_TWO_PART_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ើ', 'ៅ') // 0x17be - 0x17c5
    val KHMER_VARIOUS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ះ', 'ៈ') // 0x17c7 - 0x17c8
    val KHMER_CONSONANT_SHIFTERS_TO_VARIOUS_SIGNS: SymbolRange =
        SymbolRange.Companion.range('៉', '៓') // 0x17c9 - 0x17d3
    val KHMER_VARIOUS_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('។', '៖') // 0x17d4 - 0x17d6
    val KHMER_VARIOUS_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('៘', '៚') // 0x17d8 - 0x17da
    val KHMER_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('០', '៩') // 0x17e0 - 0x17e9
    val KHMER_NUMERIC_SYMBOLS_FOR_DIVINATION_LORE_SUBSET: SymbolRange =
        SymbolRange.Companion.range('៰', '៹') // 0x17f0 - 0x17f9
    val MONGOLIAN_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('᠀', '᠅') // 0x1800 - 0x1805
    val MONGOLIAN_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('᠀', '᠊') // 0x1800 - 0x180a
    val MONGOLIAN_PUNCTUATION_TO_EXTENSIONS_FOR_SANSKRIT_AND_TIBETAN: SymbolRange =
        SymbolRange.Companion.range('᠀', '᢯') // 0x1800 - 0x18af
    val MONGOLIAN_PUNCTUATION_SUBSET_1: SymbolRange = SymbolRange.Companion.range('᠇', '᠊') // 0x1807 - 0x180a
    val MONGOLIAN_FORMAT_CONTROLS_SUBSET: SymbolRange = SymbolRange.Companion.range('᠋', '᠍') // 0x180b - 0x180d
    val MONGOLIAN_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('᠐', '᠙') // 0x1810 - 0x1819
    val MONGOLIAN_BASIC_LETTERS: SymbolRange = SymbolRange.Companion.range('ᠠ', 'ᡂ') // 0x1820 - 0x1842
    val MONGOLIAN_BASIC_LETTERS_TO_MANCHU_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ᠠ', 'ᡸ') // 0x1820 - 0x1878
    val MONGOLIAN_TODO_LETTERS_TO_MANCHU_LETTERS: SymbolRange = SymbolRange.Companion.range('ᡄ', 'ᡸ') // 0x1844 - 0x1878
    val MONGOLIAN_EXTENSIONS_FOR_SANSKRIT_AND_TIBETAN_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ᢀ', 'ᢄ') // 0x1880 - 0x1884
    val MONGOLIAN_EXTENSIONS_FOR_SANSKRIT_AND_TIBETAN_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ᢅ', 'ᢆ') // 0x1885 - 0x1886
    val MONGOLIAN_EXTENSIONS_FOR_SANSKRIT_AND_TIBETAN_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('ᢇ', 'ᢨ') // 0x1887 - 0x18a8
    val UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS_EXTENDED_SYLLABLES_FOR_MOOSE_CREE_TO_FINALS_FOR_DENE_AND_CARRIER: SymbolRange =
        SymbolRange.Companion.range('ᢰ', 'ᣵ') // 0x18b0 - 0x18f5
    val LIMBU_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᤀ', 'ᤞ') // 0x1900 - 0x191e
    val LIMBU_CONSONANTS_TO_DIGITS: SymbolRange = SymbolRange.Companion.range('ᤀ', '᥏') // 0x1900 - 0x194f
    val LIMBU_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᤠ', 'ᤢ') // 0x1920 - 0x1922
    val LIMBU_DEPENDENT_VOWEL_SIGNS_TO_SUBJOINED_CONSONANTS: SymbolRange =
        SymbolRange.Companion.range('ᤠ', 'ᤫ') // 0x1920 - 0x192b
    val LIMBU_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ᤣ', 'ᤦ') // 0x1923 - 0x1926
    val LIMBU_DEPENDENT_VOWEL_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ᤧ', 'ᤨ') // 0x1927 - 0x1928
    val LIMBU_SUBJOINED_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᤩ', 'ᤫ') // 0x1929 - 0x192b
    val LIMBU_FINAL_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᤰ', 'ᤱ') // 0x1930 - 0x1931
    val LIMBU_FINAL_CONSONANTS_TO_VARIOUS_SIGNS: SymbolRange = SymbolRange.Companion.range('ᤰ', '᤻') // 0x1930 - 0x193b
    val LIMBU_FINAL_CONSONANTS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ᤳ', 'ᤸ') // 0x1933 - 0x1938
    val LIMBU_VARIOUS_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('᤹', '᤻') // 0x1939 - 0x193b
    val LIMBU_VARIOUS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('᥄', '᥅') // 0x1944 - 0x1945
    val LIMBU_DIGITS: SymbolRange = SymbolRange.Companion.range('᥆', '᥏') // 0x1946 - 0x194f
    val TAI_LE_CONSONANTS_TO_VOWELS: SymbolRange = SymbolRange.Companion.range('ᥐ', 'ᥭ') // 0x1950 - 0x196d
    val TAI_LE_CONSONANTS_TO_TONE_LETTERS: SymbolRange = SymbolRange.Companion.range('ᥐ', '᥿') // 0x1950 - 0x197f
    val TAI_LE_TONE_LETTERS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᥰ', 'ᥴ') // 0x1970 - 0x1974
    val NEW_TAI_LUE_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᦀ', 'ᦫ') // 0x1980 - 0x19ab
    val NEW_TAI_LUE_VOWEL_SIGNS_TO_TONE_MARKS: SymbolRange = SymbolRange.Companion.range('ᦰ', 'ᧉ') // 0x19b0 - 0x19c9
    val NEW_TAI_LUE_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('᧐', '᧚') // 0x19d0 - 0x19da
    val NEW_TAI_LUE_DIGITS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('᧐', '᧙') // 0x19d0 - 0x19d9
    val NEW_TAI_LUE_TO_KHMER_SYMBOLS: SymbolRange = SymbolRange.Companion.range('᧞', '᧿') // 0x19de - 0x19ff
    val KHMER_SYMBOLS_LUNAR_DATE_SYMBOLS: SymbolRange = SymbolRange.Companion.range('᧠', '᧿') // 0x19e0 - 0x19ff
    val BUGINESE_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ᨀ', 'ᨖ') // 0x1a00 - 0x1a16
    val BUGINESE_VOWELS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᨗ', 'ᨘ') // 0x1a17 - 0x1a18
    val BUGINESE_VOWELS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ᨗ', 'ᨛ') // 0x1a17 - 0x1a1b
    val BUGINESE_VOWELS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ᨙ', 'ᨚ') // 0x1a19 - 0x1a1a
    val BUGINESE_VARIOUS_SIGNS: SymbolRange = SymbolRange.Companion.range('᨞', '᨟') // 0x1a1e - 0x1a1f
    val TAI_THAM_CONSONANTS_TO_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ᨠ', 'ᩔ') // 0x1a20 - 0x1a54
    val TAI_THAM_CONSONANT_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᩕ', 'ᩞ') // 0x1a55 - 0x1a5e
    val TAI_THAM_CONSONANT_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ᩘ', 'ᩞ') // 0x1a58 - 0x1a5e
    val TAI_THAM_SIGN_TO_OTHER_MARKS: SymbolRange = SymbolRange.Companion.range('᩠', '᩼') // 0x1a60 - 0x1a7c
    val TAI_THAM_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᩣ', 'ᩤ') // 0x1a63 - 0x1a64
    val TAI_THAM_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ᩥ', 'ᩬ') // 0x1a65 - 0x1a6c
    val TAI_THAM_DEPENDENT_VOWEL_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ᩭ', 'ᩲ') // 0x1a6d - 0x1a72
    val TAI_THAM_DEPENDENT_VOWEL_SIGNS_TO_OTHER_MARKS: SymbolRange =
        SymbolRange.Companion.range('ᩳ', '᩼') // 0x1a73 - 0x1a7c
    val TAI_THAM_HORA_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('᪀', '᪉') // 0x1a80 - 0x1a89
    val TAI_THAM_THAM_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('᪐', '᪙') // 0x1a90 - 0x1a99
    val TAI_THAM_LOGOGRAPHS_TO_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('᪠', '᪦') // 0x1aa0 - 0x1aa6
    val TAI_THAM_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('᪨', '᪭') // 0x1aa8 - 0x1aad
    val COMBINING_DIACRITICAL_MARKS_EXTENDED_USED_IN_GERMAN_DIALECTOLOGY_TO_MARKS_SURROUNDING_OTHER_DIACRITICS_OR_LETTERS: SymbolRange =
        SymbolRange.Companion.range('᪰', '᪽') // 0x1ab0 - 0x1abd
    val COMBINING_DIACRITICAL_MARKS_EXTENDED_USED_IN_GERMAN_DIALECTOLOGY_TO_USED_FOR_SCOTS_DIALECTOLOGY: SymbolRange =
        SymbolRange.Companion.range('᪰', 'ᫀ') // 0x1ab0 - 0x1ac0
    val COMBINING_DIACRITICAL_MARKS_EXTENDED_USED_FOR_SCOTS_DIALECTOLOGY: SymbolRange =
        SymbolRange.Companion.range('ᪿ', 'ᫀ') // 0x1abf - 0x1ac0
    val BALINESE_VARIOUS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᬀ', 'ᬃ') // 0x1b00 - 0x1b03
    val BALINESE_VARIOUS_SIGNS: SymbolRange = SymbolRange.Companion.range('ᬀ', 'ᬄ') // 0x1b00 - 0x1b04
    val BALINESE_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange =
        SymbolRange.Companion.range('ᬅ', 'ᬳ') // 0x1b05 - 0x1b33
    val BALINESE_SIGN_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange = SymbolRange.Companion.range('᬴', '᭄') // 0x1b34 - 0x1b44
    val BALINESE_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᬶ', 'ᬺ') // 0x1b36 - 0x1b3a
    val BALINESE_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ᬽ', 'ᭁ') // 0x1b3d - 0x1b41
    val BALINESE_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ᭃ', '᭄') // 0x1b43 - 0x1b44
    val BALINESE_ADDITIONAL_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᭅ', 'ᭋ') // 0x1b45 - 0x1b4b
    val BALINESE_DIGITS: SymbolRange = SymbolRange.Companion.range('᭐', '᭙') // 0x1b50 - 0x1b59
    val BALINESE_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('᭚', '᭠') // 0x1b5a - 0x1b60
    val BALINESE_MUSICAL_SYMBOLS_FOR_NOTES: SymbolRange = SymbolRange.Companion.range('᭡', '᭪') // 0x1b61 - 0x1b6a
    val BALINESE_DIACRITICAL_MARKS_FOR_MUSICAL_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('᭫', '᭳') // 0x1b6b - 0x1b73
    val BALINESE_MUSICAL_SYMBOLS: SymbolRange = SymbolRange.Companion.range('᭴', '᭼') // 0x1b74 - 0x1b7c
    val SUNDANESE_VARIOUS_SIGNS: SymbolRange = SymbolRange.Companion.range('ᮀ', 'ᮂ') // 0x1b80 - 0x1b82
    val SUNDANESE_VARIOUS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᮀ', 'ᮁ') // 0x1b80 - 0x1b81
    val SUNDANESE_VOWELS_TO_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ᮃ', 'ᮠ') // 0x1b83 - 0x1ba0
    val SUNDANESE_CONSONANT_SIGNS_TO_CONSONANT_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ᮡ', 'ᮭ') // 0x1ba1 - 0x1bad
    val SUNDANESE_CONSONANT_SIGNS_TO_VOWEL_SIGNS: SymbolRange = SymbolRange.Companion.range('ᮢ', 'ᮥ') // 0x1ba2 - 0x1ba5
    val SUNDANESE_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᮦ', 'ᮧ') // 0x1ba6 - 0x1ba7
    val SUNDANESE_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ᮨ', 'ᮩ') // 0x1ba8 - 0x1ba9
    val SUNDANESE_VIRAMAS_TO_CONSONANT_SIGNS: SymbolRange = SymbolRange.Companion.range('᮫', 'ᮭ') // 0x1bab - 0x1bad
    val SUNDANESE_ADDITIONAL_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ᮮ', 'ᮯ') // 0x1bae - 0x1baf
    val SUNDANESE_DIGITS: SymbolRange = SymbolRange.Companion.range('᮰', '᮹') // 0x1bb0 - 0x1bb9
    val SUNDANESE_TO_BATAK: SymbolRange = SymbolRange.Companion.range('ᮺ', 'ᯥ') // 0x1bba - 0x1be5
    val BATAK_SIGN_TO_SIGNS: SymbolRange = SymbolRange.Companion.range('᯦', '᯳') // 0x1be6 - 0x1bf3
    val BATAK_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᯨ', 'ᯩ') // 0x1be8 - 0x1be9
    val BATAK_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ᯪ', 'ᯬ') // 0x1bea - 0x1bec
    val BATAK_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_CONSONANT_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ᯯ', 'ᯱ') // 0x1bef - 0x1bf1
    val BATAK_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('᯲', '᯳') // 0x1bf2 - 0x1bf3
    val BATAK_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('᯼', '᯿') // 0x1bfc - 0x1bff
    val LEPCHA_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ᰀ', 'ᰣ') // 0x1c00 - 0x1c23
    val LEPCHA_SUBJOINED_CONSONANTS_TO_DEPENDENT_VOWELS: SymbolRange =
        SymbolRange.Companion.range('ᰤ', 'ᰫ') // 0x1c24 - 0x1c2b
    val LEPCHA_SUBJOINED_CONSONANTS_TO_VARIOUS_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ᰤ', '᰷') // 0x1c24 - 0x1c37
    val LEPCHA_DEPENDENT_VOWELS_TO_CONSONANT_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ᰬ', 'ᰳ') // 0x1c2c - 0x1c33
    val LEPCHA_CONSONANT_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᰴ', 'ᰵ') // 0x1c34 - 0x1c35
    val LEPCHA_VARIOUS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᰶ', '᰷') // 0x1c36 - 0x1c37
    val LEPCHA_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('᰻', '᰿') // 0x1c3b - 0x1c3f
    val LEPCHA_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('᱀', '᱉') // 0x1c40 - 0x1c49
    val LEPCHA_ADDITIONAL_LETTERS: SymbolRange = SymbolRange.Companion.range('ᱍ', 'ᱏ') // 0x1c4d - 0x1c4f
    val OL_CHIKI_DIGITS: SymbolRange = SymbolRange.Companion.range('᱐', '᱙') // 0x1c50 - 0x1c59
    val OL_CHIKI_LETTERS: SymbolRange = SymbolRange.Companion.range('ᱚ', 'ᱷ') // 0x1c5a - 0x1c77
    val OL_CHIKI_LETTERS_TO_MODIFIER_LETTERS: SymbolRange = SymbolRange.Companion.range('ᱚ', 'ᱽ') // 0x1c5a - 0x1c7d
    val OL_CHIKI_MODIFIER_LETTERS: SymbolRange = SymbolRange.Companion.range('ᱸ', 'ᱽ') // 0x1c78 - 0x1c7d
    val OL_CHIKI_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('᱾', '᱿') // 0x1c7e - 0x1c7f
    val CYRILLIC_EXTENDED_C_HISTORIC_LETTER_VARIANTS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ᲀ', 'ᲈ') // 0x1c80 - 0x1c88
    val GEORGIAN_EXTENDED_CAPITAL_LETTERS_MTAVRULI_TO_ADDITIONAL_LETTERS: SymbolRange =
        SymbolRange.Companion.range('Ა', 'Ჺ') // 0x1c90 - 0x1cba
    val GEORGIAN_EXTENDED_ADDITIONAL_LETTERS_FOR_OSSETIAN_AND_ABKHAZ: SymbolRange =
        SymbolRange.Companion.range('Ჽ', 'Ჿ') // 0x1cbd - 0x1cbf
    val SUNDANESE_SUPPLEMENT_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('᳀', '᳇') // 0x1cc0 - 0x1cc7
    val VEDIC_EXTENSIONS_TONE_MARKS_FOR_THE_SAMAVEDA: SymbolRange =
        SymbolRange.Companion.range('᳐', '᳒') // 0x1cd0 - 0x1cd2
    val VEDIC_EXTENSIONS_SIGNS_FOR_YAJURVEDIC_TO_TONE_MARKS_FOR_THE_SATAPATHABRAHMANA: SymbolRange =
        SymbolRange.Companion.range('᳔', '᳠') // 0x1cd4 - 0x1ce0
    val VEDIC_EXTENSIONS_SIGNS_FOR_YAJURVEDIC_TO_DIACRITICS_FOR_VISARGA: SymbolRange =
        SymbolRange.Companion.range('᳔', '᳨') // 0x1cd4 - 0x1ce8
    val VEDIC_EXTENSIONS_DIACRITICS_FOR_VISARGA: SymbolRange = SymbolRange.Companion.range('᳢', '᳨') // 0x1ce2 - 0x1ce8
    val VEDIC_EXTENSIONS_NASALIZATION_SIGNS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ᳩ', 'ᳬ') // 0x1ce9 - 0x1cec
    val VEDIC_EXTENSIONS_NASALIZATION_SIGNS_TO_ARDHAVISARGA: SymbolRange =
        SymbolRange.Companion.range('ᳮ', 'ᳳ') // 0x1cee - 0x1cf3
    val VEDIC_EXTENSIONS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ᳵ', 'ᳶ') // 0x1cf5 - 0x1cf6
    val VEDIC_EXTENSIONS_SIGNS_TO_SIGNS_FOR_JAIMINIYA_SAMA_VEDA: SymbolRange =
        SymbolRange.Companion.range('᳷', '᳹') // 0x1cf7 - 0x1cf9
    val VEDIC_EXTENSIONS_SIGNS_FOR_JAIMINIYA_SAMA_VEDA: SymbolRange =
        SymbolRange.Companion.range('᳸', '᳹') // 0x1cf8 - 0x1cf9
    val PHONETIC_EXTENSIONS_LATIN_LETTERS_TO_GREEK_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ᴀ', 'ᴫ') // 0x1d00 - 0x1d2b
    val PHONETIC_EXTENSIONS_LATIN_LETTERS_TO_OTHER_PHONETIC_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('ᴀ', 'ᵿ') // 0x1d00 - 0x1d7f
    val PHONETIC_EXTENSIONS_TO_PHONETIC_EXTENSIONS_SUPPLEMENT: SymbolRange =
        SymbolRange.Companion.range('ᴀ', 'ᶿ') // 0x1d00 - 0x1dbf
    val PHONETIC_EXTENSIONS_LATIN_SUPERSCRIPT_MODIFIER_LETTERS_TO_GREEK_SUBSCRIPT_MODIFIER_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ᴬ', 'ᵪ') // 0x1d2c - 0x1d6a
    val PHONETIC_EXTENSIONS_LATIN_LETTER_TO_LATIN_LETTERS_WITH_MIDDLE_TILDE: SymbolRange =
        SymbolRange.Companion.range('ᵫ', 'ᵷ') // 0x1d6b - 0x1d77
    val PHONETIC_EXTENSIONS_TO_PHONETIC_EXTENSIONS_SUPPLEMENT_1: SymbolRange =
        SymbolRange.Companion.range('ᵹ', 'ᶚ') // 0x1d79 - 0x1d9a
    val PHONETIC_EXTENSIONS_SUPPLEMENT_MODIFIER_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ᶛ', 'ᶿ') // 0x1d9b - 0x1dbf
    val COMBINING_DIACRITICAL_MARKS_SUPPLEMENT_USED_FOR_ANCIENT_GREEK_TO_MISCELLANEOUS_MARKS: SymbolRange =
        SymbolRange.Companion.range('᷀', '᷹') // 0x1dc0 - 0x1df9
    val COMBINING_DIACRITICAL_MARKS_SUPPLEMENT_MISCELLANEOUS_MARKS_TO_ADDITIONAL_MARKS_FOR_UPA: SymbolRange =
        SymbolRange.Companion.range('᷻', '᷿') // 0x1dfb - 0x1dff
    val LATIN_EXTENDED_ADDITIONAL_LATIN_GENERAL_USE_EXTENSIONS_TO_MEDIEVALIST_ADDITIONS: SymbolRange =
        SymbolRange.Companion.range('Ḁ', 'ỿ') // 0x1e00 - 0x1eff
    val LATIN_EXTENDED_ADDITIONAL_TO_GREEK_EXTENDED: SymbolRange =
        SymbolRange.Companion.range('Ḁ', 'ἕ') // 0x1e00 - 0x1f15
    val LATIN_EXTENDED_ADDITIONAL_LATIN_GENERAL_USE_EXTENSIONS_TO_MEDIEVALIST_ADDITIONS_1: SymbolRange =
        SymbolRange.Companion.range('ẕ', 'ẝ') // 0x1e95 - 0x1e9d
    val LATIN_EXTENDED_ADDITIONAL_TO_GREEK_EXTENDED_1: SymbolRange =
        SymbolRange.Companion.range('ỿ', 'ἇ') // 0x1eff - 0x1f07
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK: SymbolRange =
        SymbolRange.Companion.range('ἀ', '῿') // 0x1f00 - 0x1fff
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_7: SymbolRange =
        SymbolRange.Companion.range('Ἀ', 'Ἇ') // 0x1f08 - 0x1f0f
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_14: SymbolRange =
        SymbolRange.Companion.range('ἐ', 'ἕ') // 0x1f10 - 0x1f15
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_19: SymbolRange =
        SymbolRange.Companion.range('Ἐ', 'Ἕ') // 0x1f18 - 0x1f1d
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_22: SymbolRange =
        SymbolRange.Companion.range('ἠ', 'ἧ') // 0x1f20 - 0x1f27
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_27: SymbolRange =
        SymbolRange.Companion.range('ἠ', 'ὅ') // 0x1f20 - 0x1f45
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_28: SymbolRange =
        SymbolRange.Companion.range('Ἠ', 'Ἧ') // 0x1f28 - 0x1f2f
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_33: SymbolRange =
        SymbolRange.Companion.range('ἰ', 'ἷ') // 0x1f30 - 0x1f37
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_43: SymbolRange =
        SymbolRange.Companion.range('Ἰ', 'Ἷ') // 0x1f38 - 0x1f3f
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ὀ', 'ὅ') // 0x1f40 - 0x1f45
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_9: SymbolRange =
        SymbolRange.Companion.range('Ὀ', 'Ὅ') // 0x1f48 - 0x1f4d
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_12: SymbolRange =
        SymbolRange.Companion.range('ὐ', 'ὗ') // 0x1f50 - 0x1f57
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_21: SymbolRange =
        SymbolRange.Companion.range('Ὗ', 'ώ') // 0x1f5f - 0x1f7d
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_23: SymbolRange =
        SymbolRange.Companion.range('ὠ', 'ὧ') // 0x1f60 - 0x1f67
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_29: SymbolRange =
        SymbolRange.Companion.range('Ὠ', 'Ὧ') // 0x1f68 - 0x1f6f
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_35: SymbolRange =
        SymbolRange.Companion.range('ὰ', 'ώ') // 0x1f70 - 0x1f7d
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ᾀ', 'ᾇ') // 0x1f80 - 0x1f87
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('ᾀ', 'ᾴ') // 0x1f80 - 0x1fb4
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_8: SymbolRange =
        SymbolRange.Companion.range('ᾈ', 'ᾏ') // 0x1f88 - 0x1f8f
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_13: SymbolRange =
        SymbolRange.Companion.range('ᾐ', 'ᾗ') // 0x1f90 - 0x1f97
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_18: SymbolRange =
        SymbolRange.Companion.range('ᾘ', 'ᾟ') // 0x1f98 - 0x1f9f
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_24: SymbolRange =
        SymbolRange.Companion.range('ᾠ', 'ᾧ') // 0x1fa0 - 0x1fa7
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_30: SymbolRange =
        SymbolRange.Companion.range('ᾨ', 'ᾯ') // 0x1fa8 - 0x1faf
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_34: SymbolRange =
        SymbolRange.Companion.range('ᾰ', 'ᾴ') // 0x1fb0 - 0x1fb4
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_37: SymbolRange =
        SymbolRange.Companion.range('ᾶ', 'ᾷ') // 0x1fb6 - 0x1fb7
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_39: SymbolRange =
        SymbolRange.Companion.range('ᾶ', 'ᾼ') // 0x1fb6 - 0x1fbc
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_41: SymbolRange =
        SymbolRange.Companion.range('Ᾰ', 'Ά') // 0x1fb8 - 0x1fbb
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_45: SymbolRange =
        SymbolRange.Companion.range('᾿', '῁') // 0x1fbf - 0x1fc1
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_3: SymbolRange =
        SymbolRange.Companion.range('ῂ', 'ῄ') // 0x1fc2 - 0x1fc4
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_4: SymbolRange =
        SymbolRange.Companion.range('ῆ', 'ῇ') // 0x1fc6 - 0x1fc7
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_5: SymbolRange =
        SymbolRange.Companion.range('ῆ', 'ῌ') // 0x1fc6 - 0x1fcc
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_6: SymbolRange =
        SymbolRange.Companion.range('Ὲ', 'Ή') // 0x1fc8 - 0x1fcb
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_10: SymbolRange =
        SymbolRange.Companion.range('῍', '῏') // 0x1fcd - 0x1fcf
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_11: SymbolRange =
        SymbolRange.Companion.range('ῐ', 'ΐ') // 0x1fd0 - 0x1fd3
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_15: SymbolRange =
        SymbolRange.Companion.range('ῖ', 'ῗ') // 0x1fd6 - 0x1fd7
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_16: SymbolRange =
        SymbolRange.Companion.range('ῖ', 'Ί') // 0x1fd6 - 0x1fdb
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_17: SymbolRange =
        SymbolRange.Companion.range('Ῐ', 'Ί') // 0x1fd8 - 0x1fdb
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_20: SymbolRange =
        SymbolRange.Companion.range('῝', '῟') // 0x1fdd - 0x1fdf
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_25: SymbolRange =
        SymbolRange.Companion.range('ῠ', 'ῧ') // 0x1fe0 - 0x1fe7
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_26: SymbolRange =
        SymbolRange.Companion.range('ῠ', 'Ῥ') // 0x1fe0 - 0x1fec
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_31: SymbolRange =
        SymbolRange.Companion.range('Ῠ', 'Ῥ') // 0x1fe8 - 0x1fec
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_32: SymbolRange =
        SymbolRange.Companion.range('῭', '`') // 0x1fed - 0x1fef
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_36: SymbolRange =
        SymbolRange.Companion.range('ῲ', 'ῴ') // 0x1ff2 - 0x1ff4
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_38: SymbolRange =
        SymbolRange.Companion.range('ῶ', 'ῷ') // 0x1ff6 - 0x1ff7
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_40: SymbolRange =
        SymbolRange.Companion.range('ῶ', 'ῼ') // 0x1ff6 - 0x1ffc
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_42: SymbolRange =
        SymbolRange.Companion.range('Ὸ', 'Ώ') // 0x1ff8 - 0x1ffb
    val GREEK_EXTENDED_PRECOMPOSED_POLYTONIC_GREEK_SUBSET_44: SymbolRange =
        SymbolRange.Companion.range('´', '῾') // 0x1ffd - 0x1ffe
    val GENERAL_PUNCTUATION_SPACES: SymbolRange = SymbolRange.Companion.range(' ', ' ') // 0x2000 - 0x200a
    val GENERAL_PUNCTUATION_SPACES_TO_DEPRECATED: SymbolRange = SymbolRange.Companion.range(' ', '⁯') // 0x2000 - 0x206f
    val GENERAL_PUNCTUATION_FORMAT_CHARACTERS: SymbolRange = SymbolRange.Companion.range('​', '‏') // 0x200b - 0x200f
    val GENERAL_PUNCTUATION_DASHES: SymbolRange = SymbolRange.Companion.range('‐', '―') // 0x2010 - 0x2015
    val GENERAL_PUNCTUATION_DASHES_TO_GENERAL_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('‐', '‧') // 0x2010 - 0x2027
    val GENERAL_PUNCTUATION_GENERAL_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('‖', '‗') // 0x2016 - 0x2017
    val GENERAL_PUNCTUATION_QUOTATION_MARKS_AND_APOSTROPHE_SUBSET: SymbolRange =
        SymbolRange.Companion.range('‛', '“') // 0x201b - 0x201c
    val GENERAL_PUNCTUATION_GENERAL_PUNCTUATION_1: SymbolRange =
        SymbolRange.Companion.range('†', '‧') // 0x2020 - 0x2027
    val GENERAL_PUNCTUATION_SEPARATORS: SymbolRange = SymbolRange.Companion.range(' ', ' ') // 0x2028 - 0x2029
    val GENERAL_PUNCTUATION_FORMAT_CHARACTERS_1: SymbolRange = SymbolRange.Companion.range('‪', '‮') // 0x202a - 0x202e
    val GENERAL_PUNCTUATION_GENERAL_PUNCTUATION_2: SymbolRange =
        SymbolRange.Companion.range('‰', '‸') // 0x2030 - 0x2038
    val GENERAL_PUNCTUATION_GENERAL_PUNCTUATION_TO_GENERAL_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('‰', '⁃') // 0x2030 - 0x2043
    val GENERAL_PUNCTUATION_GENERAL_PUNCTUATION_TO_GENERAL_PUNCTUATION_1: SymbolRange =
        SymbolRange.Companion.range('※', '‾') // 0x203b - 0x203e
    val GENERAL_PUNCTUATION_GENERAL_PUNCTUATION_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('‿', '⁀') // 0x203f - 0x2040
    val GENERAL_PUNCTUATION_GENERAL_PUNCTUATION_SUBSET: SymbolRange =
        SymbolRange.Companion.range('⁁', '⁃') // 0x2041 - 0x2043
    val GENERAL_PUNCTUATION_BRACKETS_TO_GENERAL_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('⁅', '⁑') // 0x2045 - 0x2051
    val GENERAL_PUNCTUATION_DOUBLE_PUNCTUATION_FOR_VERTICAL_TEXT_TO_GENERAL_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('⁇', '⁑') // 0x2047 - 0x2051
    val GENERAL_PUNCTUATION_GENERAL_PUNCTUATION_TO_ARCHAIC_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('⁓', '⁞') // 0x2053 - 0x205e
    val GENERAL_PUNCTUATION_GENERAL_PUNCTUATION_TO_ARCHAIC_PUNCTUATION_1: SymbolRange =
        SymbolRange.Companion.range('⁕', '⁞') // 0x2055 - 0x205e
    val GENERAL_PUNCTUATION_FORMAT_CHARACTER_TO_INVISIBLE_OPERATORS: SymbolRange =
        SymbolRange.Companion.range('⁠', '⁤') // 0x2060 - 0x2064
    val GENERAL_PUNCTUATION_FORMAT_CHARACTERS_TO_DEPRECATED: SymbolRange =
        SymbolRange.Companion.range('⁦', '⁯') // 0x2066 - 0x206f
    val SUPERSCRIPTS_AND_SUBSCRIPTS_SUPERSCRIPTS_TO_SUBSCRIPTS_FOR_UPA: SymbolRange =
        SymbolRange.Companion.range('⁰', '₟') // 0x2070 - 0x209f
    val SUPERSCRIPTS_AND_SUBSCRIPTS_SUPERSCRIPTS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('⁴', '⁹') // 0x2074 - 0x2079
    val SUPERSCRIPTS_AND_SUBSCRIPTS_SUPERSCRIPTS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('⁺', '⁼') // 0x207a - 0x207c
    val SUPERSCRIPTS_AND_SUBSCRIPTS_SUPERSCRIPTS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('⁽', '⁾') // 0x207d - 0x207e
    val SUPERSCRIPTS_AND_SUBSCRIPTS_SUBSCRIPTS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('₀', '₉') // 0x2080 - 0x2089
    val SUPERSCRIPTS_AND_SUBSCRIPTS_SUBSCRIPTS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('₊', '₌') // 0x208a - 0x208c
    val SUPERSCRIPTS_AND_SUBSCRIPTS_SUBSCRIPTS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('₍', '₎') // 0x208d - 0x208e
    val SUPERSCRIPTS_AND_SUBSCRIPTS_SUBSCRIPTS_TO_SUBSCRIPTS_FOR_UPA: SymbolRange =
        SymbolRange.Companion.range('ₐ', 'ₜ') // 0x2090 - 0x209c
    val CURRENCY_SYMBOLS_CURRENCY_SYMBOLS_SUBSET: SymbolRange = SymbolRange.Companion.range('₠', '₿') // 0x20a0 - 0x20bf
    val CURRENCY_SYMBOLS_CURRENCY_SYMBOLS: SymbolRange = SymbolRange.Companion.range('₠', '⃏') // 0x20a0 - 0x20cf
    val COMBINING_DIACRITICAL_MARKS_FOR_SYMBOLS_COMBINING_DIACRITICAL_MARKS_FOR_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⃐', '⃜') // 0x20d0 - 0x20dc
    val COMBINING_DIACRITICAL_MARKS_FOR_SYMBOLS_COMBINING_DIACRITICAL_MARKS_FOR_SYMBOLS_TO_ADDITIONAL_DIACRITICAL_MARKS_FOR_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⃐', '⃰') // 0x20d0 - 0x20f0
    val COMBINING_DIACRITICAL_MARKS_FOR_SYMBOLS_ENCLOSING_DIACRITICS: SymbolRange =
        SymbolRange.Companion.range('⃝', '⃠') // 0x20dd - 0x20e0
    val COMBINING_DIACRITICAL_MARKS_FOR_SYMBOLS_ADDITIONAL_ENCLOSING_DIACRITICS: SymbolRange =
        SymbolRange.Companion.range('⃢', '⃤') // 0x20e2 - 0x20e4
    val COMBINING_DIACRITICAL_MARKS_FOR_SYMBOLS_ADDITIONAL_DIACRITICAL_MARKS_FOR_SYMBOLS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('⃥', '⃰') // 0x20e5 - 0x20f0
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('℀', '℁') // 0x2100 - 0x2101
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_TO_LOWERCASE_CLAUDIAN_LETTER: SymbolRange =
        SymbolRange.Companion.range('℀', '⅏') // 0x2100 - 0x214f
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('℃', '℆') // 0x2103 - 0x2106
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('℈', '℉') // 0x2108 - 0x2109
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_3: SymbolRange =
        SymbolRange.Companion.range('ℊ', 'ℓ') // 0x210a - 0x2113
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_4: SymbolRange =
        SymbolRange.Companion.range('ℋ', 'ℍ') // 0x210b - 0x210d
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_5: SymbolRange =
        SymbolRange.Companion.range('ℎ', 'ℏ') // 0x210e - 0x210f
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_6: SymbolRange =
        SymbolRange.Companion.range('ℐ', 'ℒ') // 0x2110 - 0x2112
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_7: SymbolRange =
        SymbolRange.Companion.range('№', '℗') // 0x2116 - 0x2117
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_8: SymbolRange =
        SymbolRange.Companion.range('№', '℘') // 0x2116 - 0x2118
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_9: SymbolRange =
        SymbolRange.Companion.range('ℙ', 'ℝ') // 0x2119 - 0x211d
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_10: SymbolRange =
        SymbolRange.Companion.range('℞', '℣') // 0x211e - 0x2123
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_11: SymbolRange =
        SymbolRange.Companion.range('K', 'ℭ') // 0x212a - 0x212d
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_TO_HEBREW_LETTERLIKE_MATH_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('ℯ', 'ℹ') // 0x212f - 0x2139
    val LETTERLIKE_SYMBOLS_LETTERLIKE_SYMBOLS_SUBSET_12: SymbolRange =
        SymbolRange.Companion.range('ℰ', 'ℳ') // 0x2130 - 0x2133
    val LETTERLIKE_SYMBOLS_HEBREW_LETTERLIKE_MATH_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('ℵ', 'ℸ') // 0x2135 - 0x2138
    val LETTERLIKE_SYMBOLS_ADDITIONAL_LETTERLIKE_SYMBOLS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('℺', '℻') // 0x213a - 0x213b
    val LETTERLIKE_SYMBOLS_ADDITIONAL_LETTERLIKE_SYMBOLS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('ℼ', 'ℽ') // 0x213c - 0x213d
    val LETTERLIKE_SYMBOLS_ADDITIONAL_LETTERLIKE_SYMBOLS_SUBSET_3: SymbolRange =
        SymbolRange.Companion.range('ℼ', 'ℿ') // 0x213c - 0x213f
    val LETTERLIKE_SYMBOLS_ADDITIONAL_LETTERLIKE_SYMBOLS_SUBSET_4: SymbolRange =
        SymbolRange.Companion.range('ℾ', 'ℿ') // 0x213e - 0x213f
    val LETTERLIKE_SYMBOLS_DOUBLE_STRUCK_LARGE_OPERATOR_TO_ADDITIONAL_LETTERLIKE_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⅀', '⅄') // 0x2140 - 0x2144
    val LETTERLIKE_SYMBOLS_DOUBLE_STRUCK_ITALIC_MATH_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('ⅅ', 'ⅉ') // 0x2145 - 0x2149
    val LETTERLIKE_SYMBOLS_DOUBLE_STRUCK_ITALIC_MATH_SYMBOLS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ⅆ', 'ⅉ') // 0x2146 - 0x2149
    val LETTERLIKE_SYMBOLS_ADDITIONAL_LETTERLIKE_SYMBOLS_2: SymbolRange =
        SymbolRange.Companion.range('⅊', '⅍') // 0x214a - 0x214d
    val LETTERLIKE_SYMBOLS_ADDITIONAL_LETTERLIKE_SYMBOLS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('⅌', '⅍') // 0x214c - 0x214d
    val NUMBER_FORMS_FRACTIONS: SymbolRange = SymbolRange.Companion.range('⅐', '⅟') // 0x2150 - 0x215f
    val NUMBER_FORMS_FRACTIONS_TO_ARCHAIC_ROMAN_NUMERALS: SymbolRange =
        SymbolRange.Companion.range('⅐', 'ↂ') // 0x2150 - 0x2182
    val NUMBER_FORMS_FRACTIONS_TO_TURNED_DIGITS: SymbolRange = SymbolRange.Companion.range('⅐', '↏') // 0x2150 - 0x218f
    val NUMBER_FORMS_ROMAN_NUMERALS_TO_ARCHAIC_ROMAN_NUMERALS: SymbolRange =
        SymbolRange.Companion.range('Ⅰ', 'ↂ') // 0x2160 - 0x2182
    val NUMBER_FORMS_ARCHAIC_ROMAN_NUMERALS_TO_ARCHAIC_ROMAN_NUMERALS: SymbolRange =
        SymbolRange.Companion.range('Ↄ', 'ↄ') // 0x2183 - 0x2184
    val NUMBER_FORMS_ARCHAIC_ROMAN_NUMERALS_TO_ARCHAIC_ROMAN_NUMERALS_1: SymbolRange =
        SymbolRange.Companion.range('ↅ', '↉') // 0x2185 - 0x2189
    val NUMBER_FORMS_ARCHAIC_ROMAN_NUMERALS_1: SymbolRange = SymbolRange.Companion.range('ↅ', 'ↈ') // 0x2185 - 0x2188
    val NUMBER_FORMS_TURNED_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('↊', '↋') // 0x218a - 0x218b
    val ARROWS_SIMPLE_ARROWS_SUBSET: SymbolRange = SymbolRange.Companion.range('←', '↔') // 0x2190 - 0x2194
    val ARROWS_SIMPLE_ARROWS_TO_MISCELLANEOUS_ARROWS: SymbolRange =
        SymbolRange.Companion.range('←', '⇿') // 0x2190 - 0x21ff
    val ARROWS_TO_MISCELLANEOUS_TECHNICAL: SymbolRange = SymbolRange.Companion.range('←', '⌇') // 0x2190 - 0x2307
    val ARROWS_SIMPLE_ARROWS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('↕', '↙') // 0x2195 - 0x2199
    val ARROWS_ARROWS_WITH_MODIFICATIONS_SUBSET: SymbolRange = SymbolRange.Companion.range('↚', '↛') // 0x219a - 0x219b
    val ARROWS_ARROWS_WITH_MODIFICATIONS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('↜', '↟') // 0x219c - 0x219f
    val ARROWS_ARROWS_WITH_MODIFICATIONS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('↡', '↢') // 0x21a1 - 0x21a2
    val ARROWS_ARROWS_WITH_MODIFICATIONS_SUBSET_3: SymbolRange =
        SymbolRange.Companion.range('↤', '↥') // 0x21a4 - 0x21a5
    val ARROWS_ARROWS_WITH_MODIFICATIONS_SUBSET_4: SymbolRange =
        SymbolRange.Companion.range('↧', '↭') // 0x21a7 - 0x21ad
    val ARROWS_ARROWS_WITH_MODIFICATIONS_TO_PAIRED_ARROWS_AND_HARPOONS: SymbolRange =
        SymbolRange.Companion.range('↯', '⇍') // 0x21af - 0x21cd
    val ARROWS_DOUBLE_ARROWS_SUBSET: SymbolRange = SymbolRange.Companion.range('⇎', '⇏') // 0x21ce - 0x21cf
    val ARROWS_DOUBLE_ARROWS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('⇐', '⇑') // 0x21d0 - 0x21d1
    val ARROWS_DOUBLE_ARROWS_TO_WHITE_ARROWS_AND_KEYBOARD_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⇕', '⇳') // 0x21d5 - 0x21f3
    val ARROWS_TO_MATHEMATICAL_OPERATORS: SymbolRange = SymbolRange.Companion.range('⇴', '⋿') // 0x21f4 - 0x22ff
    val MATHEMATICAL_OPERATORS_MISCELLANEOUS_MATHEMATICAL_SYMBOLS_TO_RELATIONS: SymbolRange =
        SymbolRange.Companion.range('∀', '⋿') // 0x2200 - 0x22ff
    val MISCELLANEOUS_TECHNICAL_MISCELLANEOUS_TECHNICAL: SymbolRange =
        SymbolRange.Companion.range('⌀', '⌇') // 0x2300 - 0x2307
    val MISCELLANEOUS_TECHNICAL_MISCELLANEOUS_TECHNICAL_TO_POWER_SYMBOL_FROM_IEEE_1621_2004: SymbolRange =
        SymbolRange.Companion.range('⌀', '⏿') // 0x2300 - 0x23ff
    val MISCELLANEOUS_TECHNICAL_CEILINGS_AND_FLOORS: SymbolRange =
        SymbolRange.Companion.range('⌈', '⌋') // 0x2308 - 0x230b
    val MISCELLANEOUS_TECHNICAL_CROPS_TO_QUINE_CORNERS: SymbolRange =
        SymbolRange.Companion.range('⌌', '⌟') // 0x230c - 0x231f
    val MISCELLANEOUS_TECHNICAL_CROPS_TO_KEYBOARD_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⌌', '⌨') // 0x230c - 0x2328
    val MISCELLANEOUS_TECHNICAL_INTEGRAL_PIECES: SymbolRange = SymbolRange.Companion.range('⌠', '⌡') // 0x2320 - 0x2321
    val MISCELLANEOUS_TECHNICAL_FROWN_AND_SMILE_TO_KEYBOARD_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⌢', '⌨') // 0x2322 - 0x2328
    val MISCELLANEOUS_TECHNICAL_DEPRECATED_ANGLE_BRACKETS: SymbolRange =
        SymbolRange.Companion.range('〈', '〉') // 0x2329 - 0x232a
    val MISCELLANEOUS_TECHNICAL_KEYBOARD_SYMBOL_TO_APL: SymbolRange =
        SymbolRange.Companion.range('⌫', '⍻') // 0x232b - 0x237b
    val MISCELLANEOUS_TECHNICAL_TO_CONTROL_PICTURES_1: SymbolRange =
        SymbolRange.Companion.range('⌫', '␦') // 0x232b - 0x2426
    val MISCELLANEOUS_TECHNICAL_GRAPHICS_FOR_CONTROL_CODES_TO_KEYBOARD_SYMBOLS_FROM_ISO_9995_7: SymbolRange =
        SymbolRange.Companion.range('⍽', '⎚') // 0x237d - 0x239a
    val MISCELLANEOUS_TECHNICAL_BRACKET_PIECES_TO_SUMMATION_SIGN_PARTS: SymbolRange =
        SymbolRange.Companion.range('⎛', '⎳') // 0x239b - 0x23b3
    val MISCELLANEOUS_TECHNICAL_HORIZONTAL_BRACKETS_TO_ELECTROTECHNICAL_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⎴', '⏛') // 0x23b4 - 0x23db
    val MISCELLANEOUS_TECHNICAL_HORIZONTAL_BRACKETS_1: SymbolRange =
        SymbolRange.Companion.range('⏜', '⏡') // 0x23dc - 0x23e1
    val MISCELLANEOUS_TECHNICAL_TO_CONTROL_PICTURES: SymbolRange =
        SymbolRange.Companion.range('⏢', '␦') // 0x23e2 - 0x2426
    val CONTROL_PICTURES_GRAPHIC_PICTURES_FOR_CONTROL_CODES_TO_SPECIFIC_SYMBOL_FOR_CONTROL_CODE: SymbolRange =
        SymbolRange.Companion.range('␀', '␿') // 0x2400 - 0x243f
    val OPTICAL_CHARACTER_RECOGNITION_OCR_A_TO_MICR: SymbolRange =
        SymbolRange.Companion.range('⑀', '⑊') // 0x2440 - 0x244a
    val OPTICAL_CHARACTER_RECOGNITION_OCR_A_TO_OCR: SymbolRange =
        SymbolRange.Companion.range('⑀', '⑟') // 0x2440 - 0x245f
    val ENCLOSED_ALPHANUMERICS_CIRCLED_NUMBERS_TO_NUMBERS_PERIOD: SymbolRange =
        SymbolRange.Companion.range('①', '⒛') // 0x2460 - 0x249b
    val ENCLOSED_ALPHANUMERICS_CIRCLED_NUMBERS_TO_DOUBLE_CIRCLED_NUMBERS: SymbolRange =
        SymbolRange.Companion.range('①', '⓿') // 0x2460 - 0x24ff
    val ENCLOSED_ALPHANUMERICS_PARENTHESIZED_LATIN_LETTERS_TO_CIRCLED_LATIN_LETTERS: SymbolRange =
        SymbolRange.Companion.range('⒜', 'ⓩ') // 0x249c - 0x24e9
    val ENCLOSED_ALPHANUMERICS_ADDITIONAL_CIRCLED_NUMBER_TO_DOUBLE_CIRCLED_NUMBERS: SymbolRange =
        SymbolRange.Companion.range('⓪', '⓿') // 0x24ea - 0x24ff
    val BOX_DRAWING_LIGHT_AND_HEAVY_SOLID_LINES_TO_MIXED_LIGHT_AND_HEAVY_LINES: SymbolRange =
        SymbolRange.Companion.range('─', '╿') // 0x2500 - 0x257f
    val BOX_DRAWING_TO_GEOMETRIC_SHAPES: SymbolRange = SymbolRange.Companion.range('─', '▶') // 0x2500 - 0x25b6
    val BOX_DRAWING_TO_DINGBATS: SymbolRange = SymbolRange.Companion.range('─', '❧') // 0x2500 - 0x2767
    val BLOCK_ELEMENTS_BLOCK_ELEMENTS_TO_TERMINAL_GRAPHIC_CHARACTERS: SymbolRange =
        SymbolRange.Companion.range('▀', '▟') // 0x2580 - 0x259f
    val GEOMETRIC_SHAPES_GEOMETRIC_SHAPES_TO_GEOMETRIC_SHAPES: SymbolRange =
        SymbolRange.Companion.range('■', '◿') // 0x25a0 - 0x25ff
    val GEOMETRIC_SHAPES_GEOMETRIC_SHAPES_SUBSET: SymbolRange = SymbolRange.Companion.range('▸', '◀') // 0x25b8 - 0x25c0
    val GEOMETRIC_SHAPES_GEOMETRIC_SHAPES_TO_CONTROL_CODE_GRAPHICS: SymbolRange =
        SymbolRange.Companion.range('◂', '◷') // 0x25c2 - 0x25f7
    val GEOMETRIC_SHAPES_GEOMETRIC_SHAPES_1: SymbolRange = SymbolRange.Companion.range('◸', '◿') // 0x25f8 - 0x25ff
    val MISCELLANEOUS_SYMBOLS_WEATHER_AND_ASTROLOGICAL_SYMBOLS_TO_MUSICAL_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('☀', '♮') // 0x2600 - 0x266e
    val MISCELLANEOUS_SYMBOLS_WEATHER_AND_ASTROLOGICAL_SYMBOLS_TO_MAP_SYMBOLS_FROM_ARIB_STD_B24: SymbolRange =
        SymbolRange.Companion.range('☀', '⛿') // 0x2600 - 0x26ff
    val MISCELLANEOUS_SYMBOLS_TO_DINGBATS: SymbolRange = SymbolRange.Companion.range('♰', '❧') // 0x2670 - 0x2767
    val DINGBATS_MISCELLANEOUS_TO_DINGBAT_ARROWS: SymbolRange = SymbolRange.Companion.range('✀', '➿') // 0x2700 - 0x27bf
    val DINGBATS_ORNAMENTAL_BRACKETS: SymbolRange = SymbolRange.Companion.range('❨', '❵') // 0x2768 - 0x2775
    val DINGBATS_DINGBAT_CIRCLED_DIGITS: SymbolRange = SymbolRange.Companion.range('❶', '➓') // 0x2776 - 0x2793
    val DINGBATS_DINGBAT_ARROW_TO_DINGBAT_ARROWS: SymbolRange = SymbolRange.Companion.range('➔', '➿') // 0x2794 - 0x27bf
    val DINGBATS_TO_MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A: SymbolRange =
        SymbolRange.Companion.range('➔', '⟄') // 0x2794 - 0x27c4
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A_MISCELLANEOUS_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⟀', '⟄') // 0x27c0 - 0x27c4
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A_PAIRED_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('⟅', '⟆') // 0x27c5 - 0x27c6
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A_OPERATOR_TO_MODAL_LOGIC_OPERATORS: SymbolRange =
        SymbolRange.Companion.range('⟇', '⟥') // 0x27c7 - 0x27e5
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_A_MATHEMATICAL_BRACKETS: SymbolRange =
        SymbolRange.Companion.range('⟦', '⟯') // 0x27e6 - 0x27ef
    val SUPPLEMENTAL_ARROWS_A_ARROWS_TO_LONG_ARROWS: SymbolRange =
        SymbolRange.Companion.range('⟰', '⟿') // 0x27f0 - 0x27ff
    val SUPPLEMENTAL_ARROWS_A_TO_MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B: SymbolRange =
        SymbolRange.Companion.range('⟰', '⦂') // 0x27f0 - 0x2982
    val BRAILLE_PATTERNS_BRAILLE_PATTERNS: SymbolRange = SymbolRange.Companion.range('⠀', '⣿') // 0x2800 - 0x28ff
    val SUPPLEMENTAL_ARROWS_B_TO_MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B: SymbolRange =
        SymbolRange.Companion.range('⤀', '⦂') // 0x2900 - 0x2982
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_BRACKETS_TO_BRACKETS: SymbolRange =
        SymbolRange.Companion.range('⦃', '⦘') // 0x2983 - 0x2998
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_FENCES_TO_BOWTIE_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⦙', '⧗') // 0x2999 - 0x29d7
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_FENCES_1: SymbolRange =
        SymbolRange.Companion.range('⧘', '⧛') // 0x29d8 - 0x29db
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_MISCELLANEOUS_MATHEMATICAL_SYMBOLS_TO_SPECIALIZED_PLUS_SIGN_OPERATORS: SymbolRange =
        SymbolRange.Companion.range('⧜', '⧻') // 0x29dc - 0x29fb
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_BRACKETS_2: SymbolRange =
        SymbolRange.Companion.range('⧼', '⧽') // 0x29fc - 0x29fd
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_TO_SUPPLEMENTAL_MATHEMATICAL_OPERATORS: SymbolRange =
        SymbolRange.Companion.range('⧾', '⫿') // 0x29fe - 0x2aff
    val MISCELLANEOUS_MATHEMATICAL_SYMBOLS_B_TO_MISCELLANEOUS_SYMBOLS_AND_ARROWS: SymbolRange =
        SymbolRange.Companion.range('⧾', '⭳') // 0x29fe - 0x2b73
    val SUPPLEMENTAL_MATHEMATICAL_OPERATORS_N_ARY_OPERATORS_TO_OPERATORS: SymbolRange =
        SymbolRange.Companion.range('⨀', '⫿') // 0x2a00 - 0x2aff
    val MISCELLANEOUS_SYMBOLS_AND_ARROWS_WHITE_AND_BLACK_ARROWS_TO_ELLIPSES: SymbolRange =
        SymbolRange.Companion.range('⬀', '⬯') // 0x2b00 - 0x2b2f
    val MISCELLANEOUS_SYMBOLS_AND_ARROWS_WHITE_AND_BLACK_ARROWS_TO_SYMBOLS_USED_IN_CHESS_NOTATION: SymbolRange =
        SymbolRange.Companion.range('⬀', '⯿') // 0x2b00 - 0x2bff
    val MISCELLANEOUS_SYMBOLS_AND_ARROWS_MATHEMATICAL_ARROWS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('⬰', '⭄') // 0x2b30 - 0x2b44
    val MISCELLANEOUS_SYMBOLS_AND_ARROWS_MATHEMATICAL_ARROWS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('⭅', '⭆') // 0x2b45 - 0x2b46
    val MISCELLANEOUS_SYMBOLS_AND_ARROWS_MATHEMATICAL_ARROWS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('⭇', '⭌') // 0x2b47 - 0x2b4c
    val MISCELLANEOUS_SYMBOLS_AND_ARROWS_MISCELLANEOUS_ARROW_TO_TRIANGLE_HEADED_ARROWS: SymbolRange =
        SymbolRange.Companion.range('⭍', '⭳') // 0x2b4d - 0x2b73
    val MISCELLANEOUS_SYMBOLS_AND_ARROWS_TRIANGLE_HEADED_ARROWS_TO_MISCELLANEOUS_ARROW_SYMBOL: SymbolRange =
        SymbolRange.Companion.range('⭶', '⮕') // 0x2b76 - 0x2b95
    val MISCELLANEOUS_SYMBOLS_AND_ARROWS_MISCELLANEOUS_SYMBOL_TO_SYMBOLS_USED_IN_CHESS_NOTATION: SymbolRange =
        SymbolRange.Companion.range('⮗', '⯿') // 0x2b97 - 0x2bff
    val GLAGOLITIC_CAPITAL_LETTERS_SUBSET: SymbolRange = SymbolRange.Companion.range('Ⰰ', 'Ⱞ') // 0x2c00 - 0x2c2e
    val GLAGOLITIC_SMALL_LETTERS_SUBSET: SymbolRange = SymbolRange.Companion.range('ⰰ', 'ⱞ') // 0x2c30 - 0x2c5e
    val LATIN_EXTENDED_C_TO_COPTIC: SymbolRange = SymbolRange.Companion.range('Ⱡ', 'ⳤ') // 0x2c60 - 0x2ce4
    val LATIN_EXTENDED_C_ORTHOGRAPHIC_LATIN_ADDITIONS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('Ɫ', 'Ɽ') // 0x2c62 - 0x2c64
    val LATIN_EXTENDED_C_ORTHOGRAPHIC_LATIN_ADDITIONS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ⱥ', 'ⱦ') // 0x2c65 - 0x2c66
    val LATIN_EXTENDED_C_MISCELLANEOUS_ADDITIONS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('Ɑ', 'Ɒ') // 0x2c6d - 0x2c70
    val LATIN_EXTENDED_C_MISCELLANEOUS_ADDITIONS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ⱳ', 'ⱴ') // 0x2c73 - 0x2c74
    val LATIN_EXTENDED_C_CLAUDIAN_LETTERS_TO_ADDITIONS_FOR_UPA: SymbolRange =
        SymbolRange.Companion.range('ⱶ', 'ⱻ') // 0x2c76 - 0x2c7b
    val LATIN_EXTENDED_C_ADDITIONS_FOR_UPA_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ⱼ', 'ⱽ') // 0x2c7c - 0x2c7d
    val LATIN_EXTENDED_C_ADDITIONS_FOR_SHONA_TO_ADDITIONS_FOR_SHONA: SymbolRange =
        SymbolRange.Companion.range('Ȿ', 'Ⲁ') // 0x2c7e - 0x2c80
    val COPTIC_OLD_NUBIAN_LETTERS_TO_OLD_NUBIAN_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ⳣ', 'ⳤ') // 0x2ce3 - 0x2ce4
    val COPTIC_SYMBOLS_SUBSET: SymbolRange = SymbolRange.Companion.range('⳥', '⳪') // 0x2ce5 - 0x2cea
    val COPTIC_CRYPTOGRAMMIC_LETTERS: SymbolRange = SymbolRange.Companion.range('Ⳬ', 'ⳮ') // 0x2ceb - 0x2cee
    val COPTIC_COMBINING_MARKS: SymbolRange = SymbolRange.Companion.range('⳯', '⳱') // 0x2cef - 0x2cf1
    val COPTIC_BOHAIRIC_COPTIC_LETTERS_SUBSET: SymbolRange = SymbolRange.Companion.range('Ⳳ', 'ⳳ') // 0x2cf2 - 0x2cf3
    val COPTIC_OLD_NUBIAN_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('⳹', '⳼') // 0x2cf9 - 0x2cfc
    val COPTIC_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('⳾', '⳿') // 0x2cfe - 0x2cff
    val GEORGIAN_SUPPLEMENT_SMALL_LETTERS_KHUTSURI_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ⴀ', 'ⴥ') // 0x2d00 - 0x2d25
    val TIFINAGH_LETTERS_SUBSET: SymbolRange = SymbolRange.Companion.range('ⴰ', 'ⵧ') // 0x2d30 - 0x2d67
    val ETHIOPIC_EXTENDED_SYLLABLES_FOR_MEEN_TO_SYLLABLES_FOR_BLIN: SymbolRange =
        SymbolRange.Companion.range('ⶀ', 'ⶖ') // 0x2d80 - 0x2d96
    val ETHIOPIC_EXTENDED_SYLLABLES_FOR_BENCH_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ⶠ', 'ⶦ') // 0x2da0 - 0x2da6
    val ETHIOPIC_EXTENDED_SYLLABLES_FOR_BENCH_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ⶨ', 'ⶮ') // 0x2da8 - 0x2dae
    val ETHIOPIC_EXTENDED_SYLLABLES_FOR_BENCH_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('ⶰ', 'ⶶ') // 0x2db0 - 0x2db6
    val ETHIOPIC_EXTENDED_SYLLABLES_FOR_BENCH_SUBSET_3: SymbolRange =
        SymbolRange.Companion.range('ⶸ', 'ⶾ') // 0x2db8 - 0x2dbe
    val ETHIOPIC_EXTENDED_SYLLABLES_FOR_SEBATBEIT_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ⷀ', 'ⷆ') // 0x2dc0 - 0x2dc6
    val ETHIOPIC_EXTENDED_SYLLABLES_FOR_SEBATBEIT_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ⷈ', 'ⷎ') // 0x2dc8 - 0x2dce
    val ETHIOPIC_EXTENDED_SYLLABLES_FOR_SEBATBEIT_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('ⷐ', 'ⷖ') // 0x2dd0 - 0x2dd6
    val ETHIOPIC_EXTENDED_SYLLABLES_FOR_SEBATBEIT_SUBSET_3: SymbolRange =
        SymbolRange.Companion.range('ⷘ', 'ⷞ') // 0x2dd8 - 0x2dde
    val CYRILLIC_EXTENDED_A_OLD_CHURCH_SLAVONIC_COMBINING_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ⷠ', 'ⷿ') // 0x2de0 - 0x2dff
    val SUPPLEMENTAL_PUNCTUATION_NEW_TESTAMENT_EDITORIAL_SYMBOLS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('⸀', '⸁') // 0x2e00 - 0x2e01
    val SUPPLEMENTAL_PUNCTUATION_NEW_TESTAMENT_EDITORIAL_SYMBOLS_TO_HISTORIC_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('⸀', '⸮') // 0x2e00 - 0x2e2e
    val SUPPLEMENTAL_PUNCTUATION_NEW_TESTAMENT_EDITORIAL_SYMBOLS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('⸆', '⸈') // 0x2e06 - 0x2e08
    val SUPPLEMENTAL_PUNCTUATION_ANCIENT_GREEK_TEXTUAL_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⸎', '⸖') // 0x2e0e - 0x2e16
    val SUPPLEMENTAL_PUNCTUATION_GENERAL_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('⸘', '⸙') // 0x2e18 - 0x2e19
    val SUPPLEMENTAL_PUNCTUATION_DICTIONARY_PUNCTUATION_1: SymbolRange =
        SymbolRange.Companion.range('⸞', '⸟') // 0x2e1e - 0x2e1f
    val SUPPLEMENTAL_PUNCTUATION_HISTORIC_PUNCTUATION_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('⸪', '⸮') // 0x2e2a - 0x2e2e
    val SUPPLEMENTAL_PUNCTUATION_HISTORIC_PUNCTUATION_TO_PALAEOTYPE_TRANSLITERATION_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('⸰', '⸹') // 0x2e30 - 0x2e39
    val SUPPLEMENTAL_PUNCTUATION_HISTORIC_PUNCTUATION_TO_HISTORIC_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('⸰', '⹏') // 0x2e30 - 0x2e4f
    val SUPPLEMENTAL_PUNCTUATION_DASHES: SymbolRange = SymbolRange.Companion.range('⸺', '⸻') // 0x2e3a - 0x2e3b
    val SUPPLEMENTAL_PUNCTUATION_ALTERNATE_FORMS_OF_PUNCTUATION_TO_ALTERNATE_FORMS_OF_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('⸼', '⸿') // 0x2e3c - 0x2e3f
    val SUPPLEMENTAL_PUNCTUATION_MISCELLANEOUS_PUNCTUATION_TO_HISTORIC_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('⹃', '⹏') // 0x2e43 - 0x2e4f
    val SUPPLEMENTAL_PUNCTUATION_HISTORIC_PUNCTUATION_SUBSET: SymbolRange =
        SymbolRange.Companion.range('⹐', '⹑') // 0x2e50 - 0x2e51
    val CJK_RADICALS_SUPPLEMENT_CJK_RADICALS_SUPPLEMENT_SUBSET: SymbolRange =
        SymbolRange.Companion.range('⺀', '⺙') // 0x2e80 - 0x2e99
    val CJK_RADICALS_SUPPLEMENT_CJK_RADICALS_SUPPLEMENT: SymbolRange =
        SymbolRange.Companion.range('⺀', '⻿') // 0x2e80 - 0x2eff
    val CJK_RADICALS_SUPPLEMENT_CJK_RADICALS_SUPPLEMENT_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('⺛', '⻳') // 0x2e9b - 0x2ef3
    val KANGXI_RADICALS_KANGXI_RADICALS_SUBSET: SymbolRange = SymbolRange.Companion.range('⼀', '⿕') // 0x2f00 - 0x2fd5
    val KANGXI_RADICALS_KANGXI_RADICALS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('⼀', '⿟') // 0x2f00 - 0x2fdf
    val IDEOGRAPHIC_DESCRIPTION_CHARACTERS_IDEOGRAPHIC_DESCRIPTION_CHARACTERS: SymbolRange =
        SymbolRange.Companion.range('⿰', '⿿') // 0x2ff0 - 0x2fff
    val IDEOGRAPHIC_DESCRIPTION_CHARACTERS_IDEOGRAPHIC_DESCRIPTION_CHARACTERS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('⿰', '⿻') // 0x2ff0 - 0x2ffb
    val CJK_SYMBOLS_AND_PUNCTUATION_CJK_SYMBOLS_AND_PUNCTUATION_TO_SPECIAL_CJK_INDICATORS: SymbolRange =
        SymbolRange.Companion.range('　', '〿') // 0x3000 - 0x303f
    val CJK_SYMBOLS_AND_PUNCTUATION_CJK_SYMBOLS_AND_PUNCTUATION_SUBSET: SymbolRange =
        SymbolRange.Companion.range('、', '〃') // 0x3001 - 0x3003
    val CJK_SYMBOLS_AND_PUNCTUATION_CJK_SYMBOLS_AND_PUNCTUATION_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('々', '〆') // 0x3005 - 0x3006
    val CJK_SYMBOLS_AND_PUNCTUATION_CJK_ANGLE_BRACKETS_TO_CJK_BRACKETS: SymbolRange =
        SymbolRange.Companion.range('〈', '】') // 0x3008 - 0x3011
    val CJK_SYMBOLS_AND_PUNCTUATION_CJK_SYMBOLS: SymbolRange = SymbolRange.Companion.range('〒', '〓') // 0x3012 - 0x3013
    val CJK_SYMBOLS_AND_PUNCTUATION_CJK_BRACKETS_TO_CJK_PUNCTUATION: SymbolRange =
        SymbolRange.Companion.range('〔', '〟') // 0x3014 - 0x301f
    val CJK_SYMBOLS_AND_PUNCTUATION_CJK_PUNCTUATION_SUBSET: SymbolRange =
        SymbolRange.Companion.range('〞', '〟') // 0x301e - 0x301f
    val CJK_SYMBOLS_AND_PUNCTUATION_SUZHOU_NUMERALS: SymbolRange =
        SymbolRange.Companion.range('〡', '〩') // 0x3021 - 0x3029
    val CJK_SYMBOLS_AND_PUNCTUATION_COMBINING_TONE_MARKS: SymbolRange =
        SymbolRange.Companion.range('〪', '〯') // 0x302a - 0x302f
    val CJK_SYMBOLS_AND_PUNCTUATION_COMBINING_TONE_MARKS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('〪', '〭') // 0x302a - 0x302d
    val CJK_SYMBOLS_AND_PUNCTUATION_COMBINING_TONE_MARKS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('〮', '〯') // 0x302e - 0x302f
    val CJK_SYMBOLS_AND_PUNCTUATION_KANA_REPEAT_MARKS: SymbolRange =
        SymbolRange.Companion.range('〱', '〵') // 0x3031 - 0x3035
    val CJK_SYMBOLS_AND_PUNCTUATION_OTHER_CJK_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('〶', '〷') // 0x3036 - 0x3037
    val CJK_SYMBOLS_AND_PUNCTUATION_ADDITIONAL_SUZHOU_NUMERALS: SymbolRange =
        SymbolRange.Companion.range('〸', '〺') // 0x3038 - 0x303a
    val CJK_SYMBOLS_AND_PUNCTUATION_OTHER_CJK_PUNCTUATION_SUBSET: SymbolRange =
        SymbolRange.Companion.range('〻', '〼') // 0x303b - 0x303c
    val CJK_SYMBOLS_AND_PUNCTUATION_SPECIAL_CJK_INDICATORS: SymbolRange =
        SymbolRange.Companion.range('〾', '〿') // 0x303e - 0x303f
    val CJK_SYMBOLS_AND_PUNCTUATION_HIRAGANA_TO_ITERATION_MARKS: SymbolRange =
        SymbolRange.Companion.range('぀', 'ゟ') // 0x3040 - 0x309f
    val CJK_SYMBOLS_AND_PUNCTUATION_HIRAGANA_LETTERS_TO_SMALL_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ぁ', 'ゖ') // 0x3041 - 0x3096
    val CJK_SYMBOLS_AND_PUNCTUATION_VOICING_MARKS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('゙', '゚') // 0x3099 - 0x309a
    val CJK_SYMBOLS_AND_PUNCTUATION_VOICING_MARKS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('゛', '゜') // 0x309b - 0x309c
    val CJK_SYMBOLS_AND_PUNCTUATION_ITERATION_MARKS_TO_ITERATION_MARKS: SymbolRange =
        SymbolRange.Companion.range('ゝ', 'ゟ') // 0x309d - 0x309f
    val CJK_SYMBOLS_AND_PUNCTUATION_ITERATION_MARKS: SymbolRange =
        SymbolRange.Companion.range('ゝ', 'ゞ') // 0x309d - 0x309e
    val KATAKANA_KATAKANA_PUNCTUATION_TO_ITERATION_MARKS: SymbolRange =
        SymbolRange.Companion.range('゠', 'ヿ') // 0x30a0 - 0x30ff
    val KATAKANA_KATAKANA_LETTERS: SymbolRange = SymbolRange.Companion.range('ァ', 'ヺ') // 0x30a1 - 0x30fa
    val KATAKANA_CONJUNCTION_AND_LENGTH_MARKS_TO_ITERATION_MARKS: SymbolRange =
        SymbolRange.Companion.range('ー', 'ヾ') // 0x30fc - 0x30fe
    val KATAKANA_CONJUNCTION_AND_LENGTH_MARKS_TO_ITERATION_MARKS_1: SymbolRange =
        SymbolRange.Companion.range('ー', 'ヿ') // 0x30fc - 0x30ff
    val KATAKANA_BOPOMOFO_TO_MISCELLANEOUS_ADDITIONS: SymbolRange =
        SymbolRange.Companion.range('㄀', 'ㄯ') // 0x3100 - 0x312f
    val KATAKANA_BASED_ON_GB_2312_TO_MISCELLANEOUS_ADDITIONS: SymbolRange =
        SymbolRange.Companion.range('ㄅ', 'ㄯ') // 0x3105 - 0x312f
    val KATAKANA_HANGUL_COMPATIBILITY_JAMO_TO_OLD_VOWEL_LETTERS: SymbolRange =
        SymbolRange.Companion.range('㄰', '㆏') // 0x3130 - 0x318f
    val KATAKANA_CONSONANT_LETTERS_TO_OLD_VOWEL_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ㄱ', 'ㆎ') // 0x3131 - 0x318e
    val KANBUN_TATETEN_TO_TATETEN: SymbolRange = SymbolRange.Companion.range('㆐', '㆑') // 0x3190 - 0x3191
    val KANBUN_TATETEN_TO_KAERITEN: SymbolRange = SymbolRange.Companion.range('㆐', '㆟') // 0x3190 - 0x319f
    val KANBUN_KAERITEN_SUBSET: SymbolRange = SymbolRange.Companion.range('㆒', '㆕') // 0x3192 - 0x3195
    val KANBUN_KAERITEN_SUBSET_1: SymbolRange = SymbolRange.Companion.range('㆖', '㆟') // 0x3196 - 0x319f
    val BOPOMOFO_EXTENDED_EXTENDED_BOPOMOFO_FOR_MINNAN_AND_HAKKA_TO_EXTENDED_BOPOMOFO_FOR_CANTONESE: SymbolRange =
        SymbolRange.Companion.range('ㆠ', 'ㆿ') // 0x31a0 - 0x31bf
    val CJK_STROKES_CJK_STROKES_SUBSET: SymbolRange = SymbolRange.Companion.range('㇀', '㇣') // 0x31c0 - 0x31e3
    val KATAKANA_PHONETIC_EXTENSIONS_PHONETIC_EXTENSIONS_FOR_AINU: SymbolRange =
        SymbolRange.Companion.range('ㇰ', 'ㇿ') // 0x31f0 - 0x31ff
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_PARENTHESIZED_HANGUL_LETTERS_TO_PARENTHESIZED_KOREAN_WORDS: SymbolRange =
        SymbolRange.Companion.range('㈀', '㈞') // 0x3200 - 0x321e
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_PARENTHESIZED_HANGUL_LETTERS_TO_CIRCLED_KATAKANA: SymbolRange =
        SymbolRange.Companion.range('㈀', '㋿') // 0x3200 - 0x32ff
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_PARENTHESIZED_IDEOGRAPHS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('㈠', '㈩') // 0x3220 - 0x3229
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_PARENTHESIZED_IDEOGRAPHS_TO_CIRCLED_IDEOGRAPHS_FROM_ARIB_STD_B24: SymbolRange =
        SymbolRange.Companion.range('㈪', '㉇') // 0x322a - 0x3247
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_CIRCLED_NUMBERS_ON_BLACK_SQUARES_FROM_ARIB_STD_B24: SymbolRange =
        SymbolRange.Companion.range('㉈', '㉏') // 0x3248 - 0x324f
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_CIRCLED_NUMBERS: SymbolRange =
        SymbolRange.Companion.range('㉑', '㉟') // 0x3251 - 0x325f
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_CIRCLED_HANGUL_LETTERS_TO_CIRCLED_HANGUL_SYLLABLE: SymbolRange =
        SymbolRange.Companion.range('㉠', '㉿') // 0x3260 - 0x327f
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_CIRCLED_IDEOGRAPHS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('㊀', '㊉') // 0x3280 - 0x3289
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_CIRCLED_IDEOGRAPHS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('㊊', '㊰') // 0x328a - 0x32b0
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_CIRCLED_NUMBERS_1: SymbolRange =
        SymbolRange.Companion.range('㊱', '㊿') // 0x32b1 - 0x32bf
    val ENCLOSED_CJK_LETTERS_AND_MONTHS_TO_CJK_COMPATIBILITY: SymbolRange =
        SymbolRange.Companion.range('㋀', '㏿') // 0x32c0 - 0x33ff
    val CJK_COMPATIBILITY_SQUARED_KATAKANA_WORDS_TO_TELEGRAPH_SYMBOLS_FOR_DAYS: SymbolRange =
        SymbolRange.Companion.range('㌀', '㏿') // 0x3300 - 0x33ff
    val CJK_COMPATIBILITY_CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A: SymbolRange =
        SymbolRange.Companion.range('㐀', '䶿') // 0x3400 - 0x4dbf
    val YIJING_HEXAGRAM_SYMBOLS_YIJING_HEXAGRAM_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('䷀', '䷿') // 0x4dc0 - 0x4dff
    val YIJING_HEXAGRAM_SYMBOLS_CJK_UNIFIED_IDEOGRAPHS: SymbolRange =
        SymbolRange.Companion.range('一', '鿿') // 0x4e00 - 0x9fff
    val YIJING_HEXAGRAM_SYMBOLS_CJK_UNIFIED_IDEOGRAPHS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('一', '鿼') // 0x4e00 - 0x9ffc
    val YI_SYLLABLES_SYLLABLES: SymbolRange = SymbolRange.Companion.range('ꀀ', 'ꀔ') // 0xa000 - 0xa014
    val YI_SYLLABLES_SYLLABLES_TO_SYLLABLES: SymbolRange = SymbolRange.Companion.range('ꀀ', 'ꒌ') // 0xa000 - 0xa48c
    val YI_SYLLABLES_SYLLABLES_TO_SYLLABLES_1: SymbolRange = SymbolRange.Companion.range('ꀀ', '꒏') // 0xa000 - 0xa48f
    val YI_SYLLABLES_SYLLABLES_SUBSET: SymbolRange = SymbolRange.Companion.range('ꀖ', 'ꒌ') // 0xa016 - 0xa48c
    val YI_RADICALS_YI_RADICALS_SUBSET: SymbolRange = SymbolRange.Companion.range('꒐', '꓆') // 0xa490 - 0xa4c6
    val YI_RADICALS_YI_RADICALS: SymbolRange = SymbolRange.Companion.range('꒐', '꓏') // 0xa490 - 0xa4cf
    val LISU_CONSONANTS_TO_VOWELS: SymbolRange = SymbolRange.Companion.range('ꓐ', 'ꓷ') // 0xa4d0 - 0xa4f7
    val LISU_CONSONANTS_TO_TONES: SymbolRange = SymbolRange.Companion.range('ꓐ', 'ꓽ') // 0xa4d0 - 0xa4fd
    val LISU_TONES: SymbolRange = SymbolRange.Companion.range('ꓸ', 'ꓽ') // 0xa4f8 - 0xa4fd
    val LISU_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('꓾', '꓿') // 0xa4fe - 0xa4ff
    val VAI_SYLLABLES_IN__EE_TO_SYLLABLE_FINALS: SymbolRange = SymbolRange.Companion.range('ꔀ', 'ꘌ') // 0xa500 - 0xa60c
    val VAI_SYLLABLES_IN__EE_TO_SYLLABLES_IN__E: SymbolRange = SymbolRange.Companion.range('ꔀ', 'ꘋ') // 0xa500 - 0xa60b
    val VAI_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('꘍', '꘏') // 0xa60d - 0xa60f
    val VAI_HISTORIC_SYLLABLES_TO_LOGOGRAMS: SymbolRange = SymbolRange.Companion.range('ꘐ', 'ꘟ') // 0xa610 - 0xa61f
    val VAI_DIGITS: SymbolRange = SymbolRange.Companion.range('꘠', '꘩') // 0xa620 - 0xa629
    val VAI_HISTORIC_SYLLABLES_SUBSET: SymbolRange = SymbolRange.Companion.range('ꘪ', 'ꘫ') // 0xa62a - 0xa62b
    val CYRILLIC_EXTENDED_B_LETTERS_FOR_OLD_CYRILLIC: SymbolRange =
        SymbolRange.Companion.range('Ꙁ', 'ꙮ') // 0xa640 - 0xa66e
    val CYRILLIC_EXTENDED_B_ABBREVIATION_MARK_TO_COMBINING_NUMERIC_SIGNS: SymbolRange =
        SymbolRange.Companion.range('꙯', '꙲') // 0xa66f - 0xa672
    val CYRILLIC_EXTENDED_B_COMBINING_NUMERIC_SIGNS: SymbolRange =
        SymbolRange.Companion.range('꙰', '꙲') // 0xa670 - 0xa672
    val CYRILLIC_EXTENDED_B_COMBINING_MARKS_FOR_OLD_CYRILLIC: SymbolRange =
        SymbolRange.Companion.range('ꙴ', '꙽') // 0xa674 - 0xa67d
    val CYRILLIC_EXTENDED_B_MODIFIER_LETTER_TO_INTONATION_MARKS_FOR_LITHUANIAN_DIALECTOLOGY: SymbolRange =
        SymbolRange.Companion.range('ꙿ', 'ꚝ') // 0xa67f - 0xa69d
    val CYRILLIC_EXTENDED_B_INTONATION_MARKS_FOR_LITHUANIAN_DIALECTOLOGY: SymbolRange =
        SymbolRange.Companion.range('ꚜ', 'ꚝ') // 0xa69c - 0xa69d
    val CYRILLIC_EXTENDED_B_COMBINING_MARKS_FOR_OLD_CYRILLIC_1: SymbolRange =
        SymbolRange.Companion.range('ꚞ', 'ꚟ') // 0xa69e - 0xa69f
    val BAMUM_SYLLABLES_SUBSET: SymbolRange = SymbolRange.Companion.range('ꚠ', 'ꛥ') // 0xa6a0 - 0xa6e5
    val BAMUM_SYLLABLES_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ꛦ', 'ꛯ') // 0xa6e6 - 0xa6ef
    val BAMUM_COMBINING_MARKS: SymbolRange = SymbolRange.Companion.range('꛰', '꛱') // 0xa6f0 - 0xa6f1
    val BAMUM_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('꛲', '꛷') // 0xa6f2 - 0xa6f7
    val MODIFIER_TONE_LETTERS_CORNER_TONE_MARKS_FOR_CHINESE_TO_LEFT_STEM_TONE_LETTERS: SymbolRange =
        SymbolRange.Companion.range('꜀', '꜖') // 0xa700 - 0xa716
    val MODIFIER_TONE_LETTERS_CHINANTEC_TONE_MARKS_TO_AFRICANIST_TONE_LETTERS: SymbolRange =
        SymbolRange.Companion.range('ꜗ', 'ꜟ') // 0xa717 - 0xa71f
    val LATIN_EXTENDED_D_ADDITIONS_FOR_UPA: SymbolRange = SymbolRange.Companion.range('꜠', '꜡') // 0xa720 - 0xa721
    val LATIN_EXTENDED_D_EGYPTOLOGICAL_ADDITIONS_TO_INSULAR_AND_CELTICIST_LETTERS: SymbolRange =
        SymbolRange.Companion.range('Ꜣ', 'ꞈ') // 0xa722 - 0xa788
    val LATIN_EXTENDED_D_MAYANIST_ADDITIONS_TO_MEDIEVALIST_ADDITIONS: SymbolRange =
        SymbolRange.Companion.range('ꜯ', 'ꜱ') // 0xa72f - 0xa731
    val LATIN_EXTENDED_D_MEDIEVALIST_ADDITIONS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꝱ', 'ꝸ') // 0xa771 - 0xa778
    val LATIN_EXTENDED_D_INSULAR_AND_CELTICIST_LETTERS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('Ᵹ', 'Ꝿ') // 0xa77d - 0xa77e
    val LATIN_EXTENDED_D_MODIFIER_LETTERS_SUBSET: SymbolRange = SymbolRange.Companion.range('꞉', '꞊') // 0xa789 - 0xa78a
    val LATIN_EXTENDED_D_ORTHOGRAPHIC_LETTERS_FOR_GLOTTALS_TO_LETTERS_FOR_UGARITIC_AND_EGYPTOLOGICAL_TRANSLITERATION: SymbolRange =
        SymbolRange.Companion.range('Ꞌ', 'ꞿ') // 0xa78b - 0xa7bf
    val LATIN_EXTENDED_D_ADDITIONAL_LETTERS_TO_ADDITIONS_FOR_LITHUANIAN_DIALECTOLOGY: SymbolRange =
        SymbolRange.Companion.range('ꞓ', 'ꞕ') // 0xa793 - 0xa795
    val LATIN_EXTENDED_D_ADDITIONAL_LETTERS_TO_ADDITIONAL_LETTERS: SymbolRange =
        SymbolRange.Companion.range('Ɦ', 'Ɪ') // 0xa7aa - 0xa7ae
    val LATIN_EXTENDED_D_LETTERS_FOR_AMERICANIST_ORTHOGRAPHIES_TO_LETTER_FOR_GERMAN_DIALECTOLOGY: SymbolRange =
        SymbolRange.Companion.range('Ʞ', 'Ꞵ') // 0xa7b0 - 0xa7b4
    val LATIN_EXTENDED_D_ADDITIONAL_MEDIEVAL_LETTERS_TO_ADDITIONAL_LETTERS_FOR_GAULISH: SymbolRange =
        SymbolRange.Companion.range('Ꟃ', 'ꟊ') // 0xa7c2 - 0xa7ca
    val LATIN_EXTENDED_D_LETTERS_USED_IN_EARLY_PINYIN_ROMANIZATION_TO_LETTERS_USED_IN_EARLY_PINYIN_ROMANIZATION: SymbolRange =
        SymbolRange.Companion.range('Ꞔ', 'Ꟈ') // 0xa7c4 - 0xa7c7
    val LATIN_EXTENDED_D_TO_SYLOTI_NAGRI: SymbolRange = SymbolRange.Companion.range('Ꟶ', 'ꠁ') // 0xa7f5 - 0xa801
    val LATIN_EXTENDED_D_ADDITIONS_FOR_EXTENDED_IPA: SymbolRange =
        SymbolRange.Companion.range('ꟸ', 'ꟹ') // 0xa7f8 - 0xa7f9
    val LATIN_EXTENDED_D_TO_SYLOTI_NAGRI_1: SymbolRange = SymbolRange.Companion.range('ꟻ', 'ꠁ') // 0xa7fb - 0xa801
    val SYLOTI_NAGRI_INDEPENDENT_VOWELS_AND_DVISVARA_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꠃ', 'ꠅ') // 0xa803 - 0xa805
    val SYLOTI_NAGRI_CONSONANTS_AND_CONSONANT_SIGNS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꠇ', 'ꠊ') // 0xa807 - 0xa80a
    val SYLOTI_NAGRI_CONSONANTS_AND_CONSONANT_SIGNS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ꠌ', 'ꠢ') // 0xa80c - 0xa822
    val SYLOTI_NAGRI_DEPENDENT_VOWEL_SIGNS: SymbolRange = SymbolRange.Companion.range('ꠣ', 'ꠧ') // 0xa823 - 0xa827
    val SYLOTI_NAGRI_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꠣ', 'ꠤ') // 0xa823 - 0xa824
    val SYLOTI_NAGRI_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ꠥ', 'ꠦ') // 0xa825 - 0xa826
    val SYLOTI_NAGRI_POETRY_MARKS: SymbolRange = SymbolRange.Companion.range('꠨', '꠫') // 0xa828 - 0xa82b
    val COMMON_INDIC_NUMBER_FORMS_NUMBER_FORMS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('꠰', '꠵') // 0xa830 - 0xa835
    val COMMON_INDIC_NUMBER_FORMS_NUMBER_FORMS_TO_NUMBER_FORMS: SymbolRange =
        SymbolRange.Companion.range('꠶', '꠷') // 0xa836 - 0xa837
    val COMMON_INDIC_NUMBER_FORMS_NUMBER_FORMS_TO_CURRENCY_SYMBOL: SymbolRange =
        SymbolRange.Companion.range('꠶', '꠹') // 0xa836 - 0xa839
    val PHAGS_PA_CONSONANTS_TO_CONSONANT_ADDITION_FOR_TIBETAN: SymbolRange =
        SymbolRange.Companion.range('ꡀ', 'ꡳ') // 0xa840 - 0xa873
    val PHAGS_PA_HEAD_MARKS_FOR_TIBETAN_TO_PUNCTUATION_FOR_TIBETAN: SymbolRange =
        SymbolRange.Companion.range('꡴', '꡷') // 0xa874 - 0xa877
    val SAURASHTRA_VARIOUS_SIGNS: SymbolRange = SymbolRange.Companion.range('ꢀ', 'ꢁ') // 0xa880 - 0xa881
    val SAURASHTRA_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange =
        SymbolRange.Companion.range('ꢂ', 'ꢳ') // 0xa882 - 0xa8b3
    val SAURASHTRA_CONSONANTS_TO_VIRAMA: SymbolRange = SymbolRange.Companion.range('ꢴ', 'ꣅ') // 0xa8b4 - 0xa8c5
    val SAURASHTRA_CONSONANTS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ꢴ', 'ꣃ') // 0xa8b4 - 0xa8c3
    val SAURASHTRA_VIRAMA_TO_VIRAMA: SymbolRange = SymbolRange.Companion.range('꣄', 'ꣅ') // 0xa8c4 - 0xa8c5
    val SAURASHTRA_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('꣎', '꣏') // 0xa8ce - 0xa8cf
    val SAURASHTRA_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('꣐', '꣙') // 0xa8d0 - 0xa8d9
    val DEVANAGARI_EXTENDED_CANTILLATION_MARKS_SVARA_FOR_THE_SAMAVEDA: SymbolRange =
        SymbolRange.Companion.range('꣠', '꣱') // 0xa8e0 - 0xa8f1
    val DEVANAGARI_EXTENDED_MARKS_OF_NASALIZATION: SymbolRange =
        SymbolRange.Companion.range('ꣲ', 'ꣷ') // 0xa8f2 - 0xa8f7
    val DEVANAGARI_EXTENDED_EDITORIAL_MARKS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('꣸', '꣺') // 0xa8f8 - 0xa8fa
    val DEVANAGARI_EXTENDED_SIGNS_TO_SIGNS: SymbolRange = SymbolRange.Companion.range('ꣽ', 'ꣾ') // 0xa8fd - 0xa8fe
    val KAYAH_LI_DIGITS: SymbolRange = SymbolRange.Companion.range('꤀', '꤉') // 0xa900 - 0xa909
    val KAYAH_LI_CONSONANTS_TO_VOWELS: SymbolRange = SymbolRange.Companion.range('ꤊ', 'ꤥ') // 0xa90a - 0xa925
    val KAYAH_LI_VOWELS_TO_TONE_MARKS: SymbolRange = SymbolRange.Companion.range('ꤦ', '꤭') // 0xa926 - 0xa92d
    val KAYAH_LI_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('꤮', '꤯') // 0xa92e - 0xa92f
    val REJANG_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ꤰ', 'ꥆ') // 0xa930 - 0xa946
    val REJANG_VOWEL_SIGNS_TO_CONSONANT_SIGNS: SymbolRange = SymbolRange.Companion.range('ꥇ', '꥓') // 0xa947 - 0xa953
    val REJANG_VOWEL_SIGNS_TO_CONSONANT_SIGNS_1: SymbolRange = SymbolRange.Companion.range('ꥇ', 'ꥑ') // 0xa947 - 0xa951
    val REJANG_CONSONANT_SIGNS_TO_CONSONANT_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ꥒ', '꥓') // 0xa952 - 0xa953
    val HANGUL_JAMO_EXTENDED_A_OLD_INITIAL_CONSONANTS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꥠ', 'ꥼ') // 0xa960 - 0xa97c
    val JAVANESE_VARIOUS_SIGNS: SymbolRange = SymbolRange.Companion.range('ꦀ', 'ꦃ') // 0xa980 - 0xa983
    val JAVANESE_VARIOUS_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ꦀ', 'ꦂ') // 0xa980 - 0xa982
    val JAVANESE_LETTERS: SymbolRange = SymbolRange.Companion.range('ꦄ', 'ꦲ') // 0xa984 - 0xa9b2
    val JAVANESE_SIGN_TO_DEPENDENT_CONSONANT_SIGNS: SymbolRange =
        SymbolRange.Companion.range('꦳', '꧀') // 0xa9b3 - 0xa9c0
    val JAVANESE_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ꦴ', 'ꦵ') // 0xa9b4 - 0xa9b5
    val JAVANESE_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ꦶ', 'ꦹ') // 0xa9b6 - 0xa9b9
    val JAVANESE_DEPENDENT_VOWEL_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ꦺ', 'ꦻ') // 0xa9ba - 0xa9bb
    val JAVANESE_DEPENDENT_VOWEL_SIGNS_TO_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ꦼ', 'ꦽ') // 0xa9bc - 0xa9bd
    val JAVANESE_DEPENDENT_CONSONANT_SIGNS_TO_DEPENDENT_CONSONANT_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ꦾ', '꧀') // 0xa9be - 0xa9c0
    val JAVANESE_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('꧁', '꧍') // 0xa9c1 - 0xa9cd
    val JAVANESE_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('꧐', '꧙') // 0xa9d0 - 0xa9d9
    val JAVANESE_ELLIPSIS_MARKS: SymbolRange = SymbolRange.Companion.range('꧞', '꧟') // 0xa9de - 0xa9df
    val MYANMAR_EXTENDED_B_ADDITIONS_FOR_SHAN_PALI_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꧠ', 'ꧤ') // 0xa9e0 - 0xa9e4
    val MYANMAR_EXTENDED_B_REDUPLICATION_MARK_TO_TAI_LAING_CONSONANTS: SymbolRange =
        SymbolRange.Companion.range('ꧦ', 'ꧯ') // 0xa9e6 - 0xa9ef
    val MYANMAR_EXTENDED_B_TAI_LAING_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ꧧ', 'ꧯ') // 0xa9e7 - 0xa9ef
    val MYANMAR_EXTENDED_B_TAI_LAING_DIGITS: SymbolRange = SymbolRange.Companion.range('꧰', '꧹') // 0xa9f0 - 0xa9f9
    val MYANMAR_EXTENDED_B_TAI_LAING_CONSONANTS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꧺ', 'ꧾ') // 0xa9fa - 0xa9fe
    val CHAM_INDEPENDENT_VOWELS_TO_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ꨀ', 'ꨨ') // 0xaa00 - 0xaa28
    val CHAM_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ꨩ', 'ꨮ') // 0xaa29 - 0xaa2e
    val CHAM_DEPENDENT_VOWEL_SIGNS_TO_CONSONANT_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ꨩ', 'ꨶ') // 0xaa29 - 0xaa36
    val CHAM_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ꨯ', 'ꨰ') // 0xaa2f - 0xaa30
    val CHAM_DEPENDENT_VOWEL_SIGNS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ꨱ', 'ꨲ') // 0xaa31 - 0xaa32
    val CHAM_CONSONANT_SIGNS_SUBSET: SymbolRange = SymbolRange.Companion.range('ꨳ', 'ꨴ') // 0xaa33 - 0xaa34
    val CHAM_CONSONANT_SIGNS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ꨵ', 'ꨶ') // 0xaa35 - 0xaa36
    val CHAM_FINAL_CONSONANTS_SUBSET: SymbolRange = SymbolRange.Companion.range('ꩀ', 'ꩂ') // 0xaa40 - 0xaa42
    val CHAM_FINAL_CONSONANTS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ꩄ', 'ꩋ') // 0xaa44 - 0xaa4b
    val CHAM_FINAL_CONSONANTS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ꩌ', 'ꩍ') // 0xaa4c - 0xaa4d
    val CHAM_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('꩐', '꩙') // 0xaa50 - 0xaa59
    val CHAM_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('꩜', '꩟') // 0xaa5c - 0xaa5f
    val MYANMAR_EXTENDED_A_KHAMTI_SHAN_CONSONANTS_TO_KHAMTI_SHAN_LOGOGRAMS: SymbolRange =
        SymbolRange.Companion.range('ꩠ', 'ꩶ') // 0xaa60 - 0xaa76
    val MYANMAR_EXTENDED_A_KHAMTI_SHAN_CONSONANTS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꩠ', 'ꩯ') // 0xaa60 - 0xaa6f
    val MYANMAR_EXTENDED_A_KHAMTI_SHAN_CONSONANTS_TO_KHAMTI_SHAN_LOGOGRAMS_1: SymbolRange =
        SymbolRange.Companion.range('ꩱ', 'ꩶ') // 0xaa71 - 0xaa76
    val MYANMAR_EXTENDED_A_AITON_SYMBOLS_AND_LETTERS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('꩷', '꩹') // 0xaa77 - 0xaa79
    val MYANMAR_EXTENDED_A_PAO_KAREN_TONE_MARK_TO_TAI_LAING_TONE_MARKS: SymbolRange =
        SymbolRange.Companion.range('ꩻ', 'ꩽ') // 0xaa7b - 0xaa7d
    val MYANMAR_EXTENDED_A_TO_TAI_VIET: SymbolRange = SymbolRange.Companion.range('ꩾ', 'ꪯ') // 0xaa7e - 0xaaaf
    val TAI_VIET_VOWELS_AND_FINALS_SUBSET: SymbolRange = SymbolRange.Companion.range('ꪲ', 'ꪴ') // 0xaab2 - 0xaab4
    val TAI_VIET_VOWELS_AND_FINALS_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ꪵ', 'ꪶ') // 0xaab5 - 0xaab6
    val TAI_VIET_VOWELS_AND_FINALS_SUBSET_2: SymbolRange = SymbolRange.Companion.range('ꪷ', 'ꪸ') // 0xaab7 - 0xaab8
    val TAI_VIET_VOWELS_AND_FINALS_SUBSET_3: SymbolRange = SymbolRange.Companion.range('ꪹ', 'ꪽ') // 0xaab9 - 0xaabd
    val TAI_VIET_VOWELS_AND_FINALS_TO_VOWELS_AND_FINALS: SymbolRange =
        SymbolRange.Companion.range('ꪾ', '꪿') // 0xaabe - 0xaabf
    val TAI_VIET_WORD_LIGATURE_SYMBOLS: SymbolRange = SymbolRange.Companion.range('ꫛ', 'ꫜ') // 0xaadb - 0xaadc
    val TAI_VIET_WORD_LIGATURE_SYMBOLS_TO_WORD_LIGATURE_SYMBOLS: SymbolRange =
        SymbolRange.Companion.range('ꫛ', 'ꫝ') // 0xaadb - 0xaadd
    val TAI_VIET_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('꫞', '꫟') // 0xaade - 0xaadf
    val MEETEI_MAYEK_EXTENSIONS_INDEPENDENT_VOWEL_SIGNS_TO_CONSONANTS: SymbolRange =
        SymbolRange.Companion.range('ꫠ', 'ꫪ') // 0xaae0 - 0xaaea
    val MEETEI_MAYEK_EXTENSIONS_DEPENDENT_VOWEL_SIGNS: SymbolRange =
        SymbolRange.Companion.range('ꫫ', 'ꫯ') // 0xaaeb - 0xaaef
    val MEETEI_MAYEK_EXTENSIONS_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꫬ', 'ꫭ') // 0xaaec - 0xaaed
    val MEETEI_MAYEK_EXTENSIONS_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ꫮ', 'ꫯ') // 0xaaee - 0xaaef
    val MEETEI_MAYEK_EXTENSIONS_PUNCTUATION: SymbolRange = SymbolRange.Companion.range('꫰', '꫱') // 0xaaf0 - 0xaaf1
    val MEETEI_MAYEK_EXTENSIONS_SIGN_TO_REPETITION_MARKS: SymbolRange =
        SymbolRange.Companion.range('ꫲ', 'ꫴ') // 0xaaf2 - 0xaaf4
    val MEETEI_MAYEK_EXTENSIONS_REPETITION_MARKS: SymbolRange = SymbolRange.Companion.range('ꫳ', 'ꫴ') // 0xaaf3 - 0xaaf4
    val MEETEI_MAYEK_EXTENSIONS_SIGN_TO_SIGN: SymbolRange = SymbolRange.Companion.range('ꫵ', '꫶') // 0xaaf5 - 0xaaf6
    val MEETEI_MAYEK_EXTENSIONS_GAMO_GOFA_DAWRO_AND_BASKETO_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꬁ', 'ꬆ') // 0xab01 - 0xab06
    val MEETEI_MAYEK_EXTENSIONS_GAMO_GOFA_DAWRO_AND_BASKETO_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ꬉ', 'ꬎ') // 0xab09 - 0xab0e
    val MEETEI_MAYEK_EXTENSIONS_GAMO_GOFA_DAWRO_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꬑ', 'ꬖ') // 0xab11 - 0xab16
    val MEETEI_MAYEK_EXTENSIONS_GUMUZ_SUBSET: SymbolRange = SymbolRange.Companion.range('ꬠ', 'ꬦ') // 0xab20 - 0xab26
    val MEETEI_MAYEK_EXTENSIONS_GUMUZ_SUBSET_1: SymbolRange = SymbolRange.Companion.range('ꬨ', 'ꬮ') // 0xab28 - 0xab2e
    val LATIN_EXTENDED_E_LETTERS_FOR_GERMAN_DIALECTOLOGY: SymbolRange =
        SymbolRange.Companion.range('ꬰ', 'ꭚ') // 0xab30 - 0xab5a
    val LATIN_EXTENDED_E_MODIFIER_LETTERS_FOR_GERMAN_DIALECTOLOGY_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꭜ', 'ꭟ') // 0xab5c - 0xab5f
    val LATIN_EXTENDED_E_MODIFIER_LETTERS_FOR_GERMAN_DIALECTOLOGY_TO_LETTERS_FOR_SCOTS_DIALECTOLOGY: SymbolRange =
        SymbolRange.Companion.range('ꭜ', 'ꭩ') // 0xab5c - 0xab69
    val LATIN_EXTENDED_E_HISTORIC_LETTERS_FOR_SAKHA_YAKUT_TO_LETTERS_FOR_SINOLOGICAL_TRANSCRIPTION: SymbolRange =
        SymbolRange.Companion.range('ꭠ', 'ꭨ') // 0xab60 - 0xab68
    val LATIN_EXTENDED_E_LETTERS_FOR_SCOTS_DIALECTOLOGY_SUBSET: SymbolRange =
        SymbolRange.Companion.range('꭪', '꭫') // 0xab6a - 0xab6b
    val CHEROKEE_SUPPLEMENT_LOWERCASE_SYLLABLES: SymbolRange = SymbolRange.Companion.range('ꭰ', 'ꮿ') // 0xab70 - 0xabbf
    val CHEROKEE_SUPPLEMENT_TO_MEETEI_MAYEK: SymbolRange = SymbolRange.Companion.range('ꭰ', 'ꯢ') // 0xab70 - 0xabe2
    val MEETEI_MAYEK_LETTERS_TO_FINAL_CONSONANTS: SymbolRange = SymbolRange.Companion.range('ꯀ', 'ꯢ') // 0xabc0 - 0xabe2
    val MEETEI_MAYEK_DEPENDENT_VOWEL_SIGNS: SymbolRange = SymbolRange.Companion.range('ꯣ', 'ꯪ') // 0xabe3 - 0xabea
    val MEETEI_MAYEK_DEPENDENT_VOWEL_SIGNS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ꯣ', 'ꯤ') // 0xabe3 - 0xabe4
    val MEETEI_MAYEK_DEPENDENT_VOWEL_SIGNS_SUBSET_1: SymbolRange =
        SymbolRange.Companion.range('ꯦ', 'ꯧ') // 0xabe6 - 0xabe7
    val MEETEI_MAYEK_DEPENDENT_VOWEL_SIGNS_SUBSET_2: SymbolRange =
        SymbolRange.Companion.range('ꯩ', 'ꯪ') // 0xabe9 - 0xabea
    val MEETEI_MAYEK_PUNCTUATION_SUBSET: SymbolRange = SymbolRange.Companion.range('꯬', '꯭') // 0xabec - 0xabed
    val MEETEI_MAYEK_DIGITS_SUBSET: SymbolRange = SymbolRange.Companion.range('꯰', '꯹') // 0xabf0 - 0xabf9
    val MEETEI_MAYEK_HANGUL_SYLLABLES_SUBSET: SymbolRange = SymbolRange.Companion.range('가', '힣') // 0xac00 - 0xd7a3
    val MEETEI_MAYEK_HANGUL_SYLLABLES: SymbolRange = SymbolRange.Companion.range('가', '힯') // 0xac00 - 0xd7af
    val HANGUL_JAMO_EXTENDED_B_OLD_MEDIAL_VOWELS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ힰ', 'ퟆ') // 0xd7b0 - 0xd7c6
    val HANGUL_JAMO_EXTENDED_B_OLD_FINAL_CONSONANTS_SUBSET: SymbolRange =
        SymbolRange.Companion.range('ퟋ', 'ퟻ') // 0xd7cb - 0xd7fb
}
