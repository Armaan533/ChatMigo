package com.amigoprod.chatmigo

import android.os.Looper
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.amigoprod.chatmigo.pages.MenuPage
import com.amigoprod.chatmigo.pages.StartupPage
import java.util.logging.Handler

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun App() {
    val selectedRoute = remember {
        mutableStateOf("start")
    }
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "ChatMigo", modifier = Modifier.padding(20.dp)) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {paddingVal ->
        NavHost(
            navController = navController,
            startDestination = Routes.StartupPage.route,
            modifier = Modifier.padding(paddingVal)
        ) {
            composable(Routes.StartupPage.route){
                StartupPage()
                android.os.Handler(Looper.getMainLooper()).postDelayed(
                    {navController.navigate(Routes.MenuPage.route)},
                    500
                )
            }
            composable(Routes.MenuPage.route){
                MenuPage()
            }
        }
    }
}