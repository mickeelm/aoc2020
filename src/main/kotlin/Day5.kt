fun highestSeatId(boardingPasses: List<String>): Int = boardingPasses.maxOf { seatId(it) }

fun mySeatId(boardingPasses: List<String>): Int =
    boardingPasses.map { seatId(it) }.sorted().zipWithNext().first { it.isNotAdjacent() }.run { first + 1 }

fun seatId(boardingPass: String): Int = boardingPass.toBinary().toInt(2)

private fun String.toBinary() = toCharArray().map { binaryMappings(it) }.joinToString(separator = "")
private fun Pair<Int, Int>.isNotAdjacent() = second - first != 1

private fun binaryMappings(direction: Char) =
    when (direction) {
        'F', 'L' -> '0'
        'B', 'R' -> '1'
        else -> throw RuntimeException("Invalid direction in boarding pass: $direction")
    }
