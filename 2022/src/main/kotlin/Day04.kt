object Day04 {

    private fun containedRange(input: Pair<IntRange, IntRange>): Boolean {
        val (left, right) = input
        val rightContainsLeft = left.first in right && left.last in right
        val leftContainsRight = right.first in left && right.last in left
        return rightContainsLeft || leftContainsRight
    }

    private fun overlappedRange(input: Pair<IntRange, IntRange>): Boolean {
        val contained = containedRange(input)
        val (left, right) = input
        return contained || left.first in right || left.last in right || right.first in left || right.last in left
    }

    fun countFullyContainedSets(input: List<Pair<IntRange, IntRange>>): Int {
        return input.count(::containedRange)
    }

    fun countOverlappedSets(input: List<Pair<IntRange, IntRange>>): Int {
        return input.count(::overlappedRange)
    }

    private fun parseRange(string: String): IntRange {
        val (start, end) = string.split("-")
        return start.toInt()..end.toInt()
    }

    fun prepareData(
        input: List<String> = Parser.readLines("input_04.txt")
    ): List<Pair<IntRange, IntRange>> {
        return input.map { line ->
            val (left, right) = line.split(",")
            parseRange(left) to parseRange(right)
        }
    }
}
