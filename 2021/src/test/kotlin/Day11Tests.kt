import org.junit.Test
import kotlin.test.assertEquals

class Day11Tests : DayTest<List<List<Int>>> {
    override val testData: List<List<Int>>
        get() = """
            5483143223
            2745854711
            5264556173
            6141336146
            6357385478
            4167524645
            2176841721
            6882881134
            4846848554
            5283751526
        """.trimIndent()
            .split("\n")
            .let(Day11::prepareData)

    private val simpleTestData: List<List<Int>>
        get() = """
            11111
            19991
            19191
            19991
            11111
        """.trimIndent()
            .split("\n")
            .let(Day11::prepareData)


    @Test
    override fun `Part 01 test data passes`() {
        val tenResult = Day11.countFlashes(testData, 10)
        assertEquals(204, tenResult)
        val hundredResult = Day11.countFlashes(testData, 100)
        assertEquals(1656, hundredResult)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day11.countFlashes(Day11.prepareData(), 100)
        assertEquals(1732, result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val result = Day11.findFirstSynchronizedFlash(testData)
        assertEquals(195, result)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val result = Day11.findFirstSynchronizedFlash(Day11.prepareData())
        assertEquals(290, result)
    }
}