package com.amigoprod.chatmigo.ui.models

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.amigoprod.chatmigo.SignInResult
import com.amigoprod.chatmigo.SignInState


class SignInViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val state = savedStateHandle.getStateFlow("state", SignInState())

    fun onSignInResult(result: SignInResult) {
        savedStateHandle["state"] = SignInState(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMsg
        )
        Log.d("onSignIn", "State updated")
    }

    fun resetState() {
        savedStateHandle["state"] = SignInState()
    }

}