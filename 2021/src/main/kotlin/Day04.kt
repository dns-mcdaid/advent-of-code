object Day04 {

    private const val BOARD_SIZE = 5

    data class Input(
        val numbersCalled: List<Int>,
        val bingoBoards: List<List<List<Int>>>,
    )

    data class WinningResult(
        val board: List<List<Int>>,
        val lastCalled: Int,
        val processed: Set<Int>,
    ) {
        val finalScore: Int
            get() = board.flatten().filter { it !in processed }.sum() * lastCalled
    }

    private fun checkIfWinner(
        candidate: List<List<Int>>,
        processed: Set<Int>,
        lastCalled: Int,
    ): WinningResult? {
        for (i in 0 until BOARD_SIZE) {
            val rowHits = candidate[i].all { it in processed }
            val columnHits = candidate.column(i).all { it in processed }
            if (rowHits || columnHits) return WinningResult(candidate, lastCalled, processed)
        }
        return null
    }

    private fun findWinner(
        candidates: List<List<List<Int>>>,
        processed: Set<Int>,
        lastCalled: Int,
    ) : WinningResult? {
        for (candidate in candidates) {
            return checkIfWinner(candidate, processed, lastCalled) ?: continue
        }
        return null
    }

    fun solveBingo(input: Input): Int {
        val processed = mutableSetOf<Int>()
        for (number in input.numbersCalled) {
            processed.add(number)
            return findWinner(input.bingoBoards, processed, number)
                ?.finalScore
                ?: continue
        }
        return -1
    }

    fun findWorstBingo(input: Input): Int {
        val processed = mutableSetOf<Int>()
        var leftOvers = input.bingoBoards
        var lastBatch = listOf<WinningResult>()
        for (number in input.numbersCalled) {
            processed.add(number)
            lastBatch = leftOvers.mapNotNull { findWinner(leftOvers, processed, number) }
                .takeIf { it.isNotEmpty() }
                ?: lastBatch
            leftOvers = leftOvers.filter { checkIfWinner(it, processed, number) == null }
            if (lastBatch.size == 1 && leftOvers.isEmpty()) break
        }
        return lastBatch.firstOrNull()?.finalScore ?: -1
    }

    fun prepareData(): Input {
        val lines = Parser.readLines("input_04.txt")
        val numbersCalled = lines.first()
            .split(",")
            .map { it.toInt() }
        val bingoBoards = mutableListOf<List<List<Int>>>()
        var lineNumber = 1
        while (lineNumber < lines.size) {
            val bingoBoard = mutableListOf<List<Int>>()
            while (lineNumber < lines.size && lines[lineNumber].isNotBlank()) {
                bingoBoard.add(
                    lines[lineNumber]
                        .split("\\s+".toRegex())
                        .filter { it.isNotBlank() }
                        .map { it.toInt() }
                )
                lineNumber++
            }
            if (bingoBoard.isNotEmpty()) bingoBoards.add(bingoBoard)
            lineNumber++
        }
        check(bingoBoards.all { it.size == BOARD_SIZE && it.column(0).size == BOARD_SIZE })
        return Input(
            numbersCalled,
            bingoBoards,
        )
    }
}
