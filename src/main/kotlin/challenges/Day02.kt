package challenges

class Day02(private val input: List<String>) {
    private val checkMap = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun getPart1(): Int = input.mapIndexed { index, s -> if (checkGame(s.split(";"))) index + 1 else 0 }.sum()

    fun getPart2(): Int = calcPowers(input)

    // Part 1 helper
    private fun checkGame(draws: List<String>): Boolean = draws.all { checkDraw(it) }

    private fun checkDraw(draw: String): Boolean = draw.split(",").all { checkCube(it) }

    private fun checkCube(cube: String): Boolean = cube.trim().split(" ").let {
        it.isNotEmpty() && it.get(0).toInt() <= checkMap.get(it.get(1))!!
    }

    // Part 2 helper
    private fun calcPowers(games: List<String>) = games.sumOf {
        game -> listOf("red", "green", "blue").map {
            "\\d+ $it".toRegex().findAll(game).map {
                it.value.trim().split(" ").first.toInt()
            }.max()
        }.reduce { acc, elem -> acc * elem }
    }
}