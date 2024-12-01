import kotlin.math.absoluteValue

object Day01 {

    fun findDeltas(listA: List<Int>, listB: List<Int>): Int {
        val aSorted = listA.sorted()
        val bSorted = listB.sorted()
        return aSorted.zip(bSorted) { a, b ->
            (b - a).absoluteValue
        }.sum()
    }

    fun findSimilarities(listA: List<Int>, listB: List<Int>): Int {
        val bCache = mutableMapOf<Int, Int>()
        return listA.sumOf { a ->
            val occurrences = bCache[a] ?: listB.filter { it == a }
                .size
                .also { bCache[a] = it }
            a * occurrences
        }
    }

    fun transformData(input: String): Pair<List<Int>, List<Int>> {
        return input.splitWhitespace()
            .filter { it.isNotBlank() }
            .mapIndexed { index, s ->
                index to s.toInt()
            }
            .partition { it.first % 2 == 0 }
            .let { (a, b) ->
                a.map { it.second } to b.map { it.second }
            }
    }
}
