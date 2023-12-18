package org.example

interface BlockChain {
    var chain: MutableList<Block>
    var difficulty: Int

}