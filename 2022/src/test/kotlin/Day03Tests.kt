import org.junit.Test
import kotlin.test.assertEquals

class Day03Tests {

    private val testData = listOf(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw",
    )

    @Test
    fun `Test data passes part 1`() {
        val result = Day03.getTotalPriorities(testData)
        assertEquals(157, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day03.getTotalPriorities(Day03.prepareData())
        assertEquals(7716, result)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day03.getTotalBadgedPriorities(testData)
        assertEquals(70, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day03.getTotalBadgedPriorities(Day03.prepareData())
        assertEquals(2973, result)
    }
}
