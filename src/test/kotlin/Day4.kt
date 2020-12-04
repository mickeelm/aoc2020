import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day4 {

    @Test
    internal fun part1_example() {
        assertEquals(2, countValidPassports(asStringList("day4_example")))
    }

    @Test
    internal fun part1() {
        assertEquals(239, countValidPassports(asStringList("day4_input")))
    }

    @Test
    internal fun part2_invalid_passports() {
        assertEquals(0, countValidPassports(asStringList("day4_example_invalid_passports"), true))
    }

    @Test
    internal fun part2_valid_passports() {
        assertEquals(4, countValidPassports(asStringList("day4_input_valid_passports"), true))
    }

    @Test
    internal fun part2() {
        assertEquals(-1, countValidPassports(asStringList("day4_input"), true))
    }


}