package org.example

import java.security.MessageDigest

data class Block(
    val id: Int,
    val timestamp: Long,
    val previousHash: String,
    val data: String
) {
    var magicNumber: Long = 0
    var hash: String = calculateHash()
    var timeToMine: Long = 0


    fun mine(difficulty: Int) {
        val prefix = "0".repeat(difficulty)
        val startTime = System.currentTimeMillis()

        while (!hash.startsWith(prefix)) {
            magicNumber++
            hash = calculateHash()
        }
        val endTime = System.currentTimeMillis()
        timeToMine = endTime - startTime
    }

    private fun calculateHash(): String {
        val dataToHash = "$id$timestamp$previousHash$data$magicNumber"
        return applySha256(dataToHash)
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
