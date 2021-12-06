import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class Day06Tests : DayTest<List<Int>> {
    override val testData: List<Int> = listOf(
        3,4,3,1,2
    )

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day06.calculateNumberOfLanternFish(testData, days = 80)
        assertEquals(BigDecimal(5934), result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day06.calculateNumberOfLanternFish(Day06.prepareData(), days = 80)
        assertEquals(BigDecimal(374927), result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val result = Day06.calculateNumberOfLanternFish(testData, days = 256)
        assertEquals(BigDecimal(26984457539), result)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val result = Day06.calculateNumberOfLanternFish(Day06.prepareData(), days = 256)
        assertEquals(BigDecimal(1687617803407), result)
    }
}
