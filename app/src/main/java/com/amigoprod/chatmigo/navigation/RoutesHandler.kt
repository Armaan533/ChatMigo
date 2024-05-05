package com.amigoprod.chatmigo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.amigoprod.chatmigo.customAppBars.MenuPageBarFunc
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
    val isAppBarVisible: Boolean?,
    val navArguments: List<NamedNavArgument> = emptyList(),
    val pageBar: @Composable() () -> Unit
) {


    object Menu: Page(
        route = Routes.MENU_PAGE,
        isAppBarVisible = true,
        pageBar = { MenuPageBarFunc() }
    )

    object Signup: Page(
        route = Routes.SIGNUP_PAGE,
        isAppBarVisible = false,
        pageBar = {}
    )


    object Chat: Page(
        Routes.CHAT_PAGE,
        navArguments = listOf(navArgument(ArgParams.CHAT_ID) {
            type = NavType.Companion.StringType
        }),
        isAppBarVisible = true,
        pageBar = {}
    ) {
        fun createRoute(chatID: String) = Routes.CHAT_PAGE.replace(ArgParams.toPath(ArgParams.CHAT_ID), chatID)
    }
}


fun getScreen(route: String?): Page? = Page::class.nestedClasses.map {
        kClass -> kClass.objectInstance as Page
}.firstOrNull { page -> page.route == route }


class AppBarState(
    private val navController: NavController
) {
    private val currentPageRoute: String?
        @Composable get() = navController
            .currentBackStackEntryAsState()
            .value?.destination?.route

    val currentPage: Page?
        @Composable get() = getScreen(currentPageRoute)

    val isVisible: Boolean
        @Composable get() = currentPage?.isAppBarVisible == true

    val PageBar: @Composable() ()->Unit
        @Composable get() = { currentPage?.pageBar?.let { it() } }

}

@Composable
fun rememberAppBarState(
    navController: NavController
) = remember {
    AppBarState(navController)
}