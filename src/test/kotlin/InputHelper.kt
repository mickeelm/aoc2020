import java.io.File

fun asStringList(filename: String): List<String> =
    File("src/test/resources/$filename").useLines { it.toList() }

fun asIntList(filename: String): List<Int> =
    File("src/test/resources/$filename").useLines { line -> line.map { it.toInt() }.toList() }