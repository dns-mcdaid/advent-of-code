import org.junit.Test
import kotlin.test.assertEquals

class Day02Tests {

    private val testData = listOf(
        Instruction(
            direction = Direction.FORWARD,
            amount = 5,
        ),
        Instruction(
            direction = Direction.DOWN,
            amount = 5,
        ),
        Instruction(
            direction = Direction.FORWARD,
            amount = 8,
        ),
        Instruction(
            direction = Direction.UP,
            amount = 3,
        ),
        Instruction(
            direction = Direction.DOWN,
            amount = 8,
        ),
        Instruction(
            direction = Direction.FORWARD,
            amount = 2,
        ),
    )

    @Test
    fun `Test data passes Part 01`() {
        val result = Day02.determinePosition(testData)
        assertEquals(150, result)
    }

    @Test
    fun `Part 01 actual input passes`() {
        val data = Day02.prepareData()
        assertEquals(1670340, Day02.determinePosition(data))
    }

    @Test
    fun `Test data passes Part 02`() {
        val result = Day02.determinePositionWithAim(testData)
        assertEquals(900, result)
    }

    @Test
    fun `Part 02 actual input passes`() {
        val data = Day02.prepareData()
        assertEquals(1954293920, Day02.determinePositionWithAim(data))
    }
}