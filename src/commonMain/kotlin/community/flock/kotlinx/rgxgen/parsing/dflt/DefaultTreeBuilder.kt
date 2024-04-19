package community.flock.kotlinx.rgxgen.parsing.dflt

import community.flock.kotlinx.rgxgen.config.RgxGenOption
import community.flock.kotlinx.rgxgen.config.RgxGenProperties
import community.flock.kotlinx.rgxgen.model.GroupType
import community.flock.kotlinx.rgxgen.model.MatchType
import community.flock.kotlinx.rgxgen.model.RgxGenCharsDefinition
import community.flock.kotlinx.rgxgen.model.SymbolRange
import community.flock.kotlinx.rgxgen.model.SymbolRange.Companion.range
import community.flock.kotlinx.rgxgen.model.UnicodeCategory
import community.flock.kotlinx.rgxgen.model.WhitespaceChar
import community.flock.kotlinx.rgxgen.nodes.Choice
import community.flock.kotlinx.rgxgen.nodes.FinalSymbol
import community.flock.kotlinx.rgxgen.nodes.Group
import community.flock.kotlinx.rgxgen.nodes.GroupRef
import community.flock.kotlinx.rgxgen.nodes.Node
import community.flock.kotlinx.rgxgen.nodes.NotSymbol
import community.flock.kotlinx.rgxgen.nodes.Repeat
import community.flock.kotlinx.rgxgen.nodes.Repeat.Companion.minimum
import community.flock.kotlinx.rgxgen.nodes.SymbolSet
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofAscii
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofAsciiRanges
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofDotPattern
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofUnicode
import community.flock.kotlinx.rgxgen.nodes.SymbolSet.Companion.ofUnicodeCharacterClass
import community.flock.kotlinx.rgxgen.parsing.NodeTreeBuilder
import community.flock.kotlinx.rgxgen.util.chars.CharArrayList
import community.flock.kotlinx.rgxgen.util.chars.CharList
import kotlin.collections.ArrayList

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
 * Default implementation of parser and NodeTreeBuilder.
 * It reads expression and creates a hierarchy of `Node`.
 */
class DefaultTreeBuilder(expr: String, private val properties: RgxGenProperties<*>?) : NodeTreeBuilder {
    private val aCharIterator = CharIterator(expr)
    private val aNodesStartPos: MutableMap<Node, Int> = mutableMapOf()

    private var aNode: Node? = null
    private var aNextGroupIndex = 1

    /**
     * Convert all text aggregated in StringBuilder into FinalSymbol node.
     * Does nothing, if sb is empty
     *
     * @param sb    StringBuilder, that is read and emptied
     * @param nodes nodes collection to add created node to.
     */
    private fun sbToFinal(sb: StringBuilder, nodes: MutableCollection<Node>) {
        if (sb.length != 0) {
            val finalSymbol = FinalSymbol(sb.toString())
            aNodesStartPos[finalSymbol] = aCharIterator.prevPos() - finalSymbol.value.length
            nodes.add(finalSymbol)
            sb.deleteRange(0, Int.MAX_VALUE)
        }
    }

    /**
     * Discovers GroupType starting from current position.
     * After execution cursor is on first unprocessed character.
     *
     * @return type of the group (@see GroupType enum)
     */
    private fun processGroupType(): GroupType {
        val groupType: GroupType
        var skip = 2
        if (aCharIterator.peek() == '?') {
            val pos2char = aCharIterator.peek(1)
            when (pos2char) {
                '<' -> {
                    skip = 3
                    val pos3char = aCharIterator.peek(2)
                    groupType = if (pos3char == '!') {
                        GroupType.NEGATIVE_LOOKBEHIND
                    } else if (pos3char == '=') {
                        GroupType.POSITIVE_LOOKBEHIND
                    } else {
                        aCharIterator.skip(skip)
                        throw RgxGenParseException("Unexpected symbol in pattern: " + aCharIterator.context())
                    }
                }

                '=' -> groupType = GroupType.POSITIVE_LOOKAHEAD
                ':' -> groupType = GroupType.NON_CAPTURE_GROUP
                '!' -> groupType = GroupType.NEGATIVE_LOOKAHEAD
                else -> {
                    aCharIterator.skip(skip)
                    throw RgxGenParseException("Unexpected symbol in pattern: " + aCharIterator.context())
                }
            }
        } else {
            return GroupType.CAPTURE_GROUP
        }

        aCharIterator.skip(skip)
        return groupType
    }

