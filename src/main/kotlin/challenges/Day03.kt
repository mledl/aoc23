package challenges

class Day03(private val input: List<String>) {
    private val offset = input[0].length + 1
    private val inputStr = input.fold("") { acc, s -> acc.plus("$s.") }
    private val regex = "\\d+".toRegex()
    fun getPart1(): Int = calcRanges(inputStr, offset, input.size).filter { pair -> pair.second.any { !isDigitOrDot(inputStr[it]) } }.sumOf { it.first }

    fun getPart2(): Int {
        var sum = 0
        val ranges = input.map { regex.findAll(it).flatMap { match -> match.range.toList().map { it to match.value.toInt() } }.toMap() }

        for ((lineIdx, line) in input.withIndex()) {
            var gearIdx = line.indexOf('*')

            while (gearIdx >= 0) {
                val factors = arrayListOf<Int>()

                // check line above
                if (lineIdx > 0) {
                    if (gearIdx > 0) ranges[lineIdx - 1][gearIdx - 1]?.let { factors.add(it) }
                    if (ranges[lineIdx - 1][gearIdx - 1] != ranges[lineIdx - 1][gearIdx]) ranges[lineIdx - 1][gearIdx]?.let { factors.add(it) }
                    if (gearIdx < line.length && ranges[lineIdx - 1][gearIdx] != ranges[lineIdx - 1][gearIdx + 1]) ranges[lineIdx - 1][gearIdx + 1]?.let { factors.add(it) }
                }

                // check same line
                if (gearIdx > 0) ranges[lineIdx][gearIdx - 1]?.let { factors.add(it) }
                if (gearIdx < line.length) ranges[lineIdx][gearIdx + 1]?.let { factors.add(it) }

                // check below line
                if (lineIdx < input.size) {
                    if (gearIdx > 0) ranges[lineIdx + 1][gearIdx - 1]?.let { factors.add(it) }
                    if (ranges[lineIdx + 1][gearIdx - 1] != ranges[lineIdx + 1][gearIdx]) ranges[lineIdx + 1][gearIdx]?.let { factors.add(it) }
                    if (gearIdx < line.length && ranges[lineIdx + 1][gearIdx] != ranges[lineIdx + 1][gearIdx + 1]) ranges[lineIdx + 1][gearIdx + 1]?.let { factors.add(it) }

                }

                if (factors.size == 2) sum += factors[0] * factors[1]

                gearIdx = line.indexOf('*', gearIdx + 1)
            }
        }

        return sum
    }

    // Part 1 helper
    private fun calcRanges(inputStr: String, offset: Int, lines: Int): Sequence<Pair<Int, List<Int>>> = "\\d+".toRegex().findAll(inputStr).map { match ->
            var base = match.range.toList()
            if (match.range.first % offset != 0) base = base.plus(match.range.first - 1)
            if (match.range.last % offset != offset-1) base = base.plus(match.range.last + 1)

            var valuesOfInterest = base.map { it - offset }.plus(base.map { it + offset })

            if (match.range.first % offset != 0) valuesOfInterest = valuesOfInterest.plus(match.range.first - 1)
            if (match.range.last % offset != offset-1) valuesOfInterest = valuesOfInterest.plus(match.range.last + 1)

            valuesOfInterest = valuesOfInterest.filter { it >= 0 && it < offset * lines }

            match.value.toInt() to valuesOfInterest
        }

    private fun isDigitOrDot(c: Char): Boolean = c.isDigit() || c == '.'


    // Part 2 helper

}
