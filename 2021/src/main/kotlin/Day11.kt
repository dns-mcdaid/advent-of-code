object Day11 {

    private fun increment(grid: List<List<Int>>): List<List<Int>> = grid.map { line ->
        line.map { it + 1 }
    }

    private fun flash(grid: List<List<Int>>) : List<List<Int>> {
        val mutable = grid.map { it.toMutableList() }.toMutableList()
        val initialFlashers = grid.
    }

    fun countFlashes(grid: List<List<Int>>, steps: Int): Int {

    }

    fun prepareData(lines: List<String>) = lines.toIntGrid()
}
