package com.amigoprod.chatmigo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amigoprod.chatmigo.navigation.Page
import com.amigoprod.chatmigo.navigation.rememberAppBarState
import com.amigoprod.chatmigo.pages.MenuPage
import com.amigoprod.chatmigo.pages.SignUp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun App() {

//    val snackBarHostState = remember {
//        SnackbarHostState()
//    }

    val density = LocalDensity.current

    val navController = rememberNavController()

    val appBarState = rememberAppBarState(navController = navController)

    Scaffold (
        topBar = {
            AnimatedVisibility(
                visible = appBarState.isVisible,
                enter = slideInVertically{
                    with(density) { -40.dp.roundToPx() }
                } + fadeIn(
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically()+ fadeOut()
            ) {
                appBarState.PageBar()
            }
        }
    ) {paddingVal ->
        NavHost(
            navController = navController,
            startDestination = Page.Menu.route,
            modifier = Modifier.padding(paddingVal)
        ) {
            composable(Page.Menu.route){
                MenuPage(navController)
            }
            composable(Page.Signup.route) {
                SignUp()
            }
        }
    }
}