package com.amigoprod.chatmigo.model

import java.sql.Timestamp

data class Message(
//    val mid: String? = null,
    val content: String? = null,
    val senderId: String? = null,
    val timestamp: Timestamp? = null,
    val delivered: Boolean = false,
    val read: Boolean = false
) {
    fun Message.isMine(user: User): Boolean {
        return this.senderId == user.uid
    }
}