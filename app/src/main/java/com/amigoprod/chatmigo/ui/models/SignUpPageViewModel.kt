package com.amigoprod.chatmigo.ui.models

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class SignUpPageViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    val name = savedStateHandle.getStateFlow("name", "").value
    val phoneNumber = savedStateHandle.getStateFlow("phone", "").value
    val inputEnabler = savedStateHandle.getStateFlow("inputEn", true).value
    val buttonEnabler = savedStateHandle.getStateFlow("buttonEn", false).value

    fun resetKey(key: String, value: String) {
        savedStateHandle[key] = value
    }
    fun resetKey(key: String, value: Boolean) {
        savedStateHandle[key] = value
    }
}