object Day08 {

    private fun isVisible(
        x: Int,
        y: Int,
        grid: List<List<Int>>
    ): Boolean {
        val isExterior = x == 0 || y == 0 || x == grid[0].size - 1 || y == grid.size - 1
        if (isExterior) return true

        val tree = grid[y][x]

        val row = grid[y]
        val column = grid.map { it[x] }

        val isVisibleFromLeft = row.subList(0, x).all { it < tree }
        val isVisibleFromRight = row.subList(x + 1, row.size).all { it < tree }
        val isVisibleFromTop = column.subList(0, y).all { it < tree }
        val isVisibleFromBottom = column.subList(y + 1, column.size).all { it < tree }

        return (isVisibleFromLeft || isVisibleFromTop || isVisibleFromRight || isVisibleFromBottom)
    }

    fun countVisibleTrees(input: List<List<Int>>): Int {
        return (input.indices).map { y ->
            (input[y].indices).filter { x ->
                isVisible(x, y, input)
            }
                .map { it to y }
        }
            .flatten()
            .count()
    }

    fun prepareData(input: List<String> = Parser.readLines("input_08.txt")): List<List<Int>> {
        return input
            .filter { it.isNotBlank() }
            .map { row ->
                row.map { char ->
                    char.digitToInt()
                }
            }
    }
}
