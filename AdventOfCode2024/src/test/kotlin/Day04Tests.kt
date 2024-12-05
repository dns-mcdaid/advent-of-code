import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Tests {

    private val testData: List<String> = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """
        .trimIndent()
        .splitNewLines()

    private val realData = Parser.readLines("input_04.txt")
        .filter { it.isNotBlank() }

    @Test
    fun `Test data passes part 1`() {
        val result = Day04.findXmas(testData)
        assertEquals(18, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day04.findXmas(realData)
        assertEquals(2514, result)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day04.liveMas(testData)
        assertEquals(9, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day04.liveMas(realData)
        assertEquals(1888, result)
    }
}
