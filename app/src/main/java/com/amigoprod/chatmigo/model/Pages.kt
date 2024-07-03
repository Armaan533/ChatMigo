package com.amigoprod.chatmigo.model

import com.amigoprod.chatmigo.utils.Names
import kotlinx.serialization.Serializable

@Serializable
sealed class Pages(
    val name: String = "",
){
    @Serializable
    class Menu: Pages(
        name = Names.MENU_PAGE
    )

    @Serializable
    class Chat(
        val chatID: String
    ): Pages(
        name = Names.CHAT_PAGE
    )

    @Serializable
    class Signup: Pages(
        name = Names.SIGNUP_PAGE
    )
}