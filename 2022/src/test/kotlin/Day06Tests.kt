import org.junit.Test
import kotlin.test.assertEquals

class Day06Tests {

    private val testData = "mjqjpqmgbljsphdztnvjfqwrcgsmlb"
    private val realData = Parser.readText("input_06.txt")


    @Test
    fun `Test data passes part 1`() {
        val result = Day06.findStartOfPacket(testData)
        assertEquals(7, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day06.findStartOfPacket(realData)
        assertEquals(1142, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day06.findStartOfPacket(realData, 14)
        println("Result: $result")
    }
}
