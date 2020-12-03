fun sumTreeEncounters(input: List<String>, directions: List<Pair<Int, Int>>): Long =
    directions
        .map { (right, down) -> countTreeEncounters(input, right, down).toLong() }
        .reduce { part, count -> part * count }

fun countTreeEncounters(input: List<String>, right: Int, down: Int): Int {
    val treePoints = treePoints(input)
    val (width, height) = input.let { Pair(it.first().length, it.size) }
    val reducedTraversalPoints = reducedTraversalPoints(width, height, right, down)

    return reducedTraversalPoints.count { it in treePoints }
}

private fun treePoints(input: List<String>): List<Pair<Int, Int>> = ArrayList<Pair<Int, Int>>().apply {
    input.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { colIndex, symbol ->
            if (symbol == '#') {
                add(Pair(rowIndex, colIndex))
            }
        }
    }
}

private fun reducedTraversalPoints(width: Int, height: Int, right: Int, down: Int): Sequence<Pair<Int, Int>> =
    sequence {
        var currentRow = 0
        var currentCol = 0

        while (currentRow < height - 1) {
            currentRow += down
            currentCol += right
            val reducedRow = currentRow.rem(height)
            val reducedCol = currentCol.rem(width)

            yield(Pair(reducedRow, reducedCol))
        }
    }
