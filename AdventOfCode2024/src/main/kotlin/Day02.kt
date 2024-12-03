import kotlin.math.absoluteValue

object Day02 {

    private fun reportIsValid(report: List<Int>): Boolean {
        val (first, second) = report
        if (first == second) return false
        val increasing = first < second
        for (i in 0 until report.size - 1) {
            val current = report[i]
            val next = report[i + 1]
            val delta = (next - current).absoluteValue
            return when {
                delta > 3 || current == next -> false
                current < next -> if (increasing) continue else false
                else -> if (!increasing) continue else false
            }
        }
        return true
    }

    fun getSafeReportCount(reports: List<List<Int>>): Int {
        return reports.filter(::reportIsValid).size
    }

    fun getTolerableReportCount(reports: List<List<Int>>): Int {
        return reports.filter { report ->
            val submodules = listOf(report) + List(report.size) { index ->
                report.filterIndexed { innerDex, _ -> innerDex != index }
            }
            submodules.any(::reportIsValid)
        }.size
    }

    fun transformData(input: List<String>): List<List<Int>> {
        return input
            .filter { it.isNotBlank() }
            .map { line ->
                line.splitWhitespace()
                    .map { number ->
                        number.toInt()
                    }
            }
    }
}
