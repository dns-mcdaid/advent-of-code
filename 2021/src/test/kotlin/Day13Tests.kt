import org.junit.Test
import kotlin.test.assertEquals

class Day13Tests : DayTest<Day13.Input> {
    override val testData: Day13.Input
        get() = """
                6,10
                0,14
                9,10
                0,3
                10,4
                4,11
                6,0
                6,12
                4,1
                0,13
                10,12
                3,4
                3,0
                8,4
                1,10
                2,14
                8,10
                9,0
                
                fold along y=7
                fold along x=5
            """.trimIndent()
                .split("\n")
            .let(Day13::prepareData)

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day13.countVisibleAfterOneFold(testData)
        assertEquals(17, result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day13.countVisibleAfterOneFold(Day13.prepareData())
        assertEquals(795, result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        Day13.printCode(testData)
        // Prints 'O'
    }

    @Test
    override fun `Part 02 actual input passes`() {
        Day13.printCode(Day13.prepareData())
        // Prints 'CEJKLUGJ'
    }
}
