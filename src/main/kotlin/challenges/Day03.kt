package challenges

class Day03(private val input: List<String>) {

    fun getPart1(): Int {
        val offset = input[0].length + 1
        val inputStr = input.fold("") { acc, s -> acc.plus("$s.") }
        val ranges = "\\d+".toRegex().findAll(inputStr).map { match ->
            var base = match.range.toList()
            if (match.range.first % offset != 0) base = base.plus(match.range.first - 1)
            if (match.range.last % offset != offset-1) base = base.plus(match.range.last + 1)

            var valuesOfInterest = base.map { it - offset }.plus(base.map { it + offset })

            if (match.range.first % offset != 0) valuesOfInterest = valuesOfInterest.plus(match.range.first - 1)
            if (match.range.last % offset != offset-1) valuesOfInterest = valuesOfInterest.plus(match.range.last + 1)

            valuesOfInterest = valuesOfInterest.filter { it >= 0 && it < offset * input.size }

            match.value.toInt() to valuesOfInterest
        }

        return ranges.filter { pair -> pair.second.any { !isDigitOrDot(inputStr[it]) } }.sumOf { it.first }
    }

    fun getPart2(): Int = 1

    // Part 1 helper
    private fun isDigitOrDot(c: Char): Boolean = c.isDigit() || c == '.'


    // Part 2 helper

}
