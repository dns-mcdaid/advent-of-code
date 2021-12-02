object Day02 {

    data class Instruction(
        val direction: String,
        val amount: Int,
    )

    data class Position(
        val horizontal: Int = 0,
        val depth: Int = 0,
        val aim: Int = 0,
    )

    private fun getFinalPosition(inputs: List<Instruction>): Position {
        return inputs.fold(Position()) { current, instruction ->
            when (instruction.direction) {
                "forward" -> current.copy(
                    horizontal = current.horizontal + instruction.amount,
                    depth = current.depth + (current.aim * instruction.amount),
                )
                "up" -> current.copy(aim = current.aim - instruction.amount)
                "down" -> current.copy(aim = current.aim + instruction.amount)
                else -> throw IllegalArgumentException("Unsupported direction: ${instruction.direction}")
            }
        }
    }

    fun determinePosition(inputs: List<Instruction>): Int {
        val position = getFinalPosition(inputs)
        return position.horizontal * position.aim
    }

    fun determinePositionWithAim(inputs: List<Instruction>): Int {
        val position = getFinalPosition(inputs)
        return position.horizontal * position.depth
    }

    fun prepareData(): List<Instruction> {
        return Parser.readLines("input_02.txt")
            .map { it.split(" ") }
            .map { (direction, amount) ->
                Instruction(
                    direction = direction,
                    amount = amount.toInt(),
                )
            }
    }
}
