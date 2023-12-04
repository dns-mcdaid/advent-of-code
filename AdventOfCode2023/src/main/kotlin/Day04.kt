import kotlin.math.pow

object Day04 {

    private fun getWinningNumbersScore(winCount: Int): Int {
        return when (winCount) {
            0 -> 0
            1 -> 1
            else -> (2.0.pow(winCount - 1.0)).toInt()
        }
    }

    private fun generateScratchOffMatches(input: String): Int {
        val (_, allNumbers) = input.split(":")
        val (myNumbers, winningNumbers) = allNumbers.split("|")
            .map { numbers ->
                numbers.trim()
                    .splitWhitespace()
                    .filter { numberStr ->
                        numberStr.isNotBlank()
                    }
                    .map { number ->
                        number.toInt()
                    }
            }
        return myNumbers.count { number ->
            number in winningNumbers
        }
    }

    fun getScratchOffScores(input: List<String>): Int {
        return input.map(::generateScratchOffMatches)
            .map(::getWinningNumbersScore)
            .sum()
    }

    fun countCascadingScratchOffs(input: List<String>): Int {
        val matches = input.map(::generateScratchOffMatches)
        val frequencies = matches.foldIndexed(mapOf<Int, Int>()) { index, acc, curr ->
            val mutableMap = acc.toMutableMap()
            val multiplier = mutableMap.getOrPut(index) { 1 }
            return@foldIndexed when (curr) {
                0 -> mutableMap
                else -> {
                    val start = index + 1
                    for (i in start..<start + curr) {
                        mutableMap[i] = (mutableMap[i] ?: 1) + multiplier
                    }
                    mutableMap
                }
            }
        }
        return frequencies
            .filter { it.key < matches.size }
            .values
            .sum()
    }
}
