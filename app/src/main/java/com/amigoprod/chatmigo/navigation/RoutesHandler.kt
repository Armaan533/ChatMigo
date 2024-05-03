package com.amigoprod.chatmigo.navigation

import android.app.ActionBar
import android.widget.ActionMenuView
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.graphics.vector.ImageVector
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
    const val SIGNUP_PAGE = "signup"
    const val CHAT_PAGE = "chat/{${ArgParams.CHAT_ID}}"
}


private object ArgParams {
    const val CHAT_ID = "chatID"

    fun toPath(param: String) = "{${param}}"
}

sealed class Page(
    val route: String,
    val isAppBarVisible: Boolean,
    val navigationIcon: ImageVector?,
    val onNavigationIconClick: (() -> Unit)?,
    val title: String,
    val actions: (()-> Unit)?,
    val navArguments: List<NamedNavArgument> = emptyList()
) {


    object Menu: Page(
        route = Routes.MENU_PAGE,
        isAppBarVisible = true,
        navigationIcon = null,
        onNavigationIconClick = null,
        title = "",
        actions = null
    )

    object Signup: Page(
        route = Routes.SIGNUP_PAGE,
        isAppBarVisible = false,
        navigationIcon = null,
        onNavigationIconClick = null,
        title = "",
        actions = null
    )


    object Chat: Page(
        Routes.CHAT_PAGE,
        navArguments = listOf(navArgument(ArgParams.CHAT_ID) {
            type = NavType.Companion.StringType
        }),
        isAppBarVisible = true,
        navigationIcon = null,
        onNavigationIconClick = null,
        title = "",
        actions = null
    ) {
        fun createRoute(chatID: String) = Routes.CHAT_PAGE.replace(ArgParams.toPath(ArgParams.CHAT_ID), chatID)
    }
}
