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
                val pathsToCurrent = map.removeOrDefault(joltage, 1)
                lookAhead.filter { it <= joltage + 3 }.forEach { map.increase(it, pathsToCurrent) }
            }
        }).values.first()

private fun HashMap<Long, Long>.removeOrDefault(key: Long, default: Long): Long = remove(key) ?: default

private fun HashMap<Long, Long>.increase(key: Long, increment: Long) {
    val currentValue = this[key] ?: 0
    this[key] = currentValue + increment
}

private fun sortAndAddEnds(input: List<Long>): List<Long> =
    input.sorted().toMutableList().apply { add(0, 0); add(last() + 3) }