    private fun handleGroupEndCharacter(
        startPos: Int,
        sb: StringBuilder,
        nodes: MutableList<Node>,
        isChoice: Boolean,
        choices: MutableList<Node>,
        captureGroupIndex: Int?,
        groupType: GroupType
    ): Node {
        if (sb.length == 0 && nodes.isEmpty()) {
            // Special case when '(a|)' is used - like empty
            val finalSymbol = FinalSymbol("")
            aNodesStartPos[finalSymbol] = startPos
            nodes.add(finalSymbol)
        } else {
            sbToFinal(sb, nodes)
        }

        if (isChoice) {
            choices.add(sequenceOrNot(startPos, nodes, choices, false, null))
            nodes.clear()
        }

        val node = sequenceOrNot(startPos, nodes, choices, isChoice, captureGroupIndex)

        return if (groupType.isNegative) {
            NotSymbol(node.pattern, node)
        } else {
            node
        }
    }

    private fun getGroupIndexIfCapture(currentGroupType: GroupType): Int? {
        if (currentGroupType == GroupType.CAPTURE_GROUP) {
            return aNextGroupIndex++
        }
        return null
    }

    /**
     * There is limited numbed of characters that can precede ^ or follow $.
     * This method verifies that the pattern is syntactically correct.
     *
     * @param currentChar character at current position
     * NOTE! Must be either caret or dollar sign
     */
    private fun verifyStartEndMarkerConsistency(currentChar: Char) {
        assertCorrectCharacter(currentChar)
        val charAtPos = aCharIterator.peek(if (currentChar == '^') -2 else 0)
        val errorText = when (charAtPos) {
            '+', '*', '{', '?', Char(0x00), '\n', '\r', '|' -> return

            '^', '$' -> "Start and end of line markers cannot be put together."
            '(' -> if (currentChar == '$') {
                "After dollar only new line is allowed!"
            } else {
                return
            }

            ')' -> if (currentChar == '^') {
                "Before caret only new line is allowed!"
            } else {
                return
            }

            else -> if (currentChar == '^') "Before caret only new line is allowed!"
            else "After dollar only new line is allowed!"
        }
        throw PatternDoesNotMatchAnythingException(errorText + aCharIterator.context())
    }

    private fun parseGroup(groupStartPos: Int, currentGroupType: GroupType): Node {
        val captureGroupIndex = getGroupIndexIfCapture(currentGroupType)
        val remainingLength = aCharIterator.remaining()
        val choices: MutableList<Node> = ArrayList(remainingLength)
        val nodes: MutableList<Node> = ArrayList(remainingLength)
        val sb = StringBuilder(remainingLength)
        var isChoice = false
        var choicesStartPos = groupStartPos

        while (aCharIterator.hasNext()) {
            val c = aCharIterator.next()
            when (c) {
                '^', '$' -> verifyStartEndMarkerConsistency(c)
                '[' -> {
                    sbToFinal(sb, nodes)
                    nodes.add(handleSquareBrackets())
                }

                '(' -> {
                    sbToFinal(sb, nodes)
                    val intGroupStartPos = aCharIterator.prevPos()
                    val groupType = processGroupType()
                    nodes.add(parseGroup(intGroupStartPos, groupType))
                }

                '|' -> {
                    choicesStartPos = handlePipeCharacter(choices, nodes, sb, choicesStartPos)
                    isChoice = true
                }

                ')' -> return handleGroupEndCharacter(
                    groupStartPos,
                    sb,
                    nodes,
                    isChoice,
                    choices,
                    captureGroupIndex,
                    currentGroupType
                )

                '{', '*', '?', '+' -> handleRepeatCharacter(nodes, sb, c)
                '.' -> handleAnySymbolCharacter(nodes, sb)
                '\\' -> handleEscapedCharacter(sb, nodes, true)
                else -> sb.append(c)
            }
        }

        return handleGroupEndCharacter(groupStartPos, sb, nodes, isChoice, choices, captureGroupIndex, currentGroupType)
    }

    private fun handleAnySymbolCharacter(nodes: MutableCollection<Node>, sb: StringBuilder) {
        sbToFinal(sb, nodes)
        val symbolSet = ofDotPattern(properties)
        aNodesStartPos[symbolSet] = aCharIterator.prevPos()
        nodes.add(symbolSet)
    }

