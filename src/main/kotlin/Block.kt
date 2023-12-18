package org.example

data class Block(
    val id: Int,
    val previousHash: String = "0",
    val miner: String,
    val transactions: List<Transaction>,
    var hash: String,
    var timeStamp: Long,
    var magicNumber: Long
) {

    fun printBlock(block: Block, timeStamp: Long, magicNumber: Long) {
        print(
            "Block:\n" +
                    "Created by: $miner\n" +
                    "$miner gets 100 VC\n" +
                    "Id: ${block.id}\n" +
                    "Timestamp: $timeStamp\n" +
                    "Magic number: $magicNumber\n" +
                    "Hash of the previous block:\n" +
                    "${block.previousHash}\n" +
                    "Hash of the block:\n" +
                    "${block.hash}\n" +
                    "Block data:${
                        if (block.id == 1) {
                            "No transactions\n"
                        } else {
                            "\n${transactions}\n"
                        }
                    }" +
                    "Block was generating for 0 seconds\n"
        )
    }


}
