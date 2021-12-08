import org.junit.Test
import kotlin.test.assertEquals

class Day08Tests : DayTest<List<Day08.Input>> {

    override val testData = """
        be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
        edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
        fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
        fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
        aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
        fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
        dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
        bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
        egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
        gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
    """.trimIndent()
        .split("\n")
        .let(Day08::prepareData)

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day08.countUniqueOutputs(testData)
        assertEquals(26, result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day08.countUniqueOutputs(Day08.prepareData())
        assertEquals(449, result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val oneLineExample = Day08.prepareData(listOf("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"))
        val oneLineResult = Day08.calculateTotalOutput(oneLineExample)
        assertEquals(5353, oneLineResult)
        val testDataResult = Day08.calculateTotalOutput(testData)
        assertEquals(61229, testDataResult)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val result = Day08.calculateTotalOutput(Day08.prepareData())
        assertEquals(968175, result)
    }
}
