package com.amigoprod.chatmigo.ui.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amigoprod.chatmigo.navigation.Page


@Composable
fun MenuPage(
    navController: NavController,
    isSignedIn: Boolean,
    onSignOutClick: (() -> Unit)
) {
    Column {
        Text(text = "hello there", modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = {navController.navigate(Page.Signup.route)}) {
            Text(text = "SignUp")
        }
        Spacer(modifier = Modifier.padding(10.dp))
//        AnimatedVisibility(visible = isSignedIn) {
//        }
        Button(onClick = {onSignOutClick()}) {
            Text(text = "Sign Out")
        }
    }
}