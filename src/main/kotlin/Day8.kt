import Operation.*
import State.INFINITE_LOOP_DETECTED
import State.TERMINATED_SUCCESSFULLY

enum class Operation(val operation: String) {
    NO_OPERATION("nop"),
    ACCUMULATE("acc"),
    JUMP("jmp");

    companion object {
        operator fun get(operation: String): Operation = values().first { it.operation == operation }
    }

    fun switch() =
        when (this) {
            NO_OPERATION -> JUMP
            JUMP -> NO_OPERATION
            ACCUMULATE -> throw RuntimeException("Accumulate instructions are to remain")
        }
}

data class Instruction(val operation: Operation, val value: Int) {
    fun switch() = Instruction(operation.switch(), value)
    fun execute(currentIndex: Int, accValue: Int) =
        when (operation) {
            NO_OPERATION -> Pair(currentIndex + 1, accValue)
            ACCUMULATE -> Pair(currentIndex + 1, accValue + value)
            JUMP -> Pair(currentIndex + value, accValue)
        }
}

enum class State { TERMINATED_SUCCESSFULLY, INFINITE_LOOP_DETECTED }
data class ExecutionResult(val state: State, val accValue: Int)


fun accValueOfFixedProgram(input: List<String>): Int {
    val instructions = input.asInstructions()
    val switchIndices = instructions.withIndex().filter { it.value.operation != ACCUMULATE }.map { it.index }
    return switchIndices.map { runDevice(instructions, it) }.first { it.state == TERMINATED_SUCCESSFULLY }.accValue
}

fun accValueBeforeLoopDetection(input: List<String>) = input.asInstructions().run { runDevice(this).accValue }

private fun runDevice(instructions: List<Instruction>, switchIndex: Int = -1): ExecutionResult {
    val executedInstructions = Array(instructions.size) { false }
    var accValue = 0
    var index = 0
    while (!executedInstructions[index]) {
        executedInstructions[index] = true
        val instruction = if (index == switchIndex) instructions[index].switch() else instructions[index]
        with(instruction.execute(index, accValue)) { index = first; accValue = second }

        if (index == instructions.size) return ExecutionResult(TERMINATED_SUCCESSFULLY, accValue)
    }

    return ExecutionResult(INFINITE_LOOP_DETECTED, accValue)
}

private fun List<String>.asInstructions(): List<Instruction> = map { it.toOperation() }
private fun String.toOperation() = split(' ').let { Instruction(Operation[it[0]], it[1].toInt()) }
