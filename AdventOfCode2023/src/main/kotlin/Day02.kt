import kotlin.math.max

object Day02 {

    enum class Color(val internalValue: String) {
        Blue("blue"),
        Green("green"),
        Red("red"),
        ;
    }

    private val defaultLimits: Map<Color, Int> = mapOf(
        Color.Blue to 14,
        Color.Red to 12,
        Color.Green to 13,
    )

    data class Game(
        val id: Int,
        val rounds: List<Map<Color, Int>>,
    )

    private fun gameIsValid(
        game: Game,
        limits: Map<Color, Int> = defaultLimits,
    ): Boolean {
        return game.rounds.all { round ->
            round.entries.all { it.value <= limits[it.key]!! }
        }
    }

    private fun computePower(game: Game): Int {
        val minimums = game.rounds.reduce { acc, curr ->
            Color.entries.associateWith { color ->
                val accColor = acc[color] ?: 0
                val currColor = curr[color] ?: 0
                max(accColor, currColor)
            }
        }

        return minimums
            .map { it.value }
            .fold(1) { acc, curr ->
                acc * curr
            }
    }

    private fun constructGame(input: String): Game {
        val idDelimiter = input.indexOf(':')
        val gameTitle = input.substring(
            startIndex = 0,
            endIndex = idDelimiter,
        )
        val rounds = input.substring(
            startIndex = idDelimiter + 1,
        )
            .split(";")
            .map { round ->
                round.split(",").associate { playString ->
                    val trimmed = playString.trim()
                    val (count, color) = trimmed.split(" ")
                    val colorType = Color.entries.first { it.internalValue == color }
                    val countInt = count.toInt()
                    colorType to countInt
                }
            }

        val (_, gameId) = gameTitle.split(" ")
        return Game(
            id = gameId.toInt(),
            rounds = rounds,
        )
    }

    fun sumMatchingGames(input: List<String>): Int {
        val rounds = input.map(::constructGame)
        val validRounds = rounds.filter(::gameIsValid)
        return validRounds.sumOf { it.id }
    }

    fun sumPowerOfGames(input: List<String>): Int {
        val rounds = input.map(::constructGame)
        val powers = rounds.map(::computePower)
        return powers.sum()
    }
}
