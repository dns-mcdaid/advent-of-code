object Day13 {

    sealed class Fold(open val index: Int) {
        data class Horizontal(override val index: Int) : Fold(index)
        data class Vertical(override val index: Int) : Fold(index)
    }

    data class Input(
        val points: List<Point>,
        val folds: List<Fold>,
    )

    private fun foldX(points: List<Point>, index: Int): List<Point> {
        val leftHalf = points.filter { it.x < index }
        val rightHalf = points.filter { it.x > index }
        val mappedRightHalf = rightHalf.map { point ->
            point.copy(x = index + (index - point.x))
        }
        return (leftHalf + mappedRightHalf).toSet().toList()
    }

    private fun foldY(points: List<Point>, index: Int): List<Point> {
        val topHalf = points.filter { it.y < index }
        val bottomHalf = points.filter { it.y > index }
        val mappedBottomHalf = bottomHalf.map { point ->
            point.copy(y = index + (index - point.y))
        }
        return (topHalf + mappedBottomHalf).toSet().toList()
    }

    fun countVisibleAfterOneFold(input: Input): Int {
        return when (val fold = input.folds.first()) {
            is Fold.Horizontal -> foldX(input.points, fold.index)
            is Fold.Vertical -> foldY(input.points, fold.index)
        }.size
    }

    fun printCode(input: Input) {
        val finalPoints = input.folds.fold(input.points) { acc, curr ->
            when (curr) {
                is Fold.Horizontal -> foldX(acc, curr.index)
                is Fold.Vertical -> foldY(acc, curr.index)
            }
        }
        val maxY = finalPoints.maxOf { it.y }
        val maxX = finalPoints.maxOf { it.x }
        val lookup = finalPoints.groupBy { it.y }
            .mapValues { (_, v) -> v.map { it.x } }

        for (y in 0..maxY) {
            val xPoints = lookup[y] ?: emptyList()
            (0..maxX).joinToString("") { x ->
                if (x in xPoints) "#" else "."
            }.let(::println)
        }
    }


    fun prepareData(lines: List<String> = Parser.readLines("input_13.txt")): Input {
        return lines.filter { it.isNotBlank() }
            .fold(
                Input(
                    points = emptyList(),
                    folds = emptyList()
                )
            ) { acc, line ->
                if (line.startsWith("fold along")) {
                    val (direction, index) = line.split(" ").last().split("=")
                    acc.copy(
                        folds = acc.folds + listOf(
                            when (direction) {
                                "x" -> Fold.Horizontal(index.toInt())
                                "y" -> Fold.Vertical(index.toInt())
                                else -> throw IllegalArgumentException("Unexpected direction: $direction")
                            }
                        )
                    )
                } else {
                    val (x, y) = line.split(",").map { it.toInt() }
                    acc.copy(
                        points = acc.points + listOf(Point(x, y))
                    )
                }
            }
    }
}
