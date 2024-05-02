package com.amigoprod.chatmigo.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
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
    val inputEnabler = remember {
        mutableStateOf(true)
    }
    val buttonEnabler = remember {
        mutableStateOf(false)
    }
    val otp = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    val density = LocalDensity.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
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
                singleLine = true,
                enabled = inputEnabler.value
            )

            Spacer(modifier = Modifier.padding(vertical = 15.dp))

            TextField(
                value = phoneNumber.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    phoneNumber.value = it
                    if (phoneNumber.value.length == 10){
                        buttonEnabler.value = true
                    }
                },
                placeholder = { Text(text = "Enter your phone number")  },
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                singleLine = true,
                enabled = inputEnabler.value
            )

            Spacer(modifier = Modifier.padding(vertical = 15.dp))

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    inputEnabler.value = false
                    buttonEnabler.value = false
                },
                enabled = buttonEnabler.value
            ) {
                Text(text = "Generate OTP")
            }
        }
        AnimatedVisibility(
            visible = !inputEnabler.value,
            enter = slideInVertically{
                with(density) { -40.dp.roundToPx() }
            } + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .align(Alignment.CenterHorizontally),
                shape = CardDefaults.outlinedShape
            ) {
                Text(
                    text = "OTP sent to +91 ${phoneNumber.value}.",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 30.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Change number?",
                    modifier = Modifier
                        .clickable {
                            inputEnabler.value = true
                            buttonEnabler.value = true
                        }
                        .align(Alignment.CenterHorizontally),
                    color = Color.Blue,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(10.dp))

                BasicTextField(
                    value = otp.value,
                    onValueChange = {
                        if (it.text.length <=4) otp.value = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        repeat(4) { index ->
                            val number = when {
                                index >= otp.value.text.length -> ""
                                else -> otp.value.text[index]
                            }

                            Column (
                                verticalArrangement = Arrangement.spacedBy(6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = number.toString(),
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Box(
                                    modifier = Modifier
                                        .width(40.dp)
                                        .height(2.dp)
                                        .background(Color.Black)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Verify OTP")
                }
            }
        }
    }
}

//@Preview()
//@Composable
//fun OtpComposable(
//    pno: MutableState<String> = mutableStateOf("1234567890"),
//    enabled: MutableState<Boolean> = mutableStateOf(false)
//) {
//
//}