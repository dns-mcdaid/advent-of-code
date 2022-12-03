object Day03 {

    private val charRange: List<Char> =
        ('a'..'z').toList() + ('A'..'Z').toList()

    private fun getRucksackCompartments(rucksack: String): Pair<String, String> {
        val left = rucksack.substring(0, rucksack.length / 2)
        val right = rucksack.substring(rucksack.length / 2)
        return left to right
    }

    private fun findDuplicateChar(strings: List<String>): Char {
        if (strings.size < 2) throw IllegalStateException("Need to call this with multiple strings!")
        val firstString = strings.first()
        val otherStrings = strings.subList(1, strings.size)
        return firstString.first { char ->
            otherStrings.all { string ->
                char in string
            }
        }
    }

    fun getTotalPriorities(rucksacks: List<String>): Int {
        return rucksacks.fold(0) { acc, rucksack ->
            val (left, right) = getRucksackCompartments(rucksack)
            val duplicate = findDuplicateChar(listOf(left, right))
            val duplicateValue = charRange.indexOf(duplicate) + 1
            return@fold acc + duplicateValue
        }
    }

    fun getTotalBadgedPriorities(rucksacks: List<String>): Int {
        return rucksacks.chunked(3).fold(0) { acc, rucksackGroup ->
            val duplicate = findDuplicateChar(rucksackGroup)
            val duplicateValue = charRange.indexOf(duplicate) + 1
            return@fold acc + duplicateValue
        }
    }

    fun prepareData(): List<String> {
        return Parser.readLines("input_03.txt")
            .filter { it.isNotBlank() }
    }
}
