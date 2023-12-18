package org.example

import java.security.MessageDigest
import kotlin.random.Random

open class Miner : BlockChain, Thread() {
    override var chain: MutableList<Block> = mutableListOf()
    override var difficulty: Int = 0

    override fun run() {
        mineBlock()
    }

    private fun mineBlock(blocks: Int = 5) {
        synchronized(this) {
            while (chain.size < blocks) {
                val newBlock = generateBlock()
                chain.add(newBlock)
                newBlock.printBlock(newBlock.id, newBlock, newBlock.timeStamp, newBlock.magicNumber)
                adjustDifficulty()
                sleep(2000)
            }
        }
    }


    private fun generateBlock(): Block {
        val id = Random.nextInt(1, 100)
        val magicNumber = Random.nextLong(0, 99999999)
        val timeStamp: Long = System.currentTimeMillis()
        val prefix = "0".repeat(difficulty)
        val data = "$id$timeStamp$magicNumber"
        val hash = applySha256(data)
        val previousHash = chain.lastOrNull()?.hash ?: "0"

        return Block(
            id = chain.size + 1,
            previousHash = previousHash,
            hash = if (hash.startsWith(prefix)) hash else applySha256(data),
            timeStamp = timeStamp,
            magicNumber = magicNumber
        )
    }

    private fun adjustDifficulty() {
        val changeDifficulty = Random.nextInt(1, 4)
        when (changeDifficulty) {
            1 -> {
                difficulty++
                println("N was increased to $difficulty")
                println()
            }

            2 -> {
                if (difficulty > 0)
                    difficulty--
                println("N was decreased by $difficulty")
                println()
            }

            else -> {
                println("N stays the same")
                println()
            }
        }
    }

    private fun applySha256(input: String): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            val hash = digest.digest(input.toByteArray(charset("UTF-8")))
            val hexString = StringBuilder()
            for (elem in hash) {
                val hex = Integer.toHexString(0xff and elem.toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            hexString.toString()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}