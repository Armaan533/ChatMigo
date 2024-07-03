package com.amigoprod.chatmigo.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.amigoprod.chatmigo.navigation.getScreen

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