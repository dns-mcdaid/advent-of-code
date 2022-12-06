object Day06 {

    /**
     * This is horrible and if you're reading this please know I don't typically write code like this,
     * but it's 5 AM and I have to go to work soon.
     */
    fun findStartOfPacket(input: String, packetSize: Int = 4) : Int {
        for (i in 0 until input.length - packetSize) {
            val subset = input.subSequence(i, i + packetSize)
            val set = subset.toSet()
            if (set.size == packetSize) return i + packetSize
        }
        return -1
    }
}
