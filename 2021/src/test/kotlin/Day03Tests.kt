import org.junit.Test
import kotlin.test.assertEquals

class Day03Tests : DayTest<List<BinaryArray>> {

    override val testData = """
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
    """.trimIndent()
        .split("\n")
        .let(Day03::prepareData)

    @Test
    override fun `Part 01 test data passes`() {
        val testResult = Day03.calculatePowerConsumption(testData)
        assertEquals(198, testResult)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val part1Result = Day03.calculatePowerConsumption(Day03.prepareData())
        assertEquals(4118544, part1Result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val testResult = Day03.calculateLifeSupportRating(testData)
        assertEquals(230, testResult)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val part2Result = Day03.calculateLifeSupportRating(Day03.prepareData())
        assertEquals(3832770, part2Result)
    }
}
