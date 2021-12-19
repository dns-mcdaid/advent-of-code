import org.junit.Test
import kotlin.test.assertEquals

class Day15Tests : DayTest<List<List<Int>>> {
    override val testData: List<List<Int>>
        get() = """
            1163751742
            1381373672
            2136511328
            3694931569
            7463417111
            1319128137
            1359912421
            3125421639
            1293138521
            2311944581
        """.trimIndent()
            .split("\n")
            .let(Day15::prepareData)

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day15.calculateLowestRiskScore(testData)
        assertEquals(40, result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val data = Day15.prepareData()
        val result = Day15.calculateLowestRiskScore(data)
        assertEquals(595, result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        TODO("Not yet implemented")
    }

    @Test
    override fun `Part 02 actual input passes`() {
        TODO("Not yet implemented")
    }
}
