fun String.splitWhitespace(): List<String> {
    return split("\\s+".toRegex())
}
