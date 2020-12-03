import java.lang.RuntimeException

val regex = "([0-9]*)-([0-9]*) ([a-z]): ([a-z]*)".toRegex()

fun countCorrectPasswordsOldPolicy(input: List<String>) =
    input.count { entry ->
        val (minOccurs, maxOccurs, letter, password) = regex.matchEntire(entry)?.destructured
            ?: throw RuntimeException("Invalid entry: $entry")
        val occurrences = password.count { it == letter.first() }
        
        occurrences in (minOccurs.toInt())..(maxOccurs.toInt())
    }

fun countCorrectPasswordsNewPolicy(input: List<String>): Int =
    input.count { entry ->
        val (first, second, letter, password) = regex.matchEntire(entry)?.destructured
            ?: throw RuntimeException("Invalid entry: $entry")
        val firstIndex = first.toInt() - 1
        val secondIndex = second.toInt() -1

        (password[firstIndex] == letter.first()) xor (password[secondIndex] == letter.first())
    }