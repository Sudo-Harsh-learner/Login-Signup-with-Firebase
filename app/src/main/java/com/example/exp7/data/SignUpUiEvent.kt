package com.example.exp7.data

sealed class SignUpUiEvent {
    data class NameChanged(val name:String) : SignUpUiEvent()
    data class MailChanged(val mail:String) : SignUpUiEvent()
    data class PhoneChanged(val phone:String) : SignUpUiEvent()
    data class PassChanged(val password:String) : SignUpUiEvent()
    object registerButtonClicked : SignUpUiEvent()

}