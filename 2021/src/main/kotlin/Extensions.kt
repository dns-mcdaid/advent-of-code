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

fun <T> List<List<T>>.column(index: Int) : List<T> {
    return map { it[index] }
}

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
