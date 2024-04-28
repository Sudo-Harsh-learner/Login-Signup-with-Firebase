package com.example.exp7.data.rules

object Validator {

    fun validateName(name:String) : ValidateResult{
        return ValidateResult(
            (name.isNotEmpty() && name.length>=4)
        )
    }

    fun validateMail( mail:String) : ValidateResult{
        val emailRegex = Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,})+$")
        return ValidateResult(
            (mail.isNotEmpty() && emailRegex.matches(mail))
        )
    }

    fun validatePhone(phone:String) : ValidateResult{
        return ValidateResult(
            (phone.isNotEmpty() && phone.length>=3)
        )
    }

    fun validatePassword(password:String) : ValidateResult{
        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()-+=])[A-Za-z\\d!@#$%^&*()-+=]{8,}$")
        return ValidateResult(
            (password.isNotEmpty() && passwordRegex.matches(password) && password.length>=8)
        )
    }

}

data class ValidateResult(
    val status:Boolean = false
)