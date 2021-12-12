import org.junit.Test
import kotlin.test.assertEquals

class Day12Tests : DayTest<Map<Day12.Cave, List<Day12.Cave>>> {
    private val simpleTestData: Map<Day12.Cave, List<Day12.Cave>>
        get() = """
            start-A
            start-b
            A-c
            A-b
            b-d
            A-end
            b-end
        """.trimIndent()
            .split("\n")
            .let(Day12::prepareData)

    private val moderateTestData: Map<Day12.Cave, List<Day12.Cave>>
        get() = """
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc
        """.trimIndent()
            .split("\n")
            .let(Day12::prepareData)

    override val testData: Map<Day12.Cave, List<Day12.Cave>>
        get() = """
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW
        """.trimIndent()
            .split("\n")
            .let(Day12::prepareData)

    @Test
    override fun `Part 01 test data passes`() {
        val simpleResult = Day12.countPaths(simpleTestData)
        assertEquals(10, simpleResult)
        val moderateResult = Day12.countPaths(moderateTestData)
        assertEquals(19, moderateResult)
        val complexResult = Day12.countPaths(testData)
        assertEquals(226, complexResult)
    }

    @Test
    override fun `Part 01 actual input passes`() {
        val result = Day12.countPaths(Day12.prepareData())
        assertEquals(5874, result)
    }

    @Test
    override fun `Part 02 test data passes`() {
        val simpleResult = Day12.countPathsAllowingOneSmallRevisit(simpleTestData)
        val moderateResult = Day12.countPathsAllowingOneSmallRevisit(moderateTestData)
        val complexResult = Day12.countPathsAllowingOneSmallRevisit(testData)
        assertEquals(36, simpleResult)
        assertEquals(103, moderateResult)
        assertEquals(3509, complexResult)
    }

    @Test
    override fun `Part 02 actual input passes`() {
        val result = Day12.countPathsAllowingOneSmallRevisit(Day12.prepareData())
        assertEquals(153592, result)
    }
}
