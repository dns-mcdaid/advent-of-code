object Day09 {


    data class Command(
        val direction: Direction,
        val units: Int,
    )

    data class Rope(
        val head: Point = Point(0, 0),
        val tail: Point = Point(0, 0),
    ) {

        fun print(maxGrid: Int) {
            for (y in maxGrid downTo 0) {
                val line = (0 until maxGrid).joinToString("") { x ->
                    when (Point(x, y)) {
                        head -> "H"
                        tail -> "T"
                        Point.Zero -> "s"
                        else -> "."
                    }
                }
                println(line)
            }
        }
    }

    fun countThePointsWhereTailHasBeen(commands: List<Command>): Int {
        val flatDirections = commands.flatMap { command ->
            (0 until command.units).map { command.direction }
        }

        val tailSpots = mutableSetOf<Point>()

        val nothing = flatDirections.fold(Rope()) { (head, tail), direction ->
            val newHead = when (direction) {
                Direction.Up -> head.copy(y = head.y + 1)
                Direction.Down -> head.copy(y = head.y - 1)
                Direction.Left -> head.copy(x = head.x - 1)
                Direction.Right -> head.copy(x = head.x + 1)
            }
            val newTailX = when {
                tail.x + 1 < newHead.x -> tail.x + 1
                tail.x - 1 > newHead.x -> tail.x - 1
                else -> tail.x
            }
            val newTailY = when {
                tail.y + 1 < newHead.y -> tail.y + 1
                tail.y - 1 > newHead.y -> tail.y - 1
                else -> tail.y
            }
            val newTail = when {
                tail.absoluteDistance(newHead) > 2 -> head
                else -> Point(newTailX, newTailY)
            }
            tailSpots.add(newTail)
            return@fold Rope(
                head = newHead,
                tail = newTail,
            )
        }
        return tailSpots.count()
    }

    fun prepareData(input: List<String> = Parser.readLines("input_09.txt")): List<Command> {
        return input.filter { it.isNotBlank() }.map { line ->
            val (direction, units) = line.split(" ")
            Command(
                units = units.toInt(),
                direction = when (direction) {
                    "U" -> Direction.Up
                    "D" -> Direction.Down
                    "L" -> Direction.Left
                    "R" -> Direction.Right
                    else -> throw IllegalArgumentException("Unsupported direction: $direction")
                }
            )
        }
    }
}
