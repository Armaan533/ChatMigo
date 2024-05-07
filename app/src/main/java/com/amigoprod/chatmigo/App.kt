package com.amigoprod.chatmigo

import android.app.Activity
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amigoprod.chatmigo.auth.AuthUIClient
import com.amigoprod.chatmigo.ui.models.SignInViewModel
import com.amigoprod.chatmigo.navigation.Page
import com.amigoprod.chatmigo.navigation.rememberAppBarState
import com.amigoprod.chatmigo.ui.pages.MenuPage
import com.amigoprod.chatmigo.ui.pages.SignUp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    authUIClient: AuthUIClient,
) {

//    val snackBarHostState = remember {
//        SnackbarHostState()
//    }

    val density = LocalDensity.current

    val navController = rememberNavController()

    val appBarState = rememberAppBarState(navController = navController)

    val signInViewModel = viewModel<SignInViewModel>()
    val state = signInViewModel.state.collectAsStateWithLifecycle()

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
                LaunchedEffect(Unit) {
                    val user = authUIClient.getSignedInUser()
                    if (user == null) {
                        navController.navigate(Page.Signup.route)
                    }
                    else {
                        Log.d(
                            "user",
                            "User logged in with uid: ${user.uid} and phone: ${user.phone}"
                        )
                    }
                }
                MenuPage(
                    navController,
                    state.value.isSignInSuccessful,
                    onSignOutClick = {
                        authUIClient.signOut()
                        signInViewModel.resetState()
                    }
                )
            }
            composable(Page.Signup.route) {
                SignUp(
                    onOtpGenClick = {phone, context ->
                        authUIClient.sendVerificationCode(
                            phone,
                            context as Activity
                        )
                    },
                    onVerificationClick = {otp, context, name->
                        signInViewModel.onSignInResult(
                            authUIClient.signInWithPhoneAuthCredentials(
                                verificationCode = otp,
                                context = context as Activity,
                                name = name
                            )
                        )
                    }
                )
            }
        }
    }
}