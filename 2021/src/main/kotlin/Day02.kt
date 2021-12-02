
enum class Direction {
    FORWARD,
    UP,
    DOWN,
    ;

    companion object {
        fun from(string: String): Direction {
            return values().first { it.name.lowercase() == string }
        }
    }
}

data class Instruction(
    val direction: Direction,
    val amount: Int,
)

object Day02 {

    data class Position(
        val horizontal: Int,
        val depth: Int,
        val aim: Int,
    )

    private fun getFinalPosition(inputs: List<Instruction>): Position {
        return inputs.fold(
            Position(
                horizontal = 0,
                depth = 0,
                aim = 0,
            )
        ) { current, instruction ->
            when (instruction.direction) {
                Direction.FORWARD -> current.copy(
                    horizontal = current.horizontal + instruction.amount,
                    depth = current.depth + (current.aim * instruction.amount),
                )
                Direction.UP -> current.copy(aim = current.aim - instruction.amount)
                Direction.DOWN -> current.copy(aim = current.aim + instruction.amount)
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
                    direction = Direction.from(direction),
                    amount = amount.toInt(),
                )
            }
    }
}
