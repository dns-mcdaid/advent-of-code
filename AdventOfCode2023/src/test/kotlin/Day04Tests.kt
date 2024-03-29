import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Tests {

    private val testData: List<String> = """
        Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
        Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
        Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
        Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
        Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
        Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
    """.trimIndent()
        .split("\n")

    private val realData: List<String> = Parser.readLinesFilteringBlank("input_04.txt")

    @Test
    fun `Test data passes part 1`() {
        val results = Day04.getScratchOffScores(testData)
        assertEquals(13, results)
    }

    @Test
    fun `Real data passes part 1`() {
        val results = Day04.getScratchOffScores(realData)
        assertEquals(25010, results)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day04.countCascadingScratchOffs(testData)
        assertEquals(30, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day04.countCascadingScratchOffs(realData)
        assertEquals(9924412, result)
    }
}
