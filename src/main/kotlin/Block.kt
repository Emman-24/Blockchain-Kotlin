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
                    "Block was generating for 0 seconds\n"
        )
    }


}
