object Day02 {

    enum class Move(val score: Int) {
        Rock(1),
        Paper(2),
        Scissors(3),
    }

    enum class Outcome(val score: Int) {
        Win(6),
        Loss(0),
        Draw(3),
    }

    private fun determineOutcome(
        opponent: Move,
        self: Move,
    ): Outcome {
        if (opponent == self) {
            return Outcome.Draw
        }
        val win = when (opponent) {
            Move.Rock -> self == Move.Paper
            Move.Paper -> self == Move.Scissors
            Move.Scissors -> self == Move.Rock
        }
        return if (win) Outcome.Win else Outcome.Loss
    }

    private fun Char.toMove(): Move {
        return when (this) {
            'A',
            'X' -> Move.Rock
            'B',
            'Y' -> Move.Paper
            'C',
            'Z' -> Move.Scissors
            else -> throw IllegalArgumentException("Unexpected character: $this")
        }
    }

    private fun Char.toOutcome(): Outcome {
        return when (this) {
            'X' -> Outcome.Loss
            'Y' -> Outcome.Draw
            'Z' -> Outcome.Win
            else -> throw IllegalArgumentException("Unexpected character: $this")
        }
    }

    private fun determineMove(
        opponent: Move,
        outcome: Outcome,
    ): Move {
        return when (outcome) {
            Outcome.Win -> when (opponent) {
                Move.Rock -> Move.Paper
                Move.Paper -> Move.Scissors
                Move.Scissors -> Move.Rock
            }
            Outcome.Loss -> when (opponent) {
                Move.Rock -> Move.Scissors
                Move.Paper -> Move.Rock
                Move.Scissors -> Move.Paper
            }
            Outcome.Draw -> opponent
        }
    }

    fun calculateTotal(input: List<String>): Int {
        return input.fold(0) { acc, round ->
            val opponent = round[0].toMove()
            val self = round[2].toMove()
            val outcome = determineOutcome(opponent, self)
            return@fold self.score + outcome.score + acc
        }
    }

    fun calculateTotalCompoensatingForMove(input: List<String>): Int {
        return input.fold(0) { acc, round ->
            val opponent = round[0].toMove()
            val self = round[2].toOutcome()
            val outcome = determineMove(opponent, self)
            return@fold self.score + outcome.score + acc
        }
    }

    fun prepareData(): List<String> {
        return Parser.readLines("input_02.txt")
            .filter { it.isNotBlank() }
    }
}
