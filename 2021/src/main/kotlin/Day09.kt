object Day09 {

    private operator fun List<List<Int>>.get(point: Point): Int {
        return this[point.y][point.x]
    }

    private fun List<List<Int>>.getOrNull(point: Point): Int? {
        return getOrNull(point.y)?.getOrNull(point.x)
    }

    private fun isLowPoint(grid: List<List<Int>>, x: Int, y: Int): Boolean {
        val point = Point(x, y)
        return point.cardinalAdjacent.mapNotNull { adjacent ->
            grid.getOrNull(adjacent)
        }.all {
            it > grid[point]
        }
    }

    private fun findLowPoints(grid: List<List<Int>>): List<Point> {
        return (grid.indices).fold(listOf()) { acc, y ->
            acc + (0 until grid[y].size).filter { x ->
                isLowPoint(grid, x, y)
            }.map { x ->
                Point(x, y)
            }
        }
    }

    fun calculateRiskLevel(grid: List<List<Int>>): Int {
        return findLowPoints(grid).sumOf { (x, y) ->
            1 + grid[y][x]
        }
    }

    private fun findBasinLimits(
        grid: List<List<Int>>,
        currentPoint: Point,
        pointsBeingExplored: Set<Point> = setOf(),
    ): Set<Point> {
        val currentValue = grid[currentPoint]
        val adjacent = currentPoint.cardinalAdjacent.filter { adjacent ->
            val potentialValue = grid.getOrNull(adjacent)
            adjacent !in pointsBeingExplored
                    && potentialValue != null
                    && potentialValue != 9
                    && potentialValue > currentValue
        }
        val newPointsBeingExplored = pointsBeingExplored + adjacent
        return if (adjacent.isNotEmpty()) setOf(currentPoint) + adjacent.flatMap { newPoint ->
            findBasinLimits(
                grid,
                newPoint,
                newPointsBeingExplored,
            )
        }
        else setOf(currentPoint)
    }

    fun findBasins(grid: List<List<Int>>): Int {
        return findLowPoints(grid)
            .map { point -> findBasinLimits(grid, point) }
            .map { it.size }
            .sorted()
            .takeLast(3)
            .reduce { acc, i -> acc * i }
    }

    fun prepareData(lines: List<String> = Parser.readLines("input_09.txt")): List<List<Int>> {
        return lines.toIntGrid()
    }
}
