import org.junit.Test
import java.math.BigDecimal
import kotlin.test.assertEquals

class Day14Tests : DayTest<Day14.Input> {
    override val testData: Day14.Input
        get() = """
            NNCB

            CH -> B
            HH -> N
            CB -> H
            NH -> C
            HB -> C
            HC -> B
            HN -> C
            NN -> C
            BH -> H
            NC -> B
            NB -> B
            BN -> B
            BB -> N
            BC -> B
            CC -> N
            CN -> C
        """.trimIndent()
            .split("\n")
            .let(Day14::prepareData)

    @Test
    override fun `Part 01 test data passes`() {
        val result = Day14.differenceBetweenLeastAndMost(testData, 10)
        assertEquals(BigDecimal(1588), result)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day14.differenceBetweenLeastAndMost(Day14.prepareData(), 10)
        println(result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val result = Day14.differenceBetweenLeastAndMost(testData, 40)
        println(result)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        TODO("Not yet implemented")
    }
}
