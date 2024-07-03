package com.amigoprod.chatmigo.model

data class Chat(
    val cid: String? = null,
    var lastMessage: Message? = null,
    val members: List<User>? = emptyList()
)