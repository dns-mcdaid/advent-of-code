import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day05 {

    data class Movement(
        val start: Point,
        val end: Point,
    ) {
        private val maxX: Int
            get() = max(start.x, end.x)
        private val minX: Int
            get() = min(start.x, end.x)
        private val maxY: Int
            get() = max(start.y, end.y)
        private val minY: Int
            get() = min(start.y, end.y)

        val canDrawNonDiagonalLine: Boolean
            get() = start.x == end.x || start.y == end.y

        val canDrawDiagonalLine: Boolean
            get() = abs(maxX - minX) == abs(maxY - minY)

        val pointsBetween: List<Point>
            get() = when {
                start.x == end.x -> (minY..maxY).map { y ->
                    Point(start.x, y)
                }
                start.y == end.y -> (minX..maxX).map { x ->
                    Point(x, start.y)
                }
                canDrawDiagonalLine -> {
                    val points = mutableListOf(start)
                    var currentX = start.x
                    var currentY = start.y
                    while (currentX != end.x && currentY != end.y) {
                        currentX += if (currentX < end.x) 1 else -1
                        currentY += if (currentY < end.y) 1 else -1
                        points.add(Point(currentX, currentY))
                    }
                    points
                }
                else -> emptyList()
            }
    }

    fun findMostDangerousPoints(
        movements: List<Movement>,
        allowDiagonal: Boolean,
    ): Int {
        val pointsDict = movements.filter { movement ->
            movement.canDrawNonDiagonalLine || allowDiagonal && movement.canDrawDiagonalLine
        }
            .fold(mutableMapOf<Point, Int>()) { dict, movement ->
            movement.pointsBetween.fold(dict) { innerDict, point ->
                innerDict[point] = (innerDict[point] ?: 0) + 1
                innerDict
            }
        }
        return pointsDict.filter { it.value >= 2 }.size
    }

    private fun buildPoint(str: String): Point {
        val (xStr, yStr) = str.split(",").map { it.trim() }
        check(xStr.isNotBlank() && yStr.isNotBlank())
        return Point(
            xStr.toInt(),
            yStr.toInt(),
        )
    }

    fun prepareData(input: List<String> = Parser.readLines("input_05.txt")): List<Movement> {
        return input.filter { it.isNotBlank() }
            .map { line ->
                val (startStr, endStr) = line.split("->").map { it.trim() }
                Movement(
                    buildPoint(startStr),
                    buildPoint(endStr),
                )
            }
    }
}
