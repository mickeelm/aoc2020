import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day7 {

    @Test
    internal fun part1_example() {
        assertEquals(4, numberOfBagColorsEventuallyContaining(asStringSequence("day7_example"), "shiny gold"))
    }

    @Test
    internal fun part1() {
        assertEquals(233, numberOfBagColorsEventuallyContaining(asStringSequence("day7_input"), "shiny gold"))
    }

    @Test
    internal fun part2_example() {
        assertEquals(32, numberOfBagsRequiredFor(asStringSequence("day7_example"), "shiny gold"))
    }

    @Test
    internal fun part2_example_2() {
        assertEquals(126, numberOfBagsRequiredFor(asStringSequence("day7_example_2"), "shiny gold"))
    }

    @Test
    internal fun part2() {
        assertEquals(421550, numberOfBagsRequiredFor(asStringSequence("day7_input"), "shiny gold"))
    }
}