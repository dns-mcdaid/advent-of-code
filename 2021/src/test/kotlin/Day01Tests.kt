import org.junit.Test
import kotlin.test.assertEquals

class Day01Tests : DayTest<List<Int>> {

    override val testData = listOf(
        199,
        200,
        208,
        210,
        200,
        207,
        240,
        269,
        260,
        263,
    )

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day01.countTimesDepthIncreases(testData)
        assertEquals(7, result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val data = Day01.prepareData()
        val result = Day01.countTimesDepthIncreases(data)
        assertEquals(1559, result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val result = Day01.countWindowedDepthIncreases(testData)
        assertEquals(5, result)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val data = Day01.prepareData()
        val result = Day01.countWindowedDepthIncreases(data)
        assertEquals(1600, result)
    }
}
