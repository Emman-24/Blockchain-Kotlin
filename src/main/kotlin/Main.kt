package org.example

fun main() {
    print("Enter how many zeros the hash must start with:")
    val difficulty = readln().toInt()
    val blockchain = Blockchain(difficulty)
    blockchain.addBlock(5)
    blockchain.printBlockchain(5)
}