fun multiplyDifferences(input: List<Long>): Int {
    val adjustedInput = sortAndAddEnds(input)
    val ones = adjustedInput.zipWithNext().filter { (a, b) -> b - a == 1L }.count()
    val threes = adjustedInput.zipWithNext().filter { (a, b) -> b - a == 3L }.count()

    return ones * threes
}

fun distinctArrangements(input: List<Long>) =
    sortAndAddEnds(input).windowed(4, partialWindows = true, transform = { it.toMutableList() })
        .fold(HashMap<Long, Long>(), { map, lookAhead ->
            val joltage = lookAhead.removeFirst()
            map.apply {
                if (lookAhead.isEmpty()) return@fold map
                val paths = map.remove(joltage) ?: 1
                lookAhead.filter { it <= joltage + 3 }.forEach {
                    val currentValue = map.remove(it) ?: 0
                    map[it] = currentValue + paths
                }
            }
        }).values.first()

private fun sortAndAddEnds(input: List<Long>): List<Long> =
    input.sorted().toMutableList().apply { add(0, 0); add(last() + 3) }
