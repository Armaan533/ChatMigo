package com.amigoprod.chatmigo

import android.os.Parcelable
import com.amigoprod.chatmigo.model.User
import kotlinx.parcelize.Parcelize



class SignInResult(
    val data: User?,
    val errorMsg: String?
)

@Parcelize
class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
) : Parcelable

