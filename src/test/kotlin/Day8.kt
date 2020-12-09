import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day8 {

    @Test
    internal fun part1_example() {
        assertEquals(5, accValueBeforeLoopDetection(asStringList("day8_example")))
    }

    @Test
    internal fun part1() {
        assertEquals(1487, accValueBeforeLoopDetection(asStringList("day8_input")))
    }

    @Test
    internal fun part2_example() {
        assertEquals(8, accValueOfFixedProgram(asStringList("day8_example")))
    }

    @Test
    internal fun part2() {
        assertEquals(1607, accValueOfFixedProgram(asStringList("day8_input")))
    }
}