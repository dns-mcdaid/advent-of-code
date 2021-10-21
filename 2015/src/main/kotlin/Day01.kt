object Day01 {

    fun findFloor(instructions: String): Int {
        var currentFloor = 0
        for (instruction in instructions) {
            when (instruction) {
                '(' -> currentFloor++
                ')' -> currentFloor--
                else -> {
                    println("UNEXPECTED INSTRUCTION: $instruction")
                    // no-op
                }
            }
        }
        return currentFloor
    }

    fun findFirstTimeBasementIsEntered(instructions: String): Int {
        var currentFloor = 0
        instructions.forEachIndexed { index, instruction ->
            when (instruction) {
                '(' -> currentFloor++
                ')' -> currentFloor--
                else -> {
                    println("UNEXPECTED INSTRUCTION: $instruction")
                    // no-op
                }
            }
            if (currentFloor < 0) return index + 1
        }
        return -1
    }
}
