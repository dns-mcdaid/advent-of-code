object Day01 {

    fun countTimesDepthIncreases(measurements: List<Int>): Int {
        return measurements.windowed(size = 2)
            .filter { (first, second) -> second > first }
            .size
    }

    fun countWindowedDepthIncreases(measurements: List<Int>): Int {
        return measurements.windowed(size = 3)
            .map { it.sum() }
            .let(::countTimesDepthIncreases)
    }


    fun prepareData(): List<Int> {
        return Parser.readLines("input_01.txt").map { it.toInt() }
    }
}
