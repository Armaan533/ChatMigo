package com.amigoprod.chatmigo.model

import androidx.compose.runtime.Composable
import com.amigoprod.chatmigo.customAppBars.MenuPageBarFunc
import com.amigoprod.chatmigo.utils.Names

sealed class PageInfo(
    val name: String,
    val isAppBarVisible: Boolean,
    val pageBar: @Composable (() -> Unit)
){
    data object Menu: PageInfo(
        name = Names.MENU_PAGE,
        isAppBarVisible = true,
        pageBar = { MenuPageBarFunc() }
    )

    data object Chat: PageInfo(
        name = Names.CHAT_PAGE,
        isAppBarVisible = true,
        pageBar = {}
    )

    data object Signup: PageInfo(
        name = Names.SIGNUP_PAGE,
        isAppBarVisible = false,
        pageBar = {}
    )
}