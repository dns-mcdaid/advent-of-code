import java.util.*

object Day15 {

    data class Vertex(
        val point: Point,
        val from: Point,
        val distance: Int,
    )

    private val <T> List<List<T>>.endCoordinate: Point
        get() = Point(width - 1, size - 1)

    private fun inBounds(point: Point, grid: List<List<Int>>): Boolean {
        return !point.isNegative && point.y < grid.size && point.x < grid.width
    }

    private fun dijkstra(grid: List<List<Int>>): Int {
        val assessed = mutableSetOf<Point>()
        val shortestDistances = mutableMapOf<Point, Int>()
        val shortestPaths = mutableMapOf<Point, Point>()

        val start = Point(0,0)
        val initialPoints = start.cardinalAdjacent
            .filter { inBounds(it, grid) }

        initialPoints.forEach { point ->
            shortestPaths[point] = start
            shortestDistances[point] = grid[point]
        }

        val initialVertices = initialPoints
            .map { Vertex(it, start, grid[it]) }

        val pQueue = PriorityQueue<Vertex> { a, b ->
            if (a.distance == b.distance) 0
            else if (a.distance < b.distance) -1
            else 1
        }
        pQueue.addAll(initialVertices)
        assessed.add(start)

        while (pQueue.isNotEmpty()) {
            val current = pQueue.remove()
            val adjacentPoints = current.point.cardinalAdjacent
                .filter { inBounds(it, grid) && it != current.from }

            for (point in adjacentPoints) {
                if (current.distance + grid[point] < (shortestDistances[point] ?: Int.MAX_VALUE)) {
                    shortestDistances[point] = current.distance + grid[point]
                    shortestPaths[point] = current.point
                }
            }

            val adjacentVertices = adjacentPoints
                .map { Vertex(it, current.point, grid[it] + current.distance) }
                .filter { it.point !in assessed }

            assessed.add(current.point)

            pQueue.addAll(adjacentVertices)
        }
        return shortestDistances[grid.endCoordinate]!!
    }

    fun calculateLowestRiskScore(grid: List<List<Int>>): Int {
        return dijkstra(grid)
    }

    fun prepareData(lines: List<String> = Parser.readLines("input_15.txt")) = lines.toIntGrid()
}
