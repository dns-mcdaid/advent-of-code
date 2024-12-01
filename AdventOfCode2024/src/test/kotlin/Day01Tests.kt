import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Tests {

    private val testData = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """
        .trimIndent()
        .let(Day01::transformData)

    private val realData = Parser.readText("input_01.txt")
        .let(Day01::transformData)

    @Test
    fun `Test data passes part 1`() {
        val (a, b) = testData
        val result = Day01.findDeltas(a, b)
        assertEquals(11, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val (a, b) = realData
        val result = Day01.findDeltas(a, b)
        assertEquals(1882714, result)
    }

    @Test
    fun `Test data passes part 2`() {
        val (a, b) = testData
        val result = Day01.findSimilarities(a, b)
        assertEquals(31, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val (a, b) = realData
        val result = Day01.findSimilarities(a, b)
        assertEquals(19437052, result)
    }
}
