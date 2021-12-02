import org.junit.Test
import kotlin.test.assertEquals
import Day02.Instruction

class Day02Tests : DayTest<List<Instruction>> {

    override val testData = listOf(
        Instruction(
            direction = "forward",
            amount = 5,
        ),
        Instruction(
            direction = "down",
            amount = 5,
        ),
        Instruction(
            direction = "forward",
            amount = 8,
        ),
        Instruction(
            direction = "up",
            amount = 3,
        ),
        Instruction(
            direction = "down",
            amount = 8,
        ),
        Instruction(
            direction = "forward",
            amount = 2,
        ),
    )

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day02.determinePosition(testData)
        assertEquals(150, result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val data = Day02.prepareData()
        assertEquals(1670340, Day02.determinePosition(data))
    }

    @Test
    override fun `Part 02 test data passes`() {
        val result = Day02.determinePositionWithAim(testData)
        assertEquals(900, result)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val data = Day02.prepareData()
        assertEquals(1954293920, Day02.determinePositionWithAim(data))
    }
}