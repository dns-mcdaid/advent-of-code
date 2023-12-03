import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Tests {

    private val testData: List<String> = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
    """.trimIndent()
        .split("\n")

    private val realData: List<String> = Parser.readLinesFilteringBlank("input_03.txt")

    @Test
    fun `Test data passes part 1`() {
        val result = Day03.sumPartNumbers(testData)
        assertEquals(4361, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day03.sumPartNumbers(realData)
        assertEquals(536202, result)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day03.sumGearRatios(testData)
        assertEquals(467835, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day03.sumGearRatios(realData)
        assertEquals(78272573, result)
    }
}
