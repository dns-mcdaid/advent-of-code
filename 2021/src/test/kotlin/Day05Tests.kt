import org.junit.Test
import kotlin.test.assertEquals

class Day05Tests : DayTest<List<Day05.Movement>> {
    override val testData: List<Day05.Movement>
        get() = """
            0,9 -> 5,9
            8,0 -> 0,8
            9,4 -> 3,4
            2,2 -> 2,1
            7,0 -> 7,4
            6,4 -> 2,0
            0,9 -> 2,9
            3,4 -> 1,4
            0,0 -> 8,8
            5,5 -> 8,2
        """.trimIndent()
            .split("\n")
            .let(Day05::prepareData)

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day05.findMostDangerousPoints(testData, allowDiagonal = false)
        assertEquals(5, result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day05.findMostDangerousPoints(Day05.prepareData(), allowDiagonal = false)
        assertEquals(4873, result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val result = Day05.findMostDangerousPoints(testData, allowDiagonal = true)
        assertEquals(12, result)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val result = Day05.findMostDangerousPoints(Day05.prepareData(), allowDiagonal = true)
        println(result)
    }
}
