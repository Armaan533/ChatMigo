package com.amigoprod.chatmigo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.amigoprod.chatmigo.customAppBars.MenuPageBarFunc
import kotlinx.serialization.Serializable


private object Names {
    const val MENU_PAGE = "menu"
    const val SIGNUP_PAGE = "signup"
    const val CHAT_PAGE = "chat"
}

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


fun getScreen(route: Pages): PageInfo? = PageInfo::class.nestedClasses.map {
        kClass -> kClass.objectInstance as PageInfo
}.firstOrNull { page -> page.name == route.name }


class AppBarState(
    private val navController: NavController
) {
    private val currentPage: Pages?
        @Composable get() = navController
            .currentBackStackEntryAsState()
            .value?.toRoute()

    private val currentPageInfo: PageInfo?
        @Composable get() = currentPage?.let { getScreen(it) }

    val isVisible: Boolean
        @Composable get() = currentPageInfo?.isAppBarVisible == true

    val pageBar: @Composable ()->Unit
        @Composable get() = { currentPageInfo?.pageBar?.let { it() } }

}


@Composable
fun rememberAppBarState(
    navController: NavController
) = remember {
    AppBarState(navController)
}
