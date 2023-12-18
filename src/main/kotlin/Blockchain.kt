package org.example

class Blockchain {

    private var blockchain = mutableListOf<Block>()
    private val timeStamp: Long = System.currentTimeMillis()


    init {
        createGenesisBlock()
    }


    private fun createGenesisBlock() {
        val genesisBlock = Block(1, timeStamp, "0", "Genesis")
        this.blockchain.add(genesisBlock)

    }

    fun addBlock(num: Int) {
        for (i in 1..num) {
            val previousBlock = blockchain.last()
            val newBlock = Block(previousBlock.id + 1, timeStamp, previousBlock.hash, "Emma")
            blockchain.add(newBlock)
        }
    }

    fun printBlockchain(numBlocks: Int = 1) {
        val numToPrint = minOf(numBlocks, blockchain.size)
        for (i in 0 until numToPrint) {
            val block = blockchain[i]
            println(
                "Block:\n" +
                        "Id: ${block.id}\n" +
                        "Timestamp: ${block.timestamp}\n" +
                        "Hash of the previous block:\n" +
                        "${block.previousHash}\n" +
                        "Hash of the block:\n" +
                        block.hash
            )
            println()
        }
    }

}