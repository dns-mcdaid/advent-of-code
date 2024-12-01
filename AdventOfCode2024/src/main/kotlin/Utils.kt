fun String.splitWhitespace(): List<String> {
    return split("\\s+".toRegex())
}

fun String.splitNewLines(): List<String> {
    return split("\n")
}
