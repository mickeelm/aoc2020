import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day10 {

    @Test
    internal fun part1_example1() {
        assertEquals(35, multiplyDifferences(asLongList("day10_example_1")))
    }

    @Test
    internal fun part1_example2() {
        assertEquals(220, multiplyDifferences(asLongList("day10_example_2")))
    }

    @Test
    internal fun part1() {
        assertEquals(2450, multiplyDifferences(asLongList("day10_input")))
    }

    @Test
    internal fun part2_example1() {
        assertEquals(8, distinctArrangements(asLongList("day10_example_1")))
    }

    @Test
    internal fun part2_example2() {
        assertEquals(19208, distinctArrangements(asLongList("day10_example_2")))
    }

    @Test
    internal fun part2() {
        assertEquals(32396521357312, distinctArrangements(asLongList("day10_input")))
    }
}