    private fun handlePipeCharacter(
        choices: MutableList<Node>,
        nodes: MutableList<Node>,
        sb: StringBuilder,
        choicesStartPos: Int
    ): Int {
        var choicesStartPos = choicesStartPos
        if (sb.length == 0 && nodes.isEmpty()) {
            // Special case when '(|a)' is used - like empty or something
            val finalSymbol = FinalSymbol("")
            aNodesStartPos[finalSymbol] = aCharIterator.prevPos() + 1
            choices.add(finalSymbol)
        } else {
            sbToFinal(sb, nodes)
            choices.add(sequenceOrNot(choicesStartPos, nodes, choices, false, null))
            choicesStartPos = aCharIterator.prevPos() + 1
            nodes.clear()
        }
        return choicesStartPos
    }

    private fun handleRepeatCharacter(nodes: MutableList<Node>, sb: StringBuilder, c: Char) {
        // We had separate characters before
        val repeatNode: Node
        if (sb.length == 0) {
            // Repetition for the last node
            if (nodes.isEmpty()) {
                val previousChar = aCharIterator.peek(-2)
                if (previousChar == '^' || previousChar == '$') {
                    throw TokenNotQuantifiableException(previousChar.toString() + " at " + aCharIterator.context())
                } else {
                    throw RgxGenParseException("Cannot repeat nothing at" + aCharIterator.context())
                }
            } else {
                repeatNode = nodes.removeAt(nodes.size - 1)
            }
        } else {
            // Repetition for the last character
            val charToRepeat = sb[sb.length - 1]
            sb.deleteAt(sb.length - 1)
            sbToFinal(sb, nodes)
            repeatNode = FinalSymbol(charToRepeat.toString())
            aNodesStartPos[repeatNode] = aCharIterator.prevPos() - 1
        }
        nodes.add(handleRepeat(c, repeatNode))
    }

    /**
     * Parse hexadecimal string into a integer value.
     * Format: NN or {NNNN}
     *
     * @return integer value
     */
    private fun parseHexadecimal(): Int {
        val c = aCharIterator.peek()
        val hexValue: String?
        if (c == '{') {
            aCharIterator.skip()
            hexValue = aCharIterator.nextUntil('}')
        } else {
            hexValue = aCharIterator.next(2)
        }
        return hexValue.toInt(ConstantsProvider.HEX_RADIX)
    }

    /**
     * Parse unicode hexadecimal string into a integer value.
     * Format: NNNN
     *
     * @return integer value
     */
    private fun parseUnicode(): Int {
        val hexValue = aCharIterator.next(4)
        return hexValue.toInt(ConstantsProvider.HEX_RADIX)
    }

    /**
     * Create group reference node.
     * It starts after escape character AND first digit of group index.
     * aCharIterator after execution is on position right after group index digits.
     *
     * @param groupRefAllowed if at this position group reference is allowed
     * @param nodes           nodes to which add group reference node when created.
     * @param firstCharacter  first digit character, since we're starting after that
     * @throws RgxGenParseException if groupRefAllowed is false
     */
    private fun handleGroupReference(groupRefAllowed: Boolean, nodes: MutableCollection<Node>, firstCharacter: Char) {
        if (groupRefAllowed) {
            val startPos = aCharIterator.prevPos() - 1
            val digitsSubstring = aCharIterator.takeWhile { ch: Char? ->
                ch!!.isDigit()
            }
            val groupNumber = firstCharacter.toString() + digitsSubstring
            val groupRef = GroupRef('\\'.toString() + groupNumber, groupNumber.toInt())
            aNodesStartPos[groupRef] = startPos
            nodes.add(groupRef)
        } else {
            throw RgxGenParseException("Group ref is not expected here. " + aCharIterator.context())
        }
    }

    /**
     * Handles next character after escape sequence - \
     * It will either:
     * a) add new node to nodes, if that was any special escape sequence, or
     * b) append character to sb, otherwise
     *
     * @param sb    string builder containing all previous characters before the escape
     * @param nodes previously created nodes; new node will be appended here
     */
    private fun handleEscapedCharacter(sb: StringBuilder, nodes: MutableCollection<Node>, groupRefAllowed: Boolean) {
        val c = aCharIterator.next()
        var createdNode: Node? = null
        val nodeStartOffset = aCharIterator.prevPos() - 1
        when (c) {
            'd', 'D' -> {
                sbToFinal(sb, nodes)
                createdNode = ofAsciiRanges("\\" + c, listOf(ConstantsProvider.ASCII_DIGITS), getMatchType(c, 'd'))
            }

            's', 'S' -> {
                sbToFinal(sb, nodes)
                val whitespaceChars = RgxGenOption.WHITESPACE_DEFINITION.getFromProperties(
                    properties
                )!!
                val whitespaceCharsList = whitespaceChars.map { obj: WhitespaceChar -> obj.get() }
                    .toList()
                    .let { CharArrayList(it.toCharArray()) }

                createdNode = ofAscii(
                    "\\" + c,
                    RgxGenCharsDefinition.of(whitespaceCharsList),
                    RgxGenCharsDefinition.of(*ConstantsProvider.asciiWhitespaces),
                    getMatchType(c, 's')
                )
            }

            'w', 'W' -> {
                sbToFinal(sb, nodes)
                createdNode = ofAscii(
                    "\\" + c,
                    ConstantsProvider.asciiWordCharRanges,
                    CharList.charList('_'),
                    getMatchType(c, 'w')
                )
            }

            'p', 'P' -> {
                sbToFinal(sb, nodes)
                createdNode = createUnicodeSymbolSetNode(c, getMatchType(c, 'p'))
            }

            'x' -> sb.append(parseHexadecimal().toChar())
            'u' -> sb.append(parseUnicode().toChar())
            'Q' -> sb.append(aCharIterator.nextUntil("\\E"))
            'E' -> {}
            'b', 'B' -> {}
            '1', '2', '3', '4', '5', '6', '7', '8', '9' -> {
                sbToFinal(sb, nodes)
                handleGroupReference(groupRefAllowed, nodes, c)
            }

            else -> sb.append(c)
        }
        if (createdNode != null) {
            aNodesStartPos[createdNode] = nodeStartOffset
            nodes.add(createdNode)
        }
    }

    private fun createUnicodeSymbolSetNode(c: Char, matchType: MatchType): Node {
        val characterClassKey = characterClassKey
        val unicodeCategory = UnicodeCategory.ALL_CATEGORIES[characterClassKey]
        val pattern = "\\$c{$characterClassKey}"
        return ofUnicodeCharacterClass(pattern, unicodeCategory!!, matchType)
    }

    private val characterClassKey: String?
        get() {
            if (aCharIterator.peek() == '{') {
                aCharIterator.skip()
                return aCharIterator.nextUntil('}')
            } else {
                return aCharIterator.next(1)
            }
        }

    /**
     * Parses min and max repetitions from the {min,max} or {max} expressions (starting after {
     *
     * @param repeatNode node that should be repeated
     * @return Repeat node
     */
    private fun handleRepeatInCurvyBraces(startPos: Int, repeatNode: Node): Repeat {
        val sb = StringBuilder(10)
        var min = -1
        val contextIndex = aCharIterator.prevPos()
        while (aCharIterator.hasNext()) {
            val c = aCharIterator.next()
            when (c) {
                ',' -> {
                    val tmpContextIndex = aCharIterator.prevPos() - 1
                    try {
                        min = sb.toString().toInt()
                    } catch (e: NumberFormatException) {
                        throw RgxGenParseException(
                            "Malformed lower bound number." + aCharIterator.context(
                                tmpContextIndex
                            ), e
                        )
                    }
                    sb.deleteRange(0, sb.length)
                }

                '}' -> return if (min == -1) {
                    Repeat(aCharIterator.substringToCurrPos(startPos), repeatNode, sb.toString().toInt())
                } else {
                    if (sb.length == 0) {
                        minimum(aCharIterator.substringToCurrPos(startPos), repeatNode, min)
                    } else {
                        try {
                            Repeat(aCharIterator.substringToCurrPos(startPos), repeatNode, min, sb.toString().toInt())
                        } catch (e: NumberFormatException) {
                            throw RgxGenParseException("Malformed upper bound number." + aCharIterator.context(), e)
                        }
                    }
                }

                '\\' -> throw RgxGenParseException("Escape character inside curvy repetition is not supported. " + aCharIterator.context())

                else -> sb.append(c)
            }
        }

        throw RgxGenParseException("Unbalanced '{' - missing '}' at " + aCharIterator.context(contextIndex))
    }

