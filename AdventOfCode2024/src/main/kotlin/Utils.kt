fun String.splitWhitespace(): List<String> {
    return split("\\s+".toRegex())
}

fun String.splitNewLines(): List<String> {
    return split("\n")
}


data class Point(
    val x: Int,
    val y: Int,
) {

    fun next(direction: CardinalDirection) : Point {
        return when (direction) {
            CardinalDirection.N -> copy(
                y = y + 1,
            )
            CardinalDirection.S -> copy(
                y = y - 1,
            )
            CardinalDirection.E -> copy(
                x = x + 1,
            )
            CardinalDirection.W -> copy(
                x = x - 1,
            )
            CardinalDirection.NE -> copy(
                x = x + 1,
                y = y + 1,
            )
            CardinalDirection.NW -> copy(
                x = x - 1,
                y = y + 1,
            )
            CardinalDirection.SE -> copy(
                x = x + 1,
                y = y - 1,
            )
            CardinalDirection.SW -> copy(
                x = x - 1,
                y = y - 1,
            )
        }
    }
}

enum class CardinalDirection {
    N,
    S,
    E,
    W,
    NE,
    NW,
    SE,
    SW,
    ;
}
