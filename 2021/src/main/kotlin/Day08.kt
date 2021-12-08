object Day08 {

    data class Input(
        val inputs: List<String>,
        val outputs: List<String>,
    )

    enum class Position {
        TOP,
        BOTTOM,
        MIDDLE,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        ;
    }

    private val allTops = setOf(Position.TOP_LEFT, Position.TOP, Position.TOP_RIGHT)
    private val allBottoms = setOf(Position.BOTTOM_LEFT, Position.BOTTOM, Position.BOTTOM_RIGHT)

    private val chars: CharSequence = "abcdefg"

    private val digitLookup: Map<Set<Position>, Int> = mapOf(
        0 to allTops + allBottoms,
        1 to setOf(Position.TOP_RIGHT, Position.BOTTOM_RIGHT),
        2 to setOf(
            Position.TOP, Position.TOP_RIGHT,
            Position.MIDDLE,
            Position.BOTTOM_LEFT, Position.BOTTOM
        ),
        3 to setOf(
            Position.TOP, Position.TOP_RIGHT,
            Position.MIDDLE,
            Position.BOTTOM, Position.BOTTOM_RIGHT
        ),
        4 to setOf(
            Position.TOP_LEFT, Position.TOP_RIGHT,
            Position.MIDDLE, Position.BOTTOM_RIGHT
        ),
        5 to setOf(
            Position.TOP_LEFT, Position.TOP,
            Position.MIDDLE,
            Position.BOTTOM_RIGHT, Position.BOTTOM
        ),
        6 to setOf(Position.TOP, Position.TOP_LEFT, Position.MIDDLE) + allBottoms,
        7 to setOf(Position.TOP, Position.BOTTOM_RIGHT, Position.TOP_RIGHT),
        8 to allTops + allBottoms + setOf(Position.MIDDLE),
        9 to allTops + setOf(Position.MIDDLE, Position.BOTTOM_RIGHT, Position.BOTTOM)
    )
        .map { (k, v) -> v to k }
        .toMap()

    fun countUniqueOutputs(inputs: List<Input>): Int {
        val uniqueSizes = listOf(2, 4, 3, 7)
        return inputs.map { it.outputs }.flatMap { outputs ->
            outputs.filter { it.length in uniqueSizes }
        }.size
    }

    private fun buildUniqueLookup(digits: List<String>): Map<Int, Set<Char>> {
        val sizeToValue = mapOf(
            2 to 1,
            4 to 4,
            3 to 7,
            7 to 8,
        )
        return digits.mapNotNull { digit ->
            val representing = sizeToValue[digit.length] ?: return@mapNotNull null
            representing to digit.toSet()
        }.toMap()
    }

    private fun inferMappings(
        digitList: List<String>,
        uniques: Map<Int, Set<Char>>,
    ): Map<Char, Position> {
        val digits = digitList.map { it.toSet() }
        val twoThreeOrFive = digits.filter { it.size == 5 }
        val (one, four, seven, _) = uniques.let {
            listOfNotNull(it[1], it[4], it[7], it[8])
        }
        val top = seven.first { it !in one }
        // Middle and top left *have* to be whichever two in 4 are *not* in 1
        val middleTopLeftCandidates = four.filter { it !in one }
        check(middleTopLeftCandidates.size == 2)
        val five = twoThreeOrFive.first { digit ->
            middleTopLeftCandidates.all { it in digit }
        }
        check(top in five)
        val topRightOrBottomLeft = chars.filter { it !in five }
        check(topRightOrBottomLeft.length == 2)
        val topRight = topRightOrBottomLeft.first { it in one }
        val bottomLeft = topRightOrBottomLeft.first { it !in one }
        val bottomRight = one.first { it != topRight }
        val three = twoThreeOrFive.first { digit ->
            one.all { it in digit }
        }
        val middle = middleTopLeftCandidates.first { it in three }
        val topLeft = middleTopLeftCandidates.first { it !in three }
        val bottom = chars.first {
            it !in listOf(topLeft, top, topRight, middle, bottomLeft, bottomRight)
        }

        return mapOf(
            topLeft to Position.TOP_LEFT,
            top to Position.TOP,
            topRight to Position.TOP_RIGHT,
            middle to Position.MIDDLE,
            bottomLeft to Position.BOTTOM_LEFT,
            bottom to Position.BOTTOM,
            bottomRight to Position.BOTTOM_RIGHT,
        ).also {
            check(it.size == Position.values().size)
        }
    }

    private fun getNecessaryPositions(digit: String, mapping: Map<Char, Position>): Set<Position> {
        return digit.mapNotNull { mapping[it] }.toSet()
    }

    private fun buildDigitToNumericMap(digits: List<String>, mapping: Map<Char, Position>): Map<Set<Char>, Int> {
        return digits.associate { digit ->
            val positions = getNecessaryPositions(digit, mapping)
            val numericDigit = digitLookup[positions] ?: throw IllegalStateException("No digit found for $positions")
            digit.toSet() to numericDigit
        }
    }

    private fun calculateOutputValue(digits: List<String>, mapping: Map<Set<Char>, Int>): Int {
        val ascending = digits.map { it.toSet() }.reversed()
        return ascending.foldIndexed(0) { index, acc, charSet ->
            val match = mapping[charSet] ?: throw IllegalStateException("No digit found for $charSet")
            acc + (match * 10.pow(index))
        }
    }

    fun calculateTotalOutput(inputs: List<Input>): Int {
        return inputs.sumOf { input ->
            // 1. Find your unique ones
            val uniques = buildUniqueLookup(input.inputs)
            // 2. Infer their structure from letter makeup
            val mappings = inferMappings(input.inputs, uniques)
            // 3. Use structure to infer other numbers
            val numericMap = buildDigitToNumericMap(input.inputs, mappings)
            // 4. Compare against outputs to get individual digits
            // 5. Combine those output digits to build a number
            // 6. Sum those numbers
            calculateOutputValue(input.outputs, numericMap)
        }
    }

    private fun String.explodeToDigits(): List<String> =
        split(" ").map { it.trim() }.filter { it.isNotBlank() }

    fun prepareData(lines: List<String> = Parser.readLines("input_08.txt")): List<Input> {
        return lines.filter { it.isNotBlank() }.map { line ->
            val (input, output) = line.split(" | ")
            Input(
                inputs = input.explodeToDigits(),
                outputs = output.explodeToDigits(),
            )
        }
    }
}
