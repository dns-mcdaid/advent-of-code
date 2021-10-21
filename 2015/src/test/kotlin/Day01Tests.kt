import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class Day01Tests {

    data class TestCase(
        val input: String,
        val expected: Int,
    )

    @Test
    fun `Part 1 examples pass`() {
        val examples = listOf(
            TestCase(
                input = "(())",
                expected = 0,
            ),
            TestCase(
                input = "()()",
                expected = 0,
            ),
            TestCase(
                input = "(((",
                expected = 3,
            ),
            TestCase(
                input = "(()(()(",
                expected = 3,
            ),
            TestCase(
                input = "))(((((",
                expected = 3,
            ),
            TestCase(
                input = "())",
                expected = -1,
            ),
            TestCase(
                input = "))(",
                expected = -1,
            ),
            TestCase(
                input = ")))",
                expected = -3,
            ),
            TestCase(
                input = ")())())",
                expected = -3,
            ),
        )
        examples.forEach { testCase ->
            val result = Day01.findFloor(testCase.input)
            assert(testCase.expected == result)
        }
    }

    @Test
    fun `Part 1 input passes`() {
        val input = Parser.read("input_01.txt")
        val expected = 232
        val result = Day01.findFloor(input)
        assert(expected == result)
    }

    @Test
    fun `Part 2 examples pass`() {
        val examples = listOf(
            TestCase(
                input = ")",
                expected = 1,
            ),
            TestCase(
                input = "()())",
                expected = 5,
            ),
        )

        examples.forEach { testCase ->
            val result = Day01.findFirstTimeBasementIsEntered(testCase.input)
            assert(testCase.expected == result)
        }
    }

    @Test
    fun `Part 2 input passes`() {
        val input = Parser.read("input_01.txt")
        val expected = 1783
        val result = Day01.findFirstTimeBasementIsEntered(input)
        assert(expected == result)
    }
}
