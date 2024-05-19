package com.amigoprod.chatmigo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
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

@Parcelize
data class User(
    val uid: String? = null,
    val name: String? = null,
    val phone: String? = null
//    val password: String? = null
) : Parcelable

data class SignInResult(
    val data: User?,
    val errorMsg: String?
)

@Parcelize
data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
) : Parcelable

fun Message.isMine(user: User): Boolean {
    return this.sender!!.uid == user.uid
}