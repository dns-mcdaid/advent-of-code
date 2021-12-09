object Day09 {

    private fun List<List<Int>>.isLowPoint(x: Int, y: Int): Boolean {
        val current = this[y][x]
        val adjacent = listOfNotNull(
            getOrNull(y)?.getOrNull(x + 1),
            getOrNull(y)?.getOrNull(x - 1),
            getOrNull(y - 1)?.getOrNull(x),
            getOrNull(y + 1)?.getOrNull(x),
        )
        return adjacent.all { it > current }
    }

    private fun findLowPoints(grid: List<List<Int>>): List<Point> {
        return (grid.indices).fold(listOf()) { acc, y ->
            acc + (0 until grid[y].size).filter { x ->
                grid.isLowPoint(x, y)
            }.map { x ->
                Point(x, y)
            }
        }
    }

    fun calculateRiskLevel(grid: List<List<Int>>): Int {
        return findLowPoints(grid).sumOf { position ->
            1 + grid[position.y][position.x]
        }
    }

    fun prepareData(lines: List<String> = Parser.readLines("input_09.txt")): List<List<Int>> {
        return lines.filter { it.isNotBlank() }.map { line ->
            line.map { it - '0' }
        }
    }
}
