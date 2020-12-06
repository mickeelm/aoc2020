fun Sequence<String>.chunkedAndJoined(joiner: CharSequence = " "): Sequence<String> = sequence {
    reduce { part, line ->
        if (line.isBlank()) {
            yield(part)
            return@reduce ""
        } else {
            return@reduce if (part.isNotBlank()) "$part$joiner$line".trim() else line
        }
    }.run { yield(this) }
}

fun Sequence<String>.chunked(): Sequence<List<String>> =
    sequence {
        fold(arrayListOf<String>(), { list, line ->
            if (line.isBlank()) {
                yield(list)
                return@fold arrayListOf<String>()
            } else {
                list.add(line)
                return@fold list
            }
        }
        ).run { yield(this) }
    }