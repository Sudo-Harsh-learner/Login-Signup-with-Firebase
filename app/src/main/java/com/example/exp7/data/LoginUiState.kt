package com.example.exp7.data

data class LoginUiState (
    var mail: String = "",
    var password: String = "",

    var mailError : Boolean = false,
    var passwordError : Boolean = false
)