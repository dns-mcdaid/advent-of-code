import java.math.BigDecimal

object Day14 {

    data class Input(
        val seed: String,
        val reference: Map<String, String>,
    )

    private fun findDifferenceBetweenLeastAndMost(str: String): BigDecimal {
        val items = mutableMapOf<Char, BigDecimal>()
        for (char in str) {
            items[char] = (items[char] ?: BigDecimal.ZERO) + BigDecimal(1)
        }

        val maxCount = items.values.maxOrNull() ?: return BigDecimal.ZERO
        val minCount = items.values.minOrNull() ?: return BigDecimal.ZERO
        return maxCount - minCount
    }

    private fun expand(word: String, reference: Map<String, String>): String {
        var newBuffer = ""
        for (i in 1 until word.length) {
            val prev = word[i - 1]
            val curr = word[i]
            val mid = reference["$prev$curr"] ?: return "Fuck"
            newBuffer += "$prev$mid"
            if (i == word.length - 1) newBuffer += curr
        }
        return newBuffer
    }

    fun differenceBetweenLeastAndMost(input: Input, size: Int): BigDecimal {
        println("Start: ${input.seed}")
        println(input.reference.size)
        return (0 until size).fold(input.seed) { str, _ ->
            expand(str, input.reference)
        }.let(::findDifferenceBetweenLeastAndMost)
    }

    fun prepareData(lines: List<String> = Parser.readLines("input_14.txt")): Input {
        val seed = lines.first()
        return (1 until lines.size).filter { lines[it].isNotBlank() }
            .fold(mutableMapOf<String, String>()) { acc, i ->
                val (combo, holySpirit) = lines[i].split(" -> ")
                acc[combo] = holySpirit
                return@fold acc
            }
            .let { ref -> Input(seed, ref) }
    }
}
