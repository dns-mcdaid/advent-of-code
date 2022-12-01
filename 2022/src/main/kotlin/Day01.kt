object Day01 {

    fun calculateMostCalories(input: List<List<Int>>): Int {
        return input.maxOf { calorieSet ->
            calorieSet.sum()
        }
    }

    fun calculateTopThreeCalories(input: List<List<Int>>): Int {
        return input.map { it.sum() }
            .sorted()
            .takeLast(3)
            .sum()
    }

    fun prepareData(): List<List<Int>> {
        val input = Parser.readLines("input_01.txt")
        val output = mutableListOf<List<Int>>()
        val currentStack = mutableListOf<Int>()
        for (line in input) {
            if (line.isBlank()) {
                output.add(currentStack.toList())
                currentStack.clear()
            } else {
                currentStack.add(line.toIntOrNull()!!)
            }
        }
        if (currentStack.isNotEmpty()) {
            output.add(currentStack)
        }
        return output
    }
}
