import org.junit.Test

class Day11Tests : DayTest<List<List<Int>>> {
    override val testData: List<List<Int>>
        get() = """
            11111
            19991
            19191
            19991
            11111
        """.trimIndent()
            .split("\n")
            .let(Day11::prepareData)


    @Test
    override fun `Part 01 test data passes`() {
        TODO("Not yet implemented")
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