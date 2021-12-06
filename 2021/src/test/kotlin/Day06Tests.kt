import org.junit.Test
import kotlin.test.assertEquals

class Day06Tests : DayTest<List<Int>> {
    override val testData: List<Int> = listOf(
        3,4,3,1,2
    )

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day06.calculateNumberOfLanternFish(testData, 80)
        assertEquals(5934, result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day06.calculateNumberOfLanternFish(Day06.prepareData(), 80)
        println(result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val result = Day06.calculateNumberOfLanternFish(testData, 256)
        println(result)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        TODO("Not yet implemented")
    }
}