    /**
     * Creates appropriate repetition for a node.
     *
     * @param c          character that starts repetitions pattern - *, +, ?, {
     * @param repeatNode node that shall be repeated
     * @return Repeat node
     */
    private fun handleRepeat(c: Char, repeatNode: Node): Repeat {
        val startPos = aNodesStartPos[repeatNode]!!
        val node = when (c) {
            '*' -> minimum(aCharIterator.substringToCurrPos(startPos), repeatNode, 0)
            '?' -> Repeat(aCharIterator.substringToCurrPos(startPos), repeatNode, 0, 1)
            '+' -> minimum(aCharIterator.substringToCurrPos(startPos), repeatNode, 1)
            '{' -> handleRepeatInCurvyBraces(startPos, repeatNode)
            else -> throw RgxGenParseException("Unknown repetition character '" + c + '\'' + aCharIterator.context())
        }
        aNodesStartPos[node] = startPos
        return node
    }

    /**
     * Wraps multiple nodes into correct container node (Choice, Sequence) or node as is and wraps it into Group node, if this is a capture group
     *
     * @param nodes             list of nodes (sequence or single node)
     * @param choices           list of nodes to select one from
     * @param isChoice          true when `` choices should be used
     * @param captureGroupIndex index of capture group
     * @return Group, Node
     */
    private fun sequenceOrNot(
        startPos: Int,
        nodes: List<Node>,
        choices: List<Node>,
        isChoice: Boolean,
        captureGroupIndex: Int?
    ): Node {
        val resultNode: Node

        if (nodes.size == 1) {
            resultNode = nodes[0]
        } else {
            if (isChoice) {
                if (choices.isEmpty()) {
                    throw RgxGenParseException("Empty nodes")
                }
                resultNode = Choice(
                    aCharIterator.substringToCurrPos(startPos),
                    *choices.toTypedArray()
                )
            } else {
                if (nodes.isEmpty()) {
                    throw RgxGenParseException("Empty nodes")
                }
                resultNode = community.flock.kotlinx.rgxgen.nodes.Sequence(
                    aCharIterator.substringToCurrPos(startPos),
                    *nodes.toTypedArray()
                )
            }
        }

        aNodesStartPos[resultNode] = startPos
        if (captureGroupIndex == null) {
            return resultNode
        } else {
            val group = Group(aCharIterator.substringToCurrPos(startPos), captureGroupIndex, resultNode)
            aNodesStartPos[group] = startPos
            return group
        }
    }

    /**
     * It should be called when aCharIterator is on first character after opening bracket - [
     *
     * @return Node representing expression in square brackets
     */
    private fun handleSquareBrackets(): Node {
        val openSquareBraceIndex = aCharIterator.prevPos()
        val matchType = determineSymbolSetMatchType(aCharIterator)
        val characters = StringBuilder(aCharIterator.remaining())
        val symbolRanges: MutableList<SymbolRange> = ArrayList()
        val symbolSets: MutableList<SymbolSet> = ArrayList()
        var rangeStarted = false

        while (aCharIterator.hasNext()) {
            val c = aCharIterator.next()
            when (c) {
                ']' -> {
                    val pattern = aCharIterator.substringToCurrPos(openSquareBraceIndex)
                    val finalSymbolSet = createSymbolSetFromSquareBrackets(
                        pattern,
                        matchType,
                        characters.toString(),
                        symbolRanges,
                        symbolSets
                    )
                    aNodesStartPos[finalSymbolSet] = openSquareBraceIndex
                    return finalSymbolSet
                }

                '-' -> if (aCharIterator.peek() == ']' || aCharIterator.peek(-2) == '[') {
                    characters.append('-')
                } else {
                    rangeStarted = true
                }

                '\\' -> {
                    val symbolSet = handleBackslashInsideSquareBrackets(characters)

                    if (rangeStarted) {
                        if (symbolSet != null) {
                            throw RgxGenParseException("Cannot make range with a shorthand escape sequences before '" + aCharIterator.context() + '\'')
                        }
                        handleSymbolRange(characters, symbolRanges)
                    } else {
                        symbolSet?.let { e -> symbolSets.add(e) }
                    }
                    rangeStarted = false
                }

                else -> {
                    characters.append(c)
                    if (rangeStarted) {
                        handleSymbolRange(characters, symbolRanges)
                        rangeStarted = false
                    }
                }
            }
        }

        throw RgxGenParseException(
            "Unexpected End Of Expression. Didn't find closing ']'" + aCharIterator.context(
                openSquareBraceIndex
            )
        )
    }

    private fun handleBackslashInsideSquareBrackets(characters: StringBuilder): SymbolSet? {
        // Skip backslash and add next symbol to characters
        val nodes: MutableList<Node> = ArrayList()

        val sb = StringBuilder()
        handleEscapedCharacter(sb, nodes, false)
        characters.append(sb)

        if (nodes.isEmpty()) {
            return null
        }

        if (nodes.size > 1) {
            throw RgxGenParseException("Multiple nodes found inside square brackets escape sequence before '" + aCharIterator.context() + '\'')
        } else {
            return nodes[0] as SymbolSet
        }
    }

    fun build() {
        aNode = parseGroup(aCharIterator.prevPos() + 1, GroupType.NON_CAPTURE_GROUP)
        if (aCharIterator.hasNext()) {
            throw RgxGenParseException("Expression was not fully parsed: " + aCharIterator.context())
        }
    }

    override fun get(): Node? {
        if (aNode == null) {
            build()
        }
        return aNode
    }

    companion object {
        private fun assertCorrectCharacter(currentChar: Char) {
            if (currentChar != '^' && currentChar != '$') {
                throw RgxGenParseException("This method should not be called for character '$currentChar'. Please inform developers.")
            }
        }

        private fun getMatchType(parsedCharacter: Char, positiveMatchCharacter: Char): MatchType {
            return if (parsedCharacter == positiveMatchCharacter) MatchType.POSITIVE else MatchType.NEGATIVE
        }

        private fun determineSymbolSetMatchType(charIterator: CharIterator): MatchType {
            if (charIterator.peek() == '^') {
                charIterator.skip()
                return MatchType.NEGATIVE
            } else {
                return MatchType.POSITIVE
            }
        }

        private fun handleSymbolRange(characters: StringBuilder, symbolRanges: MutableCollection<SymbolRange>) {
            // If we're here, then previous character was '-'.
            // But dash can be used in such way: [a-c-]. In this case last dash is only a character, not a range start.
            if (characters.length < 2) {
                characters.append('-')
            } else {
                val lastChar = characters[characters.length - 1]
                val firstChar = characters[characters.length - 2]
                characters.deleteRange(characters.length - 2, characters.length)
                symbolRanges.add(range(firstChar, lastChar))
            }
        }

        private fun createSymbolSetFromSquareBrackets(
            pattern: String?,
            matchType: MatchType,
            sb: String,
            externalRanges: List<SymbolRange>,
            externalSets: Collection<SymbolSet>
        ): SymbolSet {
            val positiveMatchDefinitions = RgxGenCharsDefinition.of(externalRanges)
            if (!sb.isEmpty()) {
                positiveMatchDefinitions.withCharacters(*sb.toCharArray())
            }

            var isAscii = true
            val hasModifiedExclusionChars =
                externalSets.any { obj: SymbolSet -> obj.hasModifiedExclusionChars() }
            val negativeMatchDefinitions =
                if (hasModifiedExclusionChars) RgxGenCharsDefinition.of(positiveMatchDefinitions) else null

            for (symbolSet in externalSets) {
                isAscii = isAscii && symbolSet.isAscii
                positiveMatchDefinitions
                    .withCharacters(symbolSet.symbols)
                    .withRanges(symbolSet.symbolRanges)
                if (hasModifiedExclusionChars) {
                    if (symbolSet.hasModifiedExclusionChars()) {
                        negativeMatchDefinitions!!.addAll(symbolSet.negativeMatchExclusionChars!!)
                    } else {
                        negativeMatchDefinitions
                            ?.withCharacters(symbolSet.symbols)
                            ?.withRanges(symbolSet.symbolRanges)
                    }
                }
            }

            return if (isAscii) {
                ofAscii(pattern!!, positiveMatchDefinitions, negativeMatchDefinitions, matchType)
            } else {
                ofUnicode(pattern!!, positiveMatchDefinitions, negativeMatchDefinitions, matchType)
            }
        }
    }
}
