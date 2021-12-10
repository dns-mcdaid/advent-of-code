import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class Day10Tests : DayTest<List<CharSequence>> {
    override val testData: List<String>
        get() = """
            [({(<(())[]>[[{[]{<()<>>
            [(()[<>])]({[<{<<[]>>(
            {([(<{}[<>[]}>{[]{[(<()>
            (((({<>}<{<{<>}{[]{[]{}
            [[<[([]))<([[{}[[()]]]
            [{[{({}]{}}([{[{{{}}([]
            {<[[]]>}<{[{[{[]{()[[[]
            [<(<(<(<{}))><([]([]()
            <{([([[(<>()){}]>(<<{{
            <{([{{}}[<[[[<>{}]]]>[]]
        """.trimIndent()
            .split("\n")

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day10.calculateSyntaxScore(testData)
        assertEquals(BigDecimal(26397), result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day10.calculateSyntaxScore(Day10.prepareData())
        assertEquals(BigDecimal(168417), result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val result = Day10.calculateAutocompleteWinner(testData)
        assertEquals(BigDecimal(288957), result)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val result = Day10.calculateAutocompleteWinner(Day10.prepareData())
        assertEquals(BigDecimal(2802519786), result)
    }

}