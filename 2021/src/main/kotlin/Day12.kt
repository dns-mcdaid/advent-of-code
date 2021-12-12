object Day12 {

    sealed class Cave(open val name: String) {
        data class Big(override val name: String): Cave(name)
        data class Small(override val name: String): Cave(name)
        object Start : Cave("start")
        object End: Cave("end")
    }

    private val mappedPaths = mutableMapOf<List<Cave>, List<List<Cave>>>()

    private fun findPaths(
        currentPath: List<Cave>,
        guide: Map<Cave, List<Cave>>,
        shouldIncludeSmall: (List<Cave>, Cave) -> Boolean,
    ): List<List<Cave>> {
        if (currentPath in mappedPaths) return mappedPaths[currentPath]!!

        val nextPaths = when (val last = currentPath.last()) {
            is Cave.End -> return listOf(currentPath)
            !in guide -> return emptyList()
            else -> requireNotNull(guide[last])
        }.filter { cave ->
            when (cave) {
                is Cave.Start -> false
                is Cave.Small -> shouldIncludeSmall(currentPath, cave)
                else -> true
            }
        }.map { currentPath + listOf(it) }

        return nextPaths.flatMap { path ->
            findPaths(path, guide, shouldIncludeSmall).also { childPaths ->
                if (path !in mappedPaths) mappedPaths[path] = childPaths
            }.filter { it.isNotEmpty() }
        }
    }

    fun countPaths(guide: Map<Cave, List<Cave>>): Int {
        val paths = findPaths(listOf(Cave.Start), guide) { currentPath, cave ->
            cave !in currentPath
        }
        mappedPaths.clear()
        return paths.size
    }

    fun countPathsAllowingOneSmallRevisit(guide: Map<Cave, List<Cave>>): Int {
        val paths = findPaths(listOf(Cave.Start), guide) { currentPath, cave ->
            if (cave !in currentPath) return@findPaths true
            val existingSmalls = currentPath.filterIsInstance<Cave.Small>()
            existingSmalls.distinctBy { it.name }.size == existingSmalls.size
        }
        mappedPaths.clear()
        return paths.size
    }

    private fun constructCave(name: String): Cave {
        return when (name) {
            "start" -> Cave.Start
            "end" -> Cave.End
            name.lowercase() -> Cave.Small(name)
            else -> Cave.Big(name)
        }
    }

    fun prepareData(lines: List<String> = Parser.readLines("input_12.txt")): Map<Cave, List<Cave>> {
        return lines.filter { it.isNotBlank() }
            .map { line ->
                val (left, right) = line.split("-").map(::constructCave)
                left to right
            }
            .fold(mutableMapOf()) { map, (left, right) ->
                val existingLeft = map[left] ?: emptyList()
                map[left] = existingLeft + listOf(right)
                val existingRight = map[right] ?: emptyList()
                map[right] = existingRight + listOf(left)
                return@fold map
            }
    }
}
