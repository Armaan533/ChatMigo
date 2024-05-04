package com.amigoprod.chatmigo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.amigoprod.chatmigo.customAppBars.MenuPageBarFunc
import com.amigoprod.chatmigo.navigation.AppBarState
import com.amigoprod.chatmigo.navigation.Page
import com.amigoprod.chatmigo.pages.MenuPage
import com.amigoprod.chatmigo.pages.SignUp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun App() {

    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val density = LocalDensity.current

    val navController = rememberNavController()

    val appBarState = AppBarState(navController)


//    val selectedRoute = remember {
//        mutableStateOf("start")
//    }
//    val topBarState = rememberSaveable {
//        (mutableStateOf(true))
//    }
//    val currentBackStackEntry by navController.currentBackStackEntryAsState()
//    when (currentBackStackEntry?.destination?.route) {
//        Page.Signup.route -> {
//            topBarState.value = false
//        }
//        Page.Menu.route -> {
//            topBarState.value = true
//        }
//        Page.Chat.route -> {
//            topBarState.value = false
//        }
//    }



    Scaffold (
        topBar = {
            AnimatedVisibility(
                visible = appBarState.isVisible,
                enter = slideInVertically{
                    with(density) { -40.dp.roundToPx() }
                } + expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
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