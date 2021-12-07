import org.junit.Test
import kotlin.test.assertEquals

class Day07Tests : DayTest<List<Int>> {

    override val testData = listOf(
        16,1,2,0,4,2,7,1,2,14
    )

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day07.calculateLowestFuelCost(testData)
        assertEquals(37, result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day07.calculateLowestFuelCost(Day07.prepareData())
        assertEquals(340052, result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val result = Day07.calculateLowestWeightedFuelCost(testData)
        assertEquals(206, result)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val result = Day07.calculateLowestWeightedFuelCost(Day07.prepareData())
        assertEquals(92948968, result)
    }
}