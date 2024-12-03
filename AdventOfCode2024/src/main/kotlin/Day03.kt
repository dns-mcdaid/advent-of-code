object Day03 {

    private val mulFinder = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")

    fun performMultiplications(input: String): Int {
        return mulFinder.findAll(input).map { result ->
            result.value.drop(4)
                .dropLast(1)
                .split(",")
                .map { it.toInt() }
                .fold(1) { acc, i ->
                    acc * i
                }
        }.sum()
    }

    fun performMultiplicationsWithActions(input: String): Int {
        val dontBlocks = input.split("don't()")
        return dontBlocks.mapIndexed { index, block ->
            if (index == 0) {
                performMultiplications(block)
            } else {
                block.split("do()")
                    .drop(1)
                    .sumOf(::performMultiplications)
            }
        }.sum()
    }
}
