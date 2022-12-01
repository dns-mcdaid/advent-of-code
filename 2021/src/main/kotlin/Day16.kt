import kotlin.math.min

object Day16 {

    private val hexLookup = mapOf(
        '0' to "0000",
        '1' to "0001",
        '2' to "0010",
        '3' to "0011",
        '4' to "0100",
        '5' to "0101",
        '6' to "0110",
        '7' to "0111",
        '8' to "1000",
        '9' to "1001",
        'A' to "1010",
        'B' to "1011",
        'C' to "1100",
        'D' to "1101",
        'E' to "1110",
        'F' to "1111",
    )

    data class DigestResult(
        val value: Long,
        val cursor: Int,
        val versionNumberTotal: Long,
    )

    private fun digestLiteral(
        packet: CharSequence,
        cursor: Int = 0,
    ) : DigestResult {
        var newCursor = cursor
        var words = ""
        while (newCursor < packet.length && packet[newCursor] != '0') {
            val word = packet.subSequence(newCursor + 1, newCursor + 5)
            words += word
            newCursor += 5
        }
        val lastWord = packet.subSequence(newCursor + 1, newCursor + 5)
        words += lastWord
        var nextCursor = newCursor + 5
        println("LITERAL: ${words.binaryLongValue}")
        return DigestResult(
            value = words.binaryLongValue,
            cursor = nextCursor,
            versionNumberTotal = 0L,
        )
    }

    private fun digestPacketsByCount(
        packet: CharSequence,
        cursor: Int,
    ) : DigestResult {
        val subPacketSize = packet.subSequence(cursor, cursor + 11).binaryLongValue
        return (0 until subPacketSize).fold(
            DigestResult(
                value = 0,
                cursor = cursor + 11,
                versionNumberTotal = 0L,)
        ) { acc, _ ->
            val next = digest(packet, acc.cursor)
            return@fold next.copy(
                versionNumberTotal = acc.versionNumberTotal + next.versionNumberTotal,
            )
        }
    }

    private fun digestPacketsBySize(
        packet: CharSequence,
        cursor: Int,
    ) : DigestResult {
        val endOfLengthBlock = cursor + 15
        val subPacketSize = packet.subSequence(cursor, min(endOfLengthBlock, packet.length)).binaryLongValue
        println("SIZE: $subPacketSize")
        return generateSequence(
            seed = DigestResult(value = 0, cursor = cursor + 15, versionNumberTotal = 0)
        ) { prev ->
            if (prev.cursor < endOfLengthBlock + subPacketSize) {
                val next = digest(packet, prev.cursor)
                next.copy(
                    versionNumberTotal = prev.versionNumberTotal + next.versionNumberTotal,
                )
            }
            else null
        }
            .last()
            .copy(cursor = cursor + subPacketSize.toInt())
    }

    private fun digestOperator(
        packet: CharSequence,
        cursor: Int,
    ) : DigestResult {
        return when (packet[cursor]) {
            '0' -> digestPacketsBySize(packet, cursor + 1)
            '1' -> digestPacketsByCount(packet, cursor + 1)
            else -> throw IllegalStateException("Packet values should only be binary")
        }
    }

    fun digest(
        packet: CharSequence,
        cursor: Int = 0,
    ) : DigestResult {
        val version = packet.subSequence(cursor, cursor + 3).binaryLongValue
        val type = packet.subSequence(cursor + 3, cursor + 6).binaryLongValue
        println("VERSION: $version")
        val nextCursor = cursor + 6
        val digestResult = when (type) {
            4L -> digestLiteral(packet, nextCursor)
            else -> digestOperator(packet, nextCursor)
        }
        return digestResult.copy(versionNumberTotal = digestResult.versionNumberTotal + version)
    }

    fun getVersionSum(packet: CharSequence): Long {
        return digest(packet, 0).versionNumberTotal
    }

    fun prepareData(hexString: CharSequence): CharSequence {
        val result = hexString.mapNotNull { hexLookup[it] }
            .joinToString(separator = "")
        check(result.length == hexString.length * 4)
        check(hexLookup.values.distinct().size == hexLookup.keys.size)
        return result
    }
}
