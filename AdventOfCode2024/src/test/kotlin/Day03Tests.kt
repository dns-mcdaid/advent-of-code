import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Tests {

    private val testData: String = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    private val realData: String = Parser.readText("input_03.txt")

    @Test
    fun `Test data passes part 1`() {
        val result = Day03.performMultiplications(testData)
        assertEquals(161, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day03.performMultiplications(realData)
        assertEquals(173419328, result)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day03.performMultiplicationsWithActions(testData)
        assertEquals(48, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day03.performMultiplicationsWithActions(realData)
        assertEquals(90669332, result)
    }
}
