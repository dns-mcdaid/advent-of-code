object Day06 {

    private fun List<Int>.passDay(): List<Int> {
        var newBabies = 0
        return map { fish ->
            when (fish) {
                0 -> 6.also { newBabies++ }
                else -> fish - 1
            }
        } + List(newBabies) { 8 }
    }

    fun calculateNumberOfLanternFish(input: List<Int>, days: Int): Int {
        return (1..days).fold(input) { fish, day ->
            return@fold fish.passDay().also {
//                println("After $day days: $it")
            }
        }.size
    }

    fun prepareData(): List<Int> {
        return Parser.readText("input_06.txt")
            .trim()
            .split(",")
            .mapNotNull { it.toIntOrNull() }
    }
}
