import org.junit.Test
import kotlin.test.assertEquals

class Day16Tests : DayTest<String> {
    override val testData: String
        get() = ""


    @Test
    fun `Binary Long value parses`() {
        assertEquals("100".binaryLongValue, 4L)
        assertEquals("0100".binaryLongValue, 4L)
    }

    @Test
    fun `Data is prepared`() {
        val scenarios = listOf(
            "D2FE28" to "110100101111111000101000",
            "38006F45291200" to "00111000000000000110111101000101001010010001001000000000",
            "EE00D40C823060" to "11101110000000001101010000001100100000100011000001100000",
        )

        scenarios.forEach { (input, output) ->
            assertEquals(Day16.prepareData(input), output)
        }
    }

    @Test
    fun `Digestion works`() {
        val firstResult = Day16.digest(Day16.prepareData("D2FE28"), 0)
        assertEquals(2021L, firstResult.value)
        val secondResult = Day16.digest(Day16.prepareData("38006F45291200"), 0)
        println(secondResult)
    }

    @Test
    override fun `Part 01 test data passes`() {
        val scenarios = listOf(
//            "8A004A801A8002F478" to 16L,
            "620080001611562C8802118E34" to 12L
        )

        scenarios.forEach { (input, expected) ->
            val parsed = Day16.prepareData(input)
            val result = Day16.getVersionSum(parsed)
            println(result)
        }
    }

    @Test
    override fun `Part 01 actual input passes`() {
        TODO("Not yet implemented")
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
