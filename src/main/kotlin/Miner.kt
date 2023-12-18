package org.example

import java.security.MessageDigest
import kotlin.random.Random

open class Miner : BlockChain, Thread() {
    override var chain: MutableList<Block> = mutableListOf()
    override var difficulty: Int = 0

    private val miners = mutableMapOf(
        "miner1" to 0,
        "miner2" to 0,
        "miner3" to 0,
        "miner4" to 0,
        "miner5" to 0,
        "miner6" to 0,
        "miner7" to 0,
        "miner8" to 0,
        "miner9" to 0,
    )

    override fun run() {
        mineBlock()
    }

    private fun mineBlock(blocks: Int = 15) {
        synchronized(this) {
            while (chain.size < blocks) {
                val newBlock = generateBlock()
                chain.add(newBlock)
                newBlock.printBlock(newBlock, newBlock.timeStamp, newBlock.magicNumber)
                adjustDifficulty()
                sleep(1000)
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
            transactions = sendVC(),
            magicNumber = magicNumber,
            miner = minersBlock()
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

    private fun sendVC(): List<Transaction> {
        val sender = minersBlock()
        val receivers: MutableMap<String, Int> = Persons().names
        val receiver = receivers.keys.random()
        val transaction = if (minerGetValue(sender) > 1) {
            val amount = Random.nextInt(1, getAmount(sender))
            miners[sender] = miners[sender]!! - amount
            receivers[receiver] = receivers[receiver]!! + amount
            Transaction(sender, receiver, amount)
        } else {
            Transaction(sender, receiver, 0)
        }
        return listOf(transaction)

    }

    private fun minersBlock(): String {
        val randomMinerIndex = Random.nextInt(1, miners.size)
        val randomMiner = miners.keys.elementAt(randomMinerIndex)
        minerReward(randomMiner)
        return randomMiner
    }

    private fun minerReward(miner: String) {
        miners[miner] = miners[miner]!! + 100
    }

    private fun minerGetValue(miner: String): Int {
        return miners[miner]!!
    }

    private fun getAmount(miner: String): Int {
        return miners[miner]!!
    }
}