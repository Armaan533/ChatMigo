package com.amigoprod.chatmigo.ui.models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.amigoprod.chatmigo.SignInResult
import com.amigoprod.chatmigo.SignInState
import com.amigoprod.chatmigo.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

//    private var _state = MutableStateFlow(SignInState())
//    private var _user = MutableStateFlow(User())

    val state = savedStateHandle.getStateFlow("state", SignInState())
    val user = savedStateHandle.getStateFlow("user", User())

    fun onSignInResult(result: SignInResult) {
        savedStateHandle["state"] = result
        savedStateHandle["user"] = result.data
//        _state.update {
//            it.copy(
//                isSignInSuccessful = result.data != null,
//                signInError = result.errorMsg
//            )
//        }
//        _user.update {
//            it.copy(
//                uid = it.uid,
//                name = it.name,
//                phone = it.phone
//            )
//        }
    }

    fun resetState() {
        savedStateHandle["state"] = SignInState()
        savedStateHandle["user"] = User()
//        _state.update {
//            SignInState()
//        }
//        _user.update {
//            User()
//        }
    }

}