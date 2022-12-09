import org.junit.Test
import kotlin.test.assertEquals

class Day08Tests {

    private val testData = """
        30373
        25512
        65332
        33549
        35390
    """.trimIndent()
        .split("\n")
        .let(Day08::prepareData)

    @Test
    fun `Test data passes part 1`() {
        val result = Day08.countVisibleTrees(testData)
        assertEquals(21, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day08.countVisibleTrees(Day08.prepareData())
        println("RESULT: $result")
    }
}
