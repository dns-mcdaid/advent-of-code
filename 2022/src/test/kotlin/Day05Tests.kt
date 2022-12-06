import org.junit.Test
import kotlin.test.assertEquals

class Day05Tests {
    private val testData = """
            [D]    
        [N] [C]    
        [Z] [M] [P]
         1   2   3 
        
        move 1 from 2 to 1
        move 3 from 1 to 3
        move 2 from 2 to 1
        move 1 from 1 to 2
    """.trimIndent()
        .split("\n")

    @Test
    fun `Test data passes part 1`() {
        val result = Day05.findTopElements(Day05.prepareData(testData))
        assertEquals("CMZ", result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day05.findTopElements(Day05.prepareData())
        assertEquals("QGTHFZBHV", result)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day05.findTopElements(Day05.prepareData(testData), grabMultipleAtOnce = true)
        assertEquals("MCD", result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day05.findTopElements(Day05.prepareData(), grabMultipleAtOnce = true)
        assertEquals("MGDMPSZTM", result)
    }
}
