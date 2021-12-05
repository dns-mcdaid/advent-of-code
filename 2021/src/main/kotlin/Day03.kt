object Day03 {

    fun calculatePowerConsumption(lines: List<BinaryArray>): Int {
        val gammaBits = (0 until lines.width).map { index ->
            val ones = lines.filter { it[index] }.size
            val zeroes = lines.size - ones
            return@map ones > zeroes
        }
        val epsilonBits = gammaBits.flipped
        return gammaBits.decimalValue * epsilonBits.decimalValue
    }

    fun calculateLifeSupportRating(lines: List<BinaryArray>): Int {
        val o2bits = lines.filterByColumnColumnCount { zeroes, ones ->
            ones >= zeroes
        }
        val co2bits = lines.filterByColumnColumnCount { zeroes, ones ->
            ones < zeroes && zeroes != ones
        }
        return o2bits.decimalValue * co2bits.decimalValue
    }

    private fun List<BinaryArray>.filterByColumnColumnCount(
        comparator: List<BinaryArray>.(Int, Int) -> Boolean
    ): BinaryArray {
        return (0 until width).fold(this) { candidates, index ->
            if (candidates.size == 1) return@fold candidates
            val ones = candidates.filter { it[index] }.size
            val zeroes = candidates.size - ones
            val result = candidates.comparator(zeroes, ones)
            return@fold candidates.filter { it[index] == result }
        }.first()
    }

    fun prepareData(lines: List<String> = Parser.readLines("input_03.txt")): List<BinaryArray> {
        return lines.map { line ->
            line.map { it == '1' }
        }
    }
}
