import org.junit.Test
import kotlin.test.assertEquals

class Day07Tests {

    private val testData = """
        ${'$'} cd /
        ${'$'} ls
        dir a
        14848514 b.txt
        8504156 c.dat
        dir d
        ${'$'} cd a
        ${'$'} ls
        dir e
        29116 f
        2557 g
        62596 h.lst
        ${'$'} cd e
        ${'$'} ls
        584 i
        ${'$'} cd ..
        ${'$'} cd ..
        ${'$'} cd d
        ${'$'} ls
        4060174 j
        8033020 d.log
        5626152 d.ext
        7214296 k
    """.trimIndent()
        .split("\n")

    @Test
    fun `Test data passes part 1`() {
        val result = Day07.calculateSumOfDirectories(testData)
        assertEquals(95437, result)
    }

    @Test
    fun `Real data passes part 1`() {
        val result = Day07.calculateSumOfDirectories()
        assertEquals(1667443, result)
    }

    @Test
    fun `Test data passes part 2`() {
        val result = Day07.findMinimumLargestDirectoryForDeletion(testData)
        assertEquals(24933642, result)
    }

    @Test
    fun `Real data passes part 2`() {
        val result = Day07.findMinimumLargestDirectoryForDeletion()
        assertEquals(8998590, result)
    }
}
