package com.amigoprod.chatmigo.auth

import androidx.lifecycle.ViewModel
import com.amigoprod.chatmigo.SignInResult
import com.amigoprod.chatmigo.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel: ViewModel() {
    private var _state = MutableStateFlow(SignInState())
    val state =_state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMsg
            )
        }
    }

    fun resetState() {
        _state.update {
            SignInState()
        }
    }

}