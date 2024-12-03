import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Tests {

    private val testData: List<List<Int>> = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """
        .trimIndent()
        .splitNewLines()
        .let(Day02::transformData)

    private val realData: List<List<Int>> = Parser.readLines("input_02.txt")
        .let(Day02::transformData)

    @Test
    fun `Test data passes part 1`() {
        val result = Day02.getSafeReportCount(testData)
        assertEquals(2, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day02.getSafeReportCount(realData)
        assertEquals(326, result)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day02.getTolerableReportCount(testData)
        assertEquals(4, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day02.getTolerableReportCount(realData)
        assertEquals(381, result)
    }
}
