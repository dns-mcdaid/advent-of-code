import kotlin.math.abs

data class Point(
    val x: Int,
    val y: Int,
) {

    fun absoluteDistance(other: Point): Int {
        return abs(other.x - x) + abs(other.y - y)
    }

    companion object {
        val Zero: Point = Point(0, 0)
    }
}

enum class Direction {
    Up,
    Down,
    Left,
    Right,
    ;
}
