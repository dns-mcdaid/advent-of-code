import org.junit.Test
import kotlin.test.assertEquals

class Day04Tests {

    private val testData = """
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
    """.trimIndent()
        .split("\n")

    @Test
    fun `Test data passes part 1`() {
        val result = Day04.countFullyContainedSets(Day04.prepareData(testData))
        assertEquals(2, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day04.countFullyContainedSets(Day04.prepareData())
        assertEquals(530, result)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day04.countOverlappedSets(Day04.prepareData(testData))
        assertEquals(4, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day04.countOverlappedSets(Day04.prepareData())
        assertEquals(903, result)
    }
}
