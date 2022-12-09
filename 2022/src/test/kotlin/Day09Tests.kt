import org.junit.Test
import kotlin.test.assertEquals

class Day09Tests {

    private val testData = """
        R 4
        U 4
        L 3
        D 1
        R 4
        D 1
        L 5
        R 2
    """.trimIndent()
        .split("\n")

    @Test
    fun `Test data passes part 1`() {
        val commands = Day09.prepareData(testData)
        val result = Day09.countThePointsWhereTailHasBeen(commands)
        assertEquals(13, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val commands = Day09.prepareData()
        val result = Day09.countThePointsWhereTailHasBeen(commands)
        println(result)
    }
}
