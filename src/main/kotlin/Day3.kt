fun multiplyTreeEncounters(input: List<String>, directions: List<Coordinate>): Long =
    directions
        .map { (right, down) -> countTreeEncounters(input, right, down).toLong() }
        .reduce { part, count -> part * count }

fun countTreeEncounters(input: List<String>, rightStep: Int, downStep: Int): Int {
    val treePoints = treePoints(input)
    val (rows, cols) = input.let { Coordinate(it.size, it.first().length) }
    val reducedTraversalPoints = reducedTraversalPoints(rows, cols, rightStep, downStep)

    return reducedTraversalPoints.count { it in treePoints }
}

private fun treePoints(input: List<String>): List<Coordinate> = ArrayList<Coordinate>().apply {
    input.forEachIndexed { row, symbols ->
        symbols.forEachIndexed { col, symbol ->
            if (symbol == '#') {
                add(Coordinate(row, col))
            }
        }
    }
}

private fun reducedTraversalPoints(rows: Int, cols: Int, rightStep: Int, downStep: Int): Sequence<Coordinate> =
    sequence {
        val steps = (rows - 1) / downStep
        (1..steps).map {
            val row = (it * downStep).rem(rows)
            val col = (it * rightStep).rem(cols)
            yield(Coordinate(row, col))
        }
    }
