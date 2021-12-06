import java.math.BigDecimal

object Day06 {

    fun calculateNumberOfLanternFish(input: List<Int>, days: Int): BigDecimal {
        val dayToCount = input.groupingBy { it }
            .eachCount()
            .mapValues { (_, v) ->
                v.toBigDecimal()
            }
            .toMutableMap()

        return (1..days).fold(dayToCount) { dict, _ ->
            val newDict = mutableMapOf<Int, BigDecimal>()
            for ((k, v) in dict) {
                when (k) {
                    0 -> {
                        newDict[6] = (newDict[6] ?: BigDecimal.ZERO) + v
                        newDict[8] = v
                    }
                    else -> {
                        newDict[k - 1] = (newDict[k - 1] ?: BigDecimal.ZERO) + v
                    }
                }
            }
            return@fold newDict
        }
            .values
            .fold(BigDecimal.ZERO) { acc, curr ->
                acc.add(curr)
            }
    }

    fun prepareData(): List<Int> {
        return Parser.readText("input_06.txt")
            .trim()
            .split(",")
            .mapNotNull { it.toIntOrNull() }
    }
}
