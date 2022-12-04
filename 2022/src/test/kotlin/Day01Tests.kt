import junit.framework.Assert.assertEquals
import org.junit.Test

class Day01Tests {

    private val testData = listOf(
        listOf(
            1000,
            2000,
            3000,
        ),
        listOf(
            4000,
        ),
        listOf(
            5000,
            6000,
        ),
        listOf(
            7000,
            8000,
            9000,
        ),
        listOf(
            10000,
        ),
    )

    @Test
    fun `Test data passes part 1`() {
        val testDataOutput = Day01.calculateMostCalories(testData)
        assertEquals(24000, testDataOutput)
    }

    @Test
    fun `Real data passes part 1`() {
        val input = Day01.prepareData()
        val output = Day01.calculateMostCalories(input)
        assertEquals(68787, output)
    }

    @Test
    fun `Test data passes part 2`() {
        val testDataOutput = Day01.calculateTopThreeCalories(testData)
        assertEquals(45000, testDataOutput)
    }

    @Test
    fun `Real data passes part 2`() {
        val input = Day01.prepareData()
        val output = Day01.calculateTopThreeCalories(input)
        println(output)
    }
}
