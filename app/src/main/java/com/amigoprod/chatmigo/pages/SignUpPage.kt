package com.amigoprod.chatmigo.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Preview(backgroundColor = 0xFFFFFF)
@Composable
fun SignUp() {
    val name = remember {
        mutableStateOf("")
    }
    val phoneNumber = remember {
        mutableStateOf("")
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        shape = CardDefaults.outlinedShape
    ) {
        Text(
            text = "Sign Up",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(10.dp, 15.dp, 15.dp, 10.dp),
            style = MaterialTheme.typography.headlineMedium,
            fontFamily = FontFamily.Cursive
        )
        TextField(
            value = name.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = {
                name.value = it
            },
            placeholder = { Text(text = "Enter name")   },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        TextField(
            value = phoneNumber.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                phoneNumber.value = it
            },
            placeholder = { Text(text = "Enter your phone number")  },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.padding(vertical = 15.dp))

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Generate OTP")
        }
    }
}