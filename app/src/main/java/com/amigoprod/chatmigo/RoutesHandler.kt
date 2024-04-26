package com.amigoprod.chatmigo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector

data class NavPage(var name: String, var icon: ImageVector?, var route: String)

object Routes {
    val StartupPage = NavPage("Startup", null, "start")
    val MenuPage = NavPage("Menu", Icons.Outlined.Home, "menu")
}