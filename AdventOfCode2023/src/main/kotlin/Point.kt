data class Point(
    val x: Int,
    val y: Int,
) {

    fun getAdjacentPoints(): List<Point> {
        return listOf(
            x - 1,
            x,
            x + 1,
        ).flatMap { x ->
            listOf(
                y - 1,
                y,
                y + 1,
            ).map { y ->
                Point(x, y)
            }
        }
            .filter { it != this }
    }

    fun inBounds(
        maxX: Int,
        maxY: Int,
        minX: Int = 0,
        minY: Int = 0,
    ): Boolean {
        return x in minX..maxX && y in minY..maxY
    }
}
