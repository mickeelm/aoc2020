fun expenseReportTwoEntries(input: MutableList<Int>): Int {
    while (input.isNotEmpty()) {
        val last = input.removeLast()
        input.forEach { if (it + last == 2020) return it * last }
    }
    throw RuntimeException("Invalid input")
}

fun expenseReportThreeEntries(input: List<Int>): Int {
    eligiblePairs(input.toMutableList()).forEach { (first, second) ->
        input.forEach {
            if (first + second + it == 2020) return first * second * it
        }
    }
    throw RuntimeException("Invalid input")
}

private fun eligiblePairs(input: MutableList<Int>): Sequence<Pair<Int, Int>> = sequence {
    while (input.isNotEmpty()) {
        val last = input.removeLast()
        input.forEach { if (it + last <= 2020) yield(Pair(it, last)) }
    }
}