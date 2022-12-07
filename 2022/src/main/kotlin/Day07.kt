object Day07 {

    data class File(
        val name: String,
        val size: Long,
    )

    data class Directory(
        val name: String,
        val files: Set<File>,
        val subdirectories: Set<String>,
    ) {

        val sizeOfFiles: Long
            get() = files.sumOf { it.size }
    }

    private fun ls(
        directoryName: String,
        lines: List<String>,
    ): Directory {
        val files = mutableSetOf<File>()
        val subdirectories = mutableSetOf<String>()
        for (line in lines) {
            val content = line.split(" ")
            when (content[0]) {
                "dir" -> subdirectories.add(content[1])
                else -> files.add(
                    File(
                        name = content[1],
                        size = content[0].toLong()
                    )
                )
            }
        }
        return Directory(
            name = directoryName,
            files = files,
            subdirectories = subdirectories,
        )
    }

    private val sizeSink: MutableMap<String, Long> = mutableMapOf()

    private fun getDirectorySize(
        currentDir: String,
        directories: Map<String, Directory>,
    ): Long {
        val directory = directories[currentDir]!!
        when {
            currentDir in sizeSink -> return sizeSink[currentDir]!!
            directory.subdirectories.isNotEmpty() -> {
                val subSink = directory.subdirectories.sumOf {
                    getDirectorySize(
                        currentDir = "$currentDir/$it",
                        directories = directories,
                    )
                } + directory.sizeOfFiles
                sizeSink[directory.name] = subSink
                return subSink
            }
            else -> {
                sizeSink[directory.name] = directory.sizeOfFiles
                return directory.sizeOfFiles
            }
        }
    }

    private fun buildDirectories(input: List<String>): Map<String, Directory> {
        val directories = mutableMapOf<String, Directory>()
        val navigationStack = mutableListOf<String>()
        var i = 0
        while (i < input.size) {
            val lineElements = input[i].split(" ")
            val canonicalName = navigationStack.joinToString("/")
            when (lineElements[1]) {
                "cd" -> {
                    when (val direction = lineElements[2]) {
                        ".." -> navigationStack.removeLast()
                        else -> {
                            navigationStack.add(direction)
                        }
                    }
                    i++
                }
                "ls" -> {
                    val endIndex = (i + 1 until input.size).firstOrNull { index ->
                        input[index][0] == '$'
                    } ?: input.size

                    val activeDir = navigationStack.last()
                    val directory = ls(
                        directoryName = activeDir,
                        lines = input.subList(i + 1, endIndex)
                    )
                    directories[canonicalName] = directory
                    i = endIndex
                }
            }
        }
        return directories
    }

    private fun debugTree(
        current: String,
        directories: Map<String, Directory>,
        sizes: Map<String, Long>,
        depth: Int,
    ) {
        val tabs = "\t".repeat(depth)
        val dir = directories[current] ?: throw IllegalStateException("Can't handle it!")
        println("$tabs - ${dir.name} (dir) [SIZE=${sizes[current]}]")
        for (file in dir.files) {
            println("$tabs\t ${file.name} (file, size=${file.size})")
        }
        for (subdir in dir.subdirectories) {
            debugTree("$current/$subdir", directories, sizes, depth + 1)
        }
    }


    fun calculateSumOfDirectories(
        input: List<String> = Parser.readLines("input_07.txt"),
    ): Long {
        val directories = buildDirectories(input)
        val sizes = directories.keys.associateWith { getDirectorySize(it, directories) }
        val smallEnoughItems = sizes.filter { it.value <= 100000L }
        return smallEnoughItems.values.sum()
    }

    fun findMinimumLargestDirectoryForDeletion(
        input: List<String> = Parser.readLines("input_07.txt"),
    ): Long {
        val directories = buildDirectories(input)
        val sizes = directories.keys.associateWith { getDirectorySize(it, directories) }
        val rootSize = sizes["/"] ?: throw IllegalStateException("must contain root dir")
        val maxSpace = 70000000
        val minimumForUpdate = 30000000
        val availableSpace = maxSpace - rootSize
        val spaceRequired = minimumForUpdate - availableSpace
        return sizes.values.sorted().first { it >= spaceRequired }
    }
}
