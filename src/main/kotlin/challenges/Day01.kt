package challenges

class Day01(private val input: List<String>) {
    fun getPart1(): Int = input.sumOf { craftNumber(it) }

    private fun craftNumber(text: String): Int = "${text.first{ it.isDigit() }}${text.last{ it.isDigit() }}".toInt()
}
