object Day01 {

    private val spelledNumbersMap: Map<String, Char> = mapOf(
        "one" to '1',
        "two" to '2',
        "three" to '3',
        "four" to '4',
        "five" to '5',
        "six" to '6',
        "seven" to '7',
        "eight" to '8',
        "nine" to '9',
    )

    private fun findCalibrationValue(
        input: String,
        includeWords: Boolean = false,
    ): Int {
        val tens = input.firstOrNull { it.isDigit() }
        val ones = input.lastOrNull { it.isDigit() }

        if (includeWords) {
            val indexOfTens = (tens to input.indexOfFirst { it == tens }).takeIf { tens != null }
            val indexOfOnes = (ones to input.indexOfLast { it == ones }).takeIf { ones != null }

            val speltIndicies = spelledNumbersMap.keys.map { number ->
                Triple(number, input.indexOf(number), input.lastIndexOf(number))
            }.filter { (_, firstIndex, _) ->
                firstIndex >= 0
            }
            val indexOfFirstSpelt = speltIndicies.minByOrNull { (_, index, _) ->
                index
            }?.let { (number, index, _) ->
                spelledNumbersMap[number]!! to index
            }

            val indexOfLastSpelt = speltIndicies.maxByOrNull { (_, _, index) ->
                index
            }?.let { (number, _, index) ->
                spelledNumbersMap[number]!! to index
            }

            val candidates = listOfNotNull(
                indexOfTens,
                indexOfOnes,
                indexOfFirstSpelt,
                indexOfLastSpelt,
            )

            val newTens = candidates.minBy { it.second }
            val newOnes = candidates.maxBy { it.second }

            return "${newTens.first}${newOnes.first}".toInt()
        }
        return "$tens$ones".toInt()
    }

    fun sumCalibrationValues(
        input: List<String>,
        includeWords: Boolean = false,
    ): Int {
        val calibrationValues = input.map { line ->
            findCalibrationValue(
                input = line,
                includeWords = includeWords,
            )
        }.also { println(it) }
        return calibrationValues.sum()
    }

    fun prepareData(): List<String> {
        return Parser.readLines("input_01.txt")
            .filter { it.isNotBlank() }
    }
}
