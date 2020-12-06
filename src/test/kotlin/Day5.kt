import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day5 {

    @ParameterizedTest
    @CsvSource("357,FBFBBFFRLR", "567,BFFFBBFRRR", "119,FFFBBBFRRR", "820,BBFFBBFRLL")
    internal fun part1_example_single(seatId: Int, spec: String) {
        assertEquals(seatId, seatId(spec))
    }

    @Test
    internal fun part1_example_highest() {
        assertEquals(820, highestSeatId(asStringList("day5_example")))
    }

    @Test
    internal fun part1() {
        assertEquals(978, highestSeatId(asStringList("day5_input")))
    }

    @Test
    internal fun part2() {
        assertEquals(727, mySeatId(asStringList("day5_input")))
    }
}