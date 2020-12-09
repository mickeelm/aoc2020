fun contiguousSetSumOfEnds(input: List<Long>, preamble: Int, noOfSummable: Int): Long {

    val invalidNumber = findInvalidNumber(input, preamble, noOfSummable)
    input.forEachIndexed { index, firstInRange ->
        var (sum, largestAddend, smallestAddend) = Array(3) { firstInRange }
        for (upperIndex in index + 1 until input.size) {
            val lastInRange = input[upperIndex]
            sum += lastInRange
            if (lastInRange > largestAddend) largestAddend = lastInRange
            if (lastInRange < smallestAddend) smallestAddend = lastInRange
            if (sum > invalidNumber) break
            if (sum == invalidNumber) return smallestAddend + largestAddend
        }
    }

    throw RuntimeException("Invalid input! Couldn't find any range summing up to $invalidNumber")
}

fun findInvalidNumber(input: List<Long>, preamble: Int, noOfSummable: Int): Long {

    for (currentIndex in preamble until input.size) {
        val number = input[currentIndex]
        val fromIndex = currentIndex - noOfSummable
        val sortedSummables = input.slice(fromIndex until currentIndex).sorted().toCollection(ArrayDeque())
        var lowestRemaining = sortedSummables.removeFirst()
        var highestRemaining = sortedSummables.removeLast()

        while (sortedSummables.isNotEmpty()) {
            val sum = lowestRemaining + highestRemaining
            if (sum == number) break
            if (lowestRemaining > number / 2) return number
            if (sum < number) lowestRemaining = sortedSummables.removeFirst() else highestRemaining =
                sortedSummables.removeLast()
        }
    }

    throw RuntimeException("Invalid input. Couldn't find any invalid number.")
}