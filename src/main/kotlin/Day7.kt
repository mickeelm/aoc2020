data class BagCount(val color: String, val count: Int)
typealias BagPolicy = Set<BagCount>

val noise = setOf("bags", "contain", "bag,", "bag.", "bags,", "bags.", "no", "other")

fun bagColorsEventuallyContaining(input: Sequence<String>, color: String): Int =
    input.toBagPolicies().run {
        values.count { bagColorsEventuallyContaining(color, it, this) }
    }

private fun bagColorsEventuallyContaining(color: String, policy: BagPolicy, policies: Map<String, BagPolicy>): Boolean =
    policy.any { it.color == color } or
    policy.any { bagColorsEventuallyContaining(color, policies.getValue(it.color), policies) }

fun bagsRequiredFor(input: Sequence<String>, color: String): Int =
    input.toBagPolicies().run {
        bagsRequiredFor(getValue(color), this)
    }

private fun bagsRequiredFor(policy: BagPolicy, policies: Map<String, BagPolicy>): Int {
    return policy.map { it.count + it.count * bagsRequiredFor(policies.getValue(it.color), policies) }.sum()
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