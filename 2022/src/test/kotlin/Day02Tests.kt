import org.junit.Test
import kotlin.test.assertEquals

class Day02Tests {

    private val testData = listOf(
        "A Y",
        "B X",
        "C Z",
    )

    @Test
    fun `Test data passes part 1`() {
        val result = Day02.calculateTotal(testData)
        assertEquals(15, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day02.calculateTotal(Day02.prepareData())
        assertEquals(11150, result)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day02.calculateTotalCompoensatingForMove(testData)
        assertEquals(23, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day02.calculateTotalCompoensatingForMove(Day02.prepareData())
        assertEquals(8295, result)
    }
}
