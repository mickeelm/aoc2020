import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day1 {

    @Test
    internal fun part1_example() {
        assertEquals(514579, expenseReportTwoEntries(asIntList("day1_example").toMutableList()))
    }
    @Test
    internal fun part1() {
        assertEquals(788739, expenseReportTwoEntries(asIntList("day1_input").toMutableList()))
    }
    @Test
    internal fun part2_example() {
        assertEquals(241861950, expenseReportThreeEntries(asIntList("day1_example")))
    }
    @Test
    internal fun part2() {
        assertEquals(178724430, expenseReportThreeEntries(asIntList("day1_input")))
    }
}