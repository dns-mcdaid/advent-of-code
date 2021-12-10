import java.math.BigDecimal

object Day10 {

    data class LineAnalysis(
        val corruptIndex: Int?,
        val remainingBuffer: List<Char>,
    )

    private val syntaxPointTable: Map<Char, Int> = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137,
    )

    private val autocompletePointTable: Map<Char, Int> = mapOf(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4,
    )

    private val associations = mapOf(
        '(' to ')',
        '[' to ']',
        '{' to '}',
        '<' to '>',
    )

    private fun completeLine(buffer: List<Char>): List<Char> {
        return buffer.reversed().mapNotNull { associations[it] }
    }

    private fun analyzeLine(charSequence: CharSequence): LineAnalysis {
        val buffer = mutableListOf<Char>()
        for (index in charSequence.indices) {
            when (val char = charSequence[index]) {
                in associations.keys -> buffer.add(char)
                else -> {
                    val lastItem = buffer.removeLast()
                    val expectedCloser = associations[lastItem]
                    if (char != expectedCloser) return LineAnalysis(
                        corruptIndex = index,
                        remainingBuffer = buffer,
                    )
                }
            }
        }
        return LineAnalysis(
            corruptIndex = null,
            remainingBuffer = buffer,
        )
    }

    private fun detectCorruption(line: CharSequence): Char? {
        return analyzeLine(line).corruptIndex?.let {
            line.getOrNull(it)
        }
    }

    fun calculateSyntaxScore(lines: List<CharSequence>): BigDecimal {
        return lines.mapNotNull(::detectCorruption)
            .also { println("${it.size} out of ${lines.size} CORRUPT") }
            .groupingBy { it }
            .eachCount()
            .map { (k, v) ->
                v * (syntaxPointTable[k] ?: 1)
            }
            .sum()
            .toBigDecimal()
    }

    private fun calculateAutocorrectLineScore(line: List<Char>): BigDecimal {
        return line.fold(BigDecimal.ZERO) { acc, char ->
            (acc * 5.toBigDecimal()) + BigDecimal(autocompletePointTable[char] ?: 0)
        }
    }

    fun calculateAutocompleteWinner(lines: List<CharSequence>): BigDecimal {
        val completedLines = lines
            .asSequence()
            .map { analyzeLine(it) }
            .filter { it.corruptIndex == null }
            .map { completeLine(it.remainingBuffer) }
            .map(::calculateAutocorrectLineScore)
            .sorted()
            .toList()
        return completedLines[completedLines.size / 2]
    }

    fun prepareData(): List<CharSequence> =
        Parser.readLines("input_10.txt")
}
