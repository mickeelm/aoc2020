data class BagCount(val color: String, val count: Int)
typealias BagPolicy = Set<BagCount>

val noise = setOf("bags", "contain", "bag,", "bag.", "bags,", "bags.", "no", "other")

fun bagColorsEventuallyContaining(input: Sequence<String>, color: String): Int =
    input.toBagPolicies().run {
        fun recursiveCount(color: String, policy: BagPolicy): Boolean =
            policy.any { it.color == color } or
            policy.any { recursiveCount(color, getValue(it.color)) }

        values.count { recursiveCount(color, it) }
    }

fun bagsRequiredFor(input: Sequence<String>, color: String): Int =
    input.toBagPolicies().run {
        fun recursiveCount(policy: BagPolicy): Int =
            policy.sumOf { it.count + it.count * recursiveCount(getValue(it.color)) }

        recursiveCount(getValue(color))
    }

private fun Sequence<String>.toBagPolicies(): Map<String, BagPolicy> =
    map { it.toBagColorWithPolicy() }.toMap().withDefault { emptySet() }

private fun String.toBagColorWithPolicy(): Pair<String, BagPolicy> {
    val words = split(' ').filter { it !in noise }.toCollection(ArrayDeque())
    val color = "${words.removeFirst()} ${words.removeFirst()}"
    val policy = words.chunked(3).map { it.toBagCount() }.toSet()

    return color to policy
}

private fun List<String>.toBagCount(): BagCount = BagCount("${this[1]} ${this[2]}", this[0].toInt())