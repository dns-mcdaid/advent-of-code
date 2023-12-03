object Day03 {

    private val symbols: List<Char> = listOf('*', '#', '+', '$', '%', '@', '&', '/', '=', '-')

    data class PartNumber(
        val row: Int,
        val start: Int,
        val end: Int,
    ) {

        fun getValue(input: List<String>): Int {
            val line = input[row]
            return if (start == end) {
                line[start].digitToInt()
            } else {
                line.substring(start, end + 1).toInt()
            }
        }
    }

    private fun getPartLimit(
        line: String,
        current: Int,
        nextGenerator: (Int) -> Int,
    ): Int {
        val nextIndex = nextGenerator(current)
        val next = line.getOrNull(nextIndex)
        return if (next?.isDigit() == true) {
            getPartLimit(line, nextIndex, nextGenerator)
        } else {
            current
        }
    }

    private fun getPartNumber(
        input: List<String>,
        point: Point,
    ): PartNumber {
        val row = point.y
        val line = input[row]
        val partStart = getPartLimit(
            line = line,
            current = point.x,
            nextGenerator = { it - 1 },
        )
        val partEnd = getPartLimit(
            line = line,
            current = point.x,
            nextGenerator = { it + 1 },
        )

        return PartNumber(
            row = row,
            start = partStart,
            end = partEnd,
        )
    }

    private fun collectPartNumbers(
        input: List<String>,
        point: Point
    ): List<PartNumber> {
        val maxY = input.size - 1
        val maxX = input[point.y].length - 1
        val adjacentPoints = point.getAdjacentPoints()
        return adjacentPoints
            .filter {
                it.inBounds(maxX, maxY)
            }
            .filter { (x, y) ->
                input[y][x].isDigit()
            }
            .map { adjacentPoint ->
                getPartNumber(input, adjacentPoint)
            }
    }

    fun sumPartNumbers(input: List<String>): Int {
        return input.flatMapIndexed { y: Int, line: String ->
            line.flatMapIndexed { x, char ->
                if (char in symbols) {
                    val point = Point(x, y)
                    collectPartNumbers(input, point)
                } else {
                    emptyList()
                }
            }
        }
            .toSet()
            .sumOf { it.getValue(input) }
    }

    fun sumGearRatios(input: List<String>): Int {
        return input.flatMapIndexed { y: Int, line: String ->
            line.mapIndexed { x, char ->
                if (char == '*') {
                    val point = Point(x, y)
                    val partNumbers = collectPartNumbers(input, point).toSet()
                    if (partNumbers.size == 2) {
                        val values = partNumbers.map { it.getValue(input) }
                        values[0] * values[1]
                    } else {
                        0
                    }
                } else {
                    0
                }
            }
        }.sum()
    }
}
