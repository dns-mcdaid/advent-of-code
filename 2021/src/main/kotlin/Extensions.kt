fun Int.pow(power: Int): Int {
    return when (power) {
        0 -> 1
        1 -> this
        else -> this * pow(power - 1)
    }
}

val <T> List<List<T>>.width: Int
    get() {
        val length = first().size
        check(map { it.size }.all { it == length })
        return length
    }

fun <T> List<List<T>>.column(index: Int): List<T> {
    return map { it[index] }
}

data class Point(
    val x: Int,
    val y: Int,
) {

    val cardinalAdjacent: List<Point>
        get() = listOf(
            Point(x, y - 1),
            Point(x, y + 1),
            Point(x - 1, y),
            Point(x + 1, y),
        )

    val ordinalAdjacent: List<Point>
        get() = listOf(
            Point(x - 1, y - 1),
            Point(x + 1, y - 1),
            Point(x - 1, y + 1),
            Point(x + 1, y + 1),
        )

    val allAdjacent: List<Point>
        get() = cardinalAdjacent + ordinalAdjacent
}

operator fun <T> List<List<T>>.get(point: Point): T {
    return this[point.y][point.x]
}

operator fun <T> List<MutableList<T>>.set(point: Point, value: T) {
    this[point.y][point.x] = value
}

fun <T> List<List<T>>.getOrNull(point: Point): T? {
    return getOrNull(point.y)?.getOrNull(point.x)
}

fun CharSequence.toIntList(): List<Int> = map { char ->
    char - '0'
}

fun List<CharSequence>.toIntGrid(): List<List<Int>> =
    filter { it.isNotBlank() }
        .map { it.toIntList() }

// region BinaryArray
/**
 * Like a [BooleanArray] but where I don't have to add .toBooleanArray() to the end of every transformer.
 */
typealias BinaryArray = List<Boolean>

val BinaryArray.flipped: BinaryArray
    get() = map { !it }

val BinaryArray.decimalValue: Int
    get() = reversed().foldIndexed(0) { index, acc, bit ->
        when {
            index == 0 -> acc + if (bit) 1 else 0
            bit -> acc + 2.pow(index)
            else -> acc
        }
    }
// endregion
