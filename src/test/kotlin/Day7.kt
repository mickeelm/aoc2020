import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day7 {

    @Test
    internal fun part1_example() {
        assertEquals(4, bagColorsEventuallyContaining(asStringSequence("day7_example"),"shiny gold"))
    }

    @Test
    internal fun part1() {
        assertEquals(233, bagColorsEventuallyContaining(asStringSequence("day7_input"),"shiny gold"))
    }

    @Test
    internal fun part2_example() {
        assertEquals(32, bagsRequiredFor(asStringSequence("day7_example"),"shiny gold"))
    }

    @Test
    internal fun part2_example_2() {
        assertEquals(126, bagsRequiredFor(asStringSequence("day7_example_2"),"shiny gold"))
    }

    @Test
    internal fun part2() {
        assertEquals(421550, bagsRequiredFor(asStringSequence("day7_input"),"shiny gold"))
    }
}