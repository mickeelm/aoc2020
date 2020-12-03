import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day2 {

    @Test
    internal fun part1Example() {
        assertEquals(2, countCorrectPasswordsOldPolicy(asStringList("day2_example")))
    }

    @Test
    internal fun part1() {
        assertEquals(628, countCorrectPasswordsOldPolicy(asStringList("day2_input")))
    }

    @Test
    internal fun part2Example() {
        assertEquals(1, countCorrectPasswordsNewPolicy(asStringList("day2_example")))
    }

    @Test
    internal fun part2() {
        assertEquals(705, countCorrectPasswordsNewPolicy(asStringList("day2_input")))
    }
}