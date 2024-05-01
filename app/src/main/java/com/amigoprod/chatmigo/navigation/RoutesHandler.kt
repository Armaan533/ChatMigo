package com.amigoprod.chatmigo.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import java.util.Collections.emptyList

//data class NavPage(var name: String, var icon: ImageVector?, var route: String)

//object Routes {
//    val StartupPage = NavPage("Startup", null, "start")
//    val MenuPage = NavPage("Menu", Icons.Outlined.Home, "menu")
//}

private object Routes {
//    const val STARTUP_PAGE = "startup"
    const val MENU_PAGE = "menu"
    const val CHAT_PAGE = "chat/{${ArgParams.CHAT_ID}}"
}


private object ArgParams {
    const val CHAT_ID = "chatID"

    fun toPath(param: String) = "{${param}}"
}

sealed class Page(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
//    object StartUp: Page(Routes.STARTUP_PAGE)
    object Menu: Page(Routes.MENU_PAGE)
    object Chat: Page(
        Routes.CHAT_PAGE,
        navArguments = listOf(navArgument(ArgParams.CHAT_ID) {
            type = NavType.Companion.StringType
        })
    ) {
        fun createRoute(chatID: String) = Routes.CHAT_PAGE.replace(ArgParams.toPath(ArgParams.CHAT_ID), chatID)
    }
}
