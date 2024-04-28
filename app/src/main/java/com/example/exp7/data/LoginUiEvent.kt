package com.example.exp7.data

sealed class LoginUiEvent {
    data class MailChanged(val mail:String) : LoginUiEvent()
    data class PassChanged(val password:String) : LoginUiEvent()
    object loginButtonClicked : LoginUiEvent()
}