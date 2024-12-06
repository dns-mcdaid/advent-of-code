object Day05 {

    data class DataState(
        val registry: Registry,
        val updates: List<List<Int>>,
    )

    data class Registry(
        val lookahead: Map<Int, Set<Int>>,
        val lookback: Map<Int, Set<Int>>,
    )

    private fun validateSequence(
        sequence: List<Int>,
        registry: Registry,
    ): Boolean {
        val processedNodes = mutableSetOf<Int>()
        for (item in sequence) {
            val requiredToBeLater = registry.lookahead[item] ?: emptySet()
            if (requiredToBeLater.any { it in processedNodes }) {
                return false
            }
            processedNodes.add(item)
        }
        return true
    }

    private fun constructRegistry(lines: List<String>): Registry {
        val lookahead = mutableMapOf<Int, Set<Int>>()
        val lookback = mutableMapOf<Int, Set<Int>>()

        for (line in lines) {
            val (before, after) = line.trim()
                .split("|")
                .map { it.toInt() }

            val lookaheadSet = lookahead.getOrPut(before) { setOf() }
            lookahead[before] = lookaheadSet + setOf(after)

            val lookbackSet = lookback.getOrPut(after) { setOf() }
            lookback[after] = lookbackSet + setOf(before)
        }

        return Registry(
            lookahead = lookahead,
            lookback = lookback,
        )
    }

    fun findCorrectUpdates(
        dataState: DataState,
    ): Int {
        return dataState.updates
            .filter { it.isNotEmpty() }
            .filter { update ->
                validateSequence(
                    sequence = update,
                    registry = dataState.registry,
                )
            }
            .sumOf { update ->
                println("FOR UPDATE ($update) GOING TO INDEX ${update.size / 2} AND FETCHING ${update[update.size / 2]}")
                update[update.size / 2]
            }
    }

    fun transformData(lines: List<String>): DataState {
        val pivot = lines.indexOfFirst { it.isBlank() }

        return DataState(
            registry = constructRegistry(lines.subList(0, pivot)),
            updates = lines.subList(pivot + 1, lines.size)
                .filter { it.isNotBlank() }
                .map { line ->
                    line.split(",")
                        .map { it.toInt() }
                }
        )
    }
}
