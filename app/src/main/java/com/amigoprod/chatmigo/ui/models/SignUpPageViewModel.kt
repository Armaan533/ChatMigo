package com.amigoprod.chatmigo.ui.models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SignUpPageViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val name = savedStateHandle.getStateFlow("name", "")
//    val phoneNumber = savedStateHandle.getStateFlow("phone", "")

    val inputEnabler = savedStateHandle.getStateFlow("inputEn", true)
    val buttonEnabler = savedStateHandle.getStateFlow("buttonEn", false)

    fun resetKey(key: String, value: String) {
        savedStateHandle[key] = value
    }
    fun resetKey(key: String, value: Boolean) {
        savedStateHandle[key] = value
    }
}