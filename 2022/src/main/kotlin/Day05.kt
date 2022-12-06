object Day05 {

    data class Instruction(
        val quantity: Int,
        val source: Int,
        val destination: Int,
    )

    data class Input(
        val stacks: List<List<Char>>,
        val instructions: List<Instruction>,
    )

    private fun arrangeStacksRecursive(
        input: Input,
        grabMultipleAtOnce: Boolean,
    ): Input {
        if (input.instructions.isNotEmpty()) {
            val instruction = input.instructions.first()
            val source = input.stacks[instruction.source]
            val leaving = source.subList(0, instruction.quantity)
            val updatedSource = source.subList(instruction.quantity, source.size)
            val leavingToAdd = if (grabMultipleAtOnce) leaving else leaving.reversed()
            val updatedDestination = leavingToAdd + input.stacks[instruction.destination]
            return arrangeStacksRecursive(
                Input(
                    stacks = input.stacks.mapIndexed { index, list ->
                        when (index) {
                            instruction.source -> updatedSource
                            instruction.destination -> updatedDestination
                            else -> list
                        }
                    },
                    instructions = input.instructions.subList(1, input.instructions.size),
                ),
                grabMultipleAtOnce = grabMultipleAtOnce,
            )
        } else {
            return input
        }
    }

    fun findTopElements(
        input: Input,
        grabMultipleAtOnce: Boolean = false,
    ): String {
        val finalStacks = arrangeStacksRecursive(input, grabMultipleAtOnce)
        return finalStacks.stacks.joinToString("") { stack ->
            "${stack.firstOrNull() ?: ' '}"
        }
    }

    fun prepareData(input: List<String> = Parser.readLines("input_05.txt")): Input {
        val stackOfStacks = mutableListOf<MutableList<Char>>()
        val (instructions, stacks) = input.partition {
            it.trim().firstOrNull()?.isLetter() == true
        }
        stacks.forEach { line ->
            if (line.isBlank()) return@forEach
            line.chunked(4).forEachIndexed { index, string ->
                val substack = stackOfStacks.getOrNull(index) ?: run {
                    val s = mutableListOf<Char>()
                    stackOfStacks.add(s)
                    return@run s
                }
                val character = string[1]
                if (character.isDigit() || character.isWhitespace()) return@forEachIndexed
                substack.add(character)
            }
        }

        val actualInstructions = instructions.map { instruction ->
            val breakdown = instruction.split(" ")
            val quantity = breakdown[1]
            val source = breakdown[3]
            val destination = breakdown[5]
            return@map Instruction(
                quantity = quantity.toInt(),
                source = source.toInt() - 1,
                destination = destination.toInt() - 1,
            )
        }

        return Input(stackOfStacks, actualInstructions)
    }
}
