package org.example

data class Transaction(val sender: String, val receiver: String, val amount: Int) {
    override fun toString(): String {
        return "$sender sent $amount VC to $receiver"
    }
}
