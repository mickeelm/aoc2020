import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day9 {

    @Test
    internal fun part1_example() {
        assertEquals(127, findInvalidNumber(asLongList("day9_example"),5,5))
    }

    @Test
    internal fun part1() {
        assertEquals(104054607, findInvalidNumber(asLongList("day9_input"),25,25))
    }

    @Test
    internal fun part2_example() {
        assertEquals(62, contiguousSetSumOfEnds(asLongList("day9_example"),5,5))
    }

    @Test
    internal fun part2() {
        assertEquals(13935797, contiguousSetSumOfEnds(asLongList("day9_input"),25,25))
    }
}