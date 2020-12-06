fun anyYesCount(input: Sequence<String>) = input.chunkedAndJoined(joiner = "").map { it.toSet().size }.sum()

fun allYesCount(input: Sequence<String>) = input.chunked().map { declarations ->
    declarations.map { it.toSet() }.reduce { allYes, declaration ->
        allYes.intersect(declaration)
    }.size
}.sum()