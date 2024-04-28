package com.example.exp7.data

data class RegistrationUiState(
    var name : String = "",
    var mail: String = "",
    var phone: String = "",
    var password: String = "",

    var nameError : Boolean = false,
    var mailError : Boolean = false,
    var phoneError : Boolean = false,
    var passwordError : Boolean = false
)
