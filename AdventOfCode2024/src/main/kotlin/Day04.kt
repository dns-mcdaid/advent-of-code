object Day04 {

    private val start = 'X'

    private val nextList = listOf('M', 'A', 'S')

    private fun search(
        lines: List<String>,
        point: Point,
        direction: CardinalDirection,
        letters: List<Char>,
    ): Boolean {
        if (letters.isNotEmpty()) {
            val nextLetter = letters.first()
            val nextPoint = point.next(direction)
            val potentialLetter = lines.getOrNull(nextPoint.y)
                ?.getOrNull(nextPoint.x)
            return if (potentialLetter != nextLetter) {
                false
            } else {
                return search(
                    lines = lines,
                    point = nextPoint,
                    direction = direction,
                    letters = letters.drop(1),
                )
            }
        } else {
            return true
        }
    }

    fun findXmas(lines: List<String>): Int {
        val results = lines.mapIndexed { y, line ->
            line.mapIndexed { x, char ->
                val point = Point(x, y)
                if (char == start) {
                    CardinalDirection.entries
                        .filter { direction ->
                            val result = search(
                                lines = lines,
                                point = point,
                                direction = direction,
                                letters = nextList,
                            )
                            result
                        }
                        .size
                } else {
                    0
                }
            }.sum()
        }
            .sum()

        return results
    }

    private fun checkForMas(
        topLeft: Char,
        topRight: Char,
        bottomLeft: Char,
        bottomRight: Char,
    ): Boolean {
        val charList = listOf(topLeft, topRight, bottomLeft, bottomRight)
        val hasTwoS = charList.filter { it == 'S' }.size == 2
        val hasTwoM = charList.filter { it == 'M' }.size == 2

        return when {
            !hasTwoS || !hasTwoM -> false
            topLeft == bottomRight -> false
            topLeft == topRight -> bottomRight == bottomLeft
            topLeft == bottomLeft -> topRight == bottomRight
            else -> false
        }
    }

    fun liveMas(lines: List<String>): Int {
        val results = lines.mapIndexed { y, line ->
            line.mapIndexed inner@{ x, char ->
                val point = Point(x, y)
                if (char == 'A') {
                    val topLeft = point.next(CardinalDirection.NW)
                    val topRight = point.next(CardinalDirection.NE)
                    val bottomLeft = point.next(CardinalDirection.SW)
                    val bottomRight = point.next(CardinalDirection.SE)

                    val topLeftChar = lines.getOrNull(topLeft.y)
                        ?.getOrNull(topLeft.x) ?: return@inner false
                    val topRightChar = lines.getOrNull(topRight.y)
                        ?.getOrNull(topRight.x) ?: return@inner false
                    val bottomLeftChar = lines.getOrNull(bottomLeft.y)
                        ?.getOrNull(bottomLeft.x) ?: return@inner false
                    val bottomRightChar = lines.getOrNull(bottomRight.y)
                        ?.getOrNull(bottomRight.x) ?: return@inner false
                    checkForMas(
                        topLeft = topLeftChar,
                        topRight = topRightChar,
                        bottomLeft = bottomLeftChar,
                        bottomRight = bottomRightChar,
                    )
                } else {
                    false
                }
            }.filter { it }
                .size
        }.sum()

        return results
    }
}
