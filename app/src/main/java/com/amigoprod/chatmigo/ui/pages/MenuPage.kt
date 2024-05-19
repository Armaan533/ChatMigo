package com.amigoprod.chatmigo.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun MenuPage(
    onSignOutClick: (() -> Unit)
) {
    Column {
        Spacer(modifier = Modifier.padding(10.dp))

        Button(onClick = {onSignOutClick()}) {
            Text(text = "Sign Out")
        }

    }
}