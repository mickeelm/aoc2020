class Passport(properties: Map<String, String>) {
    private val birthYear: Int? = properties["byr"]?.toInt()
    private val issueYear: Int? = properties["iyr"]?.toInt()
    private val expirationYear: Int? = properties["eyr"]?.toInt()
    private val height: String? = properties["hgt"]
    private val hairColor: String? = properties["hcl"]
    private val eyeColor: String? = properties["ecl"]
    private val passportId: String? = properties["pid"]

    companion object {
        private val heightRegex = """(\d*)(\w*)""".toRegex()
        private val hairColorRegex = "#[0-9a-f]{6}".toRegex()
        private val eyeColors = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
        private val pidRegex = "[0-9]{9}".toRegex()
    }

    fun valid(strict: Boolean = false): Boolean {
        birthYear ?: return false
        issueYear ?: return false
        expirationYear ?: return false
        height ?: return false
        hairColor ?: return false
        eyeColor ?: return false
        passportId ?: return false

        if (strict) {
            if (birthYear !in 1920..2002) return false
            if (issueYear !in 2010..2020) return false
            if (expirationYear !in 2020..2030) return false
            if (eyeColor !in eyeColors) return false
            if (!hairColorRegex.matches(hairColor)) return false
            if (!pidRegex.matches(passportId)) return false
            val (value, unit) = heightRegex.matchEntire(height)?.destructured ?: return false
            when (unit) {
                "cm" -> if (value.toInt() !in 150..193) return false
                "in" -> if (value.toInt() !in 59..76) return false
                else -> return false
            }
        }

        return true
    }
}

fun countValidPassports(input: Sequence<String>, strict: Boolean = false) =
    toPassports(input).count { it.valid(strict) }

private fun toPassports(input: Sequence<String>): Sequence<Passport> = sequence {
    input.chunkedAndJoined().forEach { yield(it.toPassport()) }
}

private fun String.toPassport(): Passport =
    split(" ").map {
        val (property, value) = it.split(":")
        property to value
    }.toMap().let {
        Passport(it)
    }
