import Status.EMPTY
import Status.OCCUPIED

private typealias Position = Pair<Int, Int>

private enum class Status { EMPTY, OCCUPIED; }

fun seatingStalemateCloseRuling(input: Sequence<String>): Int = generateSequence(
    input.asSeatingPlan(),
    { plan ->
        plan.applyRules(seatReleaseThreshold = 4) { position ->
            position.directlyAdjacent().count { plan[it] == OCCUPIED }
        }
    })
    .countOccupiedAfterRuleApplication()
    .repeatUntilStalemate()

fun seatingStalemateWideRuling(input: Sequence<String>): Int {
    val seatingPlan = input.asSeatingPlan()
    val (maxRow, maxCol) = seatingPlan.keys.maxByOrNull { it.first + it.second } ?: throw RuntimeException("Bad input.")
    return generateSequence(seatingPlan,
        { plan ->
            plan.applyRules(seatReleaseThreshold = 5) { plan.countOccupiedWide(it, maxRow, maxCol) }
        })
        .countOccupiedAfterRuleApplication()
        .repeatUntilStalemate()
}

private fun Sequence<Map<Position, Status>>.countOccupiedAfterRuleApplication() =
    map { plan -> plan.values.count { it == OCCUPIED } }

private fun Sequence<Int>.repeatUntilStalemate() =
    reduce { lastCount, current -> if (lastCount == current) return current else current }

private fun Map<Position, Status>.applyRules(
    seatReleaseThreshold: Int,
    occupancyCounter: (Position) -> Int
): Map<Position, Status> =
    mapValues { entry ->
        val (position, currentStatus) = entry
        val occupancies = occupancyCounter(position)

        if (currentStatus == EMPTY && occupancies == 0) OCCUPIED
        else if (currentStatus == OCCUPIED && occupancies >= seatReleaseThreshold) EMPTY
        else currentStatus
    }

private fun Map<Position, Status>.countOccupiedWide(position: Position, maxRow: Int, maxCol: Int) =
    position.compassRanges(maxRow, maxCol).count { direction ->
        direction.mapNotNull { this[it] }.firstOrNull() == OCCUPIED
    }

private fun Pair<Int, Int>.compassRanges(maxRow: Int, maxCol: Int) = listOf(
    sequence { northRange().forEach { yield(Position(it, second)) } },
    sequence { northRange().zip(eastRange(maxCol)).forEach { yield(Position(it.first, it.second)) } },
    sequence { eastRange(maxCol).forEach { yield(Position(first, it)) } },
    sequence { southRange(maxRow).zip(eastRange(maxCol)).forEach { yield(Position(it.first, it.second)) } },
    sequence { southRange(maxRow).forEach { yield(Position(it, second)) } },
    sequence { southRange(maxRow).zip(westRange()).forEach { yield(Position(it.first, it.second)) } },
    sequence { westRange().forEach { yield(Position(first, it)) } },
    sequence { northRange().zip(westRange()).forEach { yield(Position(it.first, it.second)) } }
)

private fun Pair<Int, Int>.northRange() = first - 1 downTo 0
private fun Pair<Int, Int>.eastRange(maxCol: Int) = second + 1..maxCol
private fun Pair<Int, Int>.southRange(maxRow: Int) = first + 1..maxRow
private fun Pair<Int, Int>.westRange() = second - 1 downTo 0

private fun Pair<Int, Int>.directlyAdjacent(): List<Pair<Int, Int>> = listOf(
    Pair(first - 1, second - 1), Pair(first - 1, second), Pair(first - 1, second + 1), Pair(first, second + 1),
    Pair(first, second - 1), Pair(first + 1, second - 1), Pair(first + 1, second), Pair(first + 1, second + 1)
)

private fun Sequence<String>.asSeatingPlan(): Map<Position, Status> =
    HashMap<Position, Status>().apply {
        this@asSeatingPlan.forEachIndexed { row, string ->
            string.forEachIndexed { col, char ->
                if (char == 'L') put(Pair(row, col), EMPTY)
            }
        }
    }
