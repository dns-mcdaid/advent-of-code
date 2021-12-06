import java.io.File

object Parser {

    private fun getFile(fileName: String): File {
        val url = javaClass.classLoader.getResource(fileName)
            ?: throw IllegalArgumentException("Failed to find file: $fileName")
        return File(url.toURI())
    }


    fun readLines(fileName: String): List<String> = getFile(fileName).readLines()

    fun readText(fileName: String): String = getFile(fileName).readText()
}
