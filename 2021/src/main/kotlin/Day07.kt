import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.round

object Day07 {

    fun calculateLowestFuelCost(positions: List<Int>): Int {
        return when (positions.size) {
            0, 1 -> 0
            2 -> abs(positions.first() - positions.last())
            else -> {
                val median = positions.sorted()[positions.size / 2]
                positions.sumOf { abs(it - median) }
            }
        }
    }

    // FIXME: Rounding is janky here. Can't work for both test data and input data at the same time.
    fun calculateLowestWeightedFuelCost(positions: List<Int>): Int {
        return when (positions.size) {
            0, 1 -> 0
            2 -> (0..abs(positions.first() - positions.last())).sum()
            else -> {
                val mean = floor((positions.sum().toDouble() / positions.size.toDouble())).toInt()
                positions.sumOf { (0..abs(it - mean)).sum() }
            }
        }
    }

    fun prepareData(): List<Int> {
        return Parser.readIntList("input_07.txt")
    }
}
