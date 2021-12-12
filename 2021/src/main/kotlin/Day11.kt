object Day11 {

    data class FlashResult(
        val grid: List<List<Int>>,
        val flashCount: Int,
    ) {
        val allFlashed: Boolean
            get() = grid.size * grid.width == flashCount
    }

    private fun increment(grid: List<List<Int>>): List<List<Int>> = grid.map { line ->
        line.map { it + 1 }
    }

    private fun flash(grid: List<List<Int>>) : FlashResult {
        // 1. Build a stack of points which need to be blown up.
        // 2. Traverse said stack
        // 3. Increment
        // 4. If on incrementing, another node is blown up, do that.
        val mutableGrid = grid.map { it.toMutableList() }.toMutableList()
        val flashPoints = mutableListOf<Point>()
        val flashedPoints = mutableSetOf<Point>()
        for (y in grid.indices) {
            for (x in 0 until grid.width) {
                if (grid[y][x] > 9) flashPoints.add(Point(x, y))
            }
        }

        while (flashPoints.isNotEmpty()) {
            val currentPoint = flashPoints.removeFirst()
            if (currentPoint in flashedPoints) continue
            currentPoint.allAdjacent.filter { it !in flashedPoints }.forEach { point ->
                val adjacentValue = mutableGrid.getOrNull(point) ?: return@forEach
                val newValue = adjacentValue + 1
                mutableGrid[point] = newValue
                if (newValue > 9) flashPoints.add(point)
            }
            flashedPoints.add(currentPoint)
        }
        return FlashResult(
            grid = mutableGrid.map { row ->
                row.map {
                    if (it > 9) 0
                    else it
                }
            },
            flashCount = flashedPoints.size,
        )
    }

    fun countFlashes(grid: List<List<Int>>, steps: Int): Int {
        return (0 until steps).fold(FlashResult(grid, flashCount = 0)) { acc, step ->
            val nextResult = flash(increment(acc.grid))
            return@fold nextResult.copy(flashCount = acc.flashCount + nextResult.flashCount)
        }.flashCount
    }

    fun findFirstSynchronizedFlash(grid: List<List<Int>>): Int {
        var step = 0
        var previousResult = FlashResult(grid, 0)
        while (!previousResult.allFlashed) {
            step++
            val nextResult = flash(increment(previousResult.grid))
            if (nextResult.allFlashed) return step
            previousResult = nextResult
        }
        return -1
    }

    fun prepareData(lines: List<String> = Parser.readLines("input_11.txt")) = lines.toIntGrid()
}
