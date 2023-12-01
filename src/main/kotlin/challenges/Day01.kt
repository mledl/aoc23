package challenges

class Day01(private val input: List<String>) {
    fun getPart1(): Int = input.sumOf { craftNumber(it) }

    fun getPart2(): Int = input.map { transpileNumber(it) }.sumOf { craftNumber(it) }

    private val numberMap = mapOf(
        "nine" to "n9e",
        "eight" to "e8t",
        "seven" to "s7n",
        "six" to "s6x",
        "five" to "f5e",
        "four" to "f4r",
        "three" to "t3e",
        "two" to "t2o",
        "one" to "o1e")

    private fun craftNumber(text: String): Int = "${text.first{ it.isDigit() }}${text.last{ it.isDigit() }}".toInt()

    private fun transpileNumber(text: String): String = numberMap.entries.fold(text) { acc, entry -> acc.replace(entry.key, entry.value) }
}
