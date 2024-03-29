import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Tests {

    private val testData1: List<String> = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent()
        .split("\n")

    private val testData2: List<String> = """
        two1nine
        eightwothree
        abcone2threexyz
        xtwone3four
        4nineeightseven2
        zoneight234
        7pqrstsixteen
    """.trimIndent()
        .split("\n")

    private val realData = Parser.readLinesFilteringBlank("input_01.txt")

    @Test
    fun `Test data passes part 1`() {
        val result = Day01.sumCalibrationValues(testData1)
        assertEquals(142, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day01.sumCalibrationValues(realData)
        assertEquals(54081, result)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day01.sumCalibrationValues(
            input = testData2,
            includeWords = true,
        )
        assertEquals(281, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day01.sumCalibrationValues(
            input = realData,
            includeWords = true,
        )
        assertEquals(54649, result)
    }
}
