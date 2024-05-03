package com.amigoprod.chatmigo.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.amigoprod.chatmigo.navigation.Page


@Composable
fun MenuPage(navController: NavController) {
    Text(text = "hello there", modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.padding(50.dp))
    Button(onClick = {navController.navigate(Page.Signup.route)}) {
        Text(text = "SignUp")
    }
}