package com.amigoprod.chatmigo

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amigoprod.chatmigo.navigation.Pages
import com.amigoprod.chatmigo.ui.models.AuthUIClient
import com.amigoprod.chatmigo.ui.models.SignInViewModel
import com.amigoprod.chatmigo.navigation.rememberAppBarState
import com.amigoprod.chatmigo.ui.pages.ChatPage
import com.amigoprod.chatmigo.ui.pages.MenuPage
import com.amigoprod.chatmigo.ui.pages.SignUp

@Composable
fun App() {

    val density = LocalDensity.current

    val navController = rememberNavController()

    val appBarState = rememberAppBarState(navController = navController)

    val authUIClient = viewModel<AuthUIClient>()

    val context = LocalContext.current

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
                appBarState.pageBar()
            }
        }
    ) {paddingVal ->
        NavHost(
            navController = navController,
            startDestination = if(authUIClient.getSignedInUser() != null) Pages.Menu() else Pages.Signup(),
            modifier = Modifier.padding(paddingVal)
        ) {
            composable<Pages.Menu>{
                val user = authUIClient.getSignedInUser()
                LaunchedEffect(Unit) {
                    if (user == null) {
//                        navController.navigate(Page.Signup.route)
                    }
                    else {
                        Log.d(
                            "user",
                            "User logged in with uid: ${user.uid} and phone: ${user.phone}"
                        )
                    }
                }
                MenuPage(
                    onSignOutClick = {
                        authUIClient.signOut()
                    },
                    onChatPageClick = {
                        navController.navigate(Pages.Chat(""))
                    }
                )
            }
            composable<Pages.Signup> {
                val signInViewModel = viewModel<SignInViewModel>()
                val state by signInViewModel.state.collectAsState()


                LaunchedEffect(key1 = state.isSignInSuccessful) {
                    if (state.isSignInSuccessful) {
                        Toast.makeText(
                            context,
                            "Signin successful",
                            Toast.LENGTH_LONG
                        ).show()
                        navController.navigate(Pages.Menu())
                        signInViewModel.resetState()
                    }
                }
                LaunchedEffect(key1 = state.signInError) {
                    state.signInError?.let {error ->
                        Toast.makeText(
                            context,
                            error,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }


                SignUp(
                    onVerificationClick = {result ->
                        Log.d("verifyClick", "${result.data?.uid} ${result.data?.name} ${result.data?.phone}")
                        signInViewModel.onSignInResult(
                            result
                        )
                    },
                    authUIClient
                )
            }
            composable<Pages.Chat> {
                ChatPage()
            }
        }
    }
}