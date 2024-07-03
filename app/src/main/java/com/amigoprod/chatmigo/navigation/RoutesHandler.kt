package com.amigoprod.chatmigo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.amigoprod.chatmigo.model.AppBarState
import com.amigoprod.chatmigo.model.PageInfo
import com.amigoprod.chatmigo.model.Pages


fun getScreen(route: Pages): PageInfo? = PageInfo::class.nestedClasses.map {
        kClass -> kClass.objectInstance as PageInfo
}.firstOrNull { page -> page.name == route.name }


@Composable
fun rememberAppBarState(
    navController: NavController
) = remember {
    AppBarState(navController)
}
