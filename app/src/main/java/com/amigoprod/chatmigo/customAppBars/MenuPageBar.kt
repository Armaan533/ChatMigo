package com.amigoprod.chatmigo.customAppBars

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPageBarFunc() {
    val isExpanded = remember {
        mutableStateOf(false)
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "ChatMigo",
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            IconButton(onClick = {
                isExpanded.value = !isExpanded.value
            }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Options"
                )
            }
            DropdownMenu(
                expanded = isExpanded.value,
                onDismissRequest = {
                    isExpanded.value = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                           Text(text = "Load")
                    },
                    onClick = { /*TODO*/ }
                )
                DropdownMenuItem(
                    text = { Text(text = "Save") },
                    onClick = { /*TODO*/ }
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    )
}