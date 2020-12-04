import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day3 {

    @Test
    internal fun part1_example() {
        assertEquals(7, countTreeEncounters(asStringList("day3_example"), 3, 1))
    }

    @Test
    internal fun part1() {
        assertEquals(200, countTreeEncounters(asStringList("day3_input"), 3, 1))
    }

    @Test
    internal fun part2_example() {
        assertEquals(336, multiplyTreeEncounters(asStringList("day3_example"), directions()))
    }

    @Test
    internal fun part2() {
        assertEquals(3737923200, multiplyTreeEncounters(asStringList("day3_input"), directions()))
    }

    private fun directions() = listOf(
        Coordinate(1, 1),
        Coordinate(3, 1),
        Coordinate(5, 1),
        Coordinate(7, 1),
        Coordinate(1, 2)
    )
}