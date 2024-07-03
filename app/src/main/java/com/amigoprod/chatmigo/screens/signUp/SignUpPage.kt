package com.amigoprod.chatmigo.screens.signUp

import android.app.Activity
import android.util.Log
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amigoprod.chatmigo.SignInResult
import com.amigoprod.chatmigo.ui.models.AuthUIClient
import kotlinx.coroutines.launch


@Composable
fun SignUp(
    onVerificationClick: ((SignInResult) -> Unit),
    authUIClient: AuthUIClient
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    val otp = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    val pageModel = viewModel<SignUpPageViewModel>()
    val name by pageModel.name.collectAsState()
    val phone = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    val inputEnabler by pageModel.inputEnabler.collectAsState()
    val buttonEnabler by pageModel.buttonEnabler.collectAsState()
    val isOtpSent by authUIClient.isOtpSent.collectAsState()
    val isVerifyClicked by pageModel.verifyButtonEn.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(30.dp))
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
                value = name,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = {
                    pageModel.resetKey("name", it)
                },
                placeholder = { Text(text = "Enter name")   },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                singleLine = true,
                enabled = inputEnabler
            )

            Spacer(modifier = Modifier.padding(vertical = 15.dp))

            TextField(
                value = phone.value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    phone.value = it
                    pageModel.resetKey("buttonEn", phone.value.text.length == 10)
                    Log.d("phone", phone.value.text)
                },
                placeholder = { Text(text = "Enter your phone number")  },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                singleLine = true,
                enabled = inputEnabler
            )

            Spacer(modifier = Modifier.padding(vertical = 15.dp))

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = {
                    pageModel.resetKey("inputEn", false)
                    pageModel.resetKey("buttonEn", false)
                    Log.d("phn", phone.value.text)
                    authUIClient.sendVerificationCode(
                        "+91${phone.value.text}",
                        context as Activity
                    )
                    Log.d("otpsent", "isOtpSent value : ${authUIClient.isOtpSent.value}")
                },
                enabled = buttonEnabler,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.Cyan
                )
            ) {
                if (inputEnabler)
                    Text(text = "Generate OTP")
                else if (!isOtpSent) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(40.dp)
                            .fillMaxWidth(),
                        strokeWidth = 3.dp
                    )
                    Text(text = "Generating OTP")
                }
                else
                    Text(text = "OTP Generated!!")
            }
        }
        Spacer(modifier = Modifier.padding(20.dp))
        AnimatedVisibility(
            visible = !inputEnabler && isOtpSent,
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
                    text = "OTP sent to +91${phone.value.text}.",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 30.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Change number?",
                    modifier = Modifier
                        .clickable {
                            pageModel.resetKey("inputEn", true)
                            pageModel.resetKey("buttonEn", true)
                        }
                        .align(Alignment.CenterHorizontally),
                    color = Color.Blue,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(10.dp))

                BasicTextField(
                    value = otp.value,
                    onValueChange = {
                        if (it.text.length <=6) otp.value = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 20.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        repeat(6) { index ->
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
                        coroutineScope.launch {
                            val result = authUIClient.signInWithPhoneAuthCredentials(
                                verificationCode = otp.value.text,
                                name = name
                            )
                            onVerificationClick(
                                result
                            )
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    enabled = otp.value.text.length == 6 && isVerifyClicked
                ) {
                    Text(text = "Verify OTP")
                }
            }
        }
    }
}