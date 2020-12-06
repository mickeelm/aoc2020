import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day6 {

    @Test
    internal fun part1_example() {
        assertEquals(11, anyYesCount(asStringSequence("day6_example")))
    }

    @Test
    internal fun part1() {
        assertEquals(6778, anyYesCount(asStringSequence("day6_input")))
    }

    @Test
    internal fun part2_example() {
        assertEquals(6, allYesCount(asStringSequence("day6_example")))
    }

    @Test
    internal fun part2() {
        assertEquals(3406, allYesCount(asStringSequence("day6_input")))
    }
}