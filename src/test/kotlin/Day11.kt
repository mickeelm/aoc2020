import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class Day11 {

    @Test
    internal fun part1_example() {
        assertEquals(37, seatingStalemateCloseRuling(asStringSequence("day11_example")))
    }

    @Test
    internal fun part1() {
        assertEquals(2489, seatingStalemateCloseRuling(asStringSequence("day11_input")))
    }

    @Test
    internal fun part2_example() {
        assertEquals(26, seatingStalemateWideRuling(asStringSequence("day11_example")))
    }

    @Test
    internal fun part2() {
        assertEquals(2180, seatingStalemateWideRuling(asStringSequence("day11_input")))
    }
}