import org.junit.Test
import kotlin.test.assertEquals

class Day09Tests : DayTest<List<List<Int>>> {

    override val testData = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent()
        .split("\n")
        .let(Day09::prepareData)

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day09.calculateRiskLevel(testData)
        assertEquals(15, result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day09.calculateRiskLevel(Day09.prepareData())
        assertEquals(562, result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val result = Day09.findBasins(testData)
        assertEquals(1134, result)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val result = Day09.findBasins(Day09.prepareData())
        assertEquals(1076922, result)
    }
}
