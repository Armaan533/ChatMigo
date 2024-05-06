package com.amigoprod.chatmigo

import android.system.Int64Ref
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp


data class Message(
    val mid: String,
    val content: String? = null,
    val sender: User? = null,
    val timestamp: Timestamp? = null,
    val delivered: Boolean = false,
    val read: Boolean = false
)

data class Chat(
    val cid: String,
    val messages: List<Message>? = emptyList(),
    val members: List<User>? = emptyList()
)

data class User(
    val uid: String,
    val name: String? = null,
    val phone: String,
//    val password: String? = null
)

data class SignInResult(
    val data: User?,
    val errorMsg: String?
)

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)

fun Message.isMine(user: User): Boolean {
    return this.sender!!.uid == user.uid
}