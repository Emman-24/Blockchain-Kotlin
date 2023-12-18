package org.example

data class Block(
    val id: Int,
    val previousHash: String = "0",
    var hash: String,
    var timeStamp: Long,
    var magicNumber: Long
) {

    fun printBlock(id: Int, block: Block, timeStamp: Long, magicNumber: Long) {
        print(
            "Block:\n" +
                    "Created by miner # $id\n" +
                    "Id: ${block.id}\n" +
                    "Timestamp: $timeStamp\n" +
                    "Magic number: $magicNumber\n" +
                    "Hash of the previous block:\n" +
                    "${block.previousHash}\n" +
                    "Hash of the block:\n" +
                    "${block.hash}\n" +
                    "Block data:${
                        if (block.id == 1) {
                            " no messages\n"
                        } else {
                            "\n${randomMessages()}\n"
                        }
                    }" +
                    "Block was generating for 0 seconds\n"
        )
    }

    private fun randomMessages(): String {
        val messages = mutableListOf(
            "Tom: Hey, I'm first!",
            "Sarah: It's not fair!",
            "Sarah: You always will be first because it is your blockchain!",
            "Sarah: Anyway, thank you for this amazing chat.",
            "Tom: You're welcome :)",
            "Nick: Hey Tom, nice chat!",
        )
        val number = (1..messages.size).random()
        return messages.take(number).joinToString(separator = "\n")
    }


}
