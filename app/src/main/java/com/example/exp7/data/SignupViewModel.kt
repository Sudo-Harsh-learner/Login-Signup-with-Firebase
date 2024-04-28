package com.example.exp7.data

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.exp7.data.rules.Validator
import com.example.exp7.navigate.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class SignupViewModel: ViewModel() {
    var registrationUiState = mutableStateOf(RegistrationUiState())
    val allValidationPassed = mutableStateOf(false)
    var signupInProgress = mutableStateOf(false)
    fun onEvent(event: SignUpUiEvent, navController: NavHostController){
        when(event){
            is SignUpUiEvent.NameChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    name = event.name
                )
            }

            is SignUpUiEvent.MailChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    mail = event.mail
                )
            }

            is SignUpUiEvent.PassChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    password = event.password
                )
            }

            is SignUpUiEvent.PhoneChanged -> {
                registrationUiState.value = registrationUiState.value.copy(
                    phone = event.phone
                )
            }
            is SignUpUiEvent.registerButtonClicked -> {
                signupInProgress.value = true
                signup(navController = navController)
            }
        }
        validateData()
    }

    private fun signup(navController: NavHostController){
        createUser( mail = registrationUiState.value.mail, pass = registrationUiState.value.password,navController=navController)
    }

    private fun validateData(){
        val nameResult = Validator.validateName(name = registrationUiState.value.name)
        val mailResult = Validator.validateMail(mail = registrationUiState.value.mail)
        val phoneResult = Validator.validatePhone(phone = registrationUiState.value.phone)
        val passwordResult = Validator.validatePassword(password = registrationUiState.value.password)

        registrationUiState.value = registrationUiState.value.copy(
            nameError = nameResult.status,
            mailError = mailResult.status,
            phoneError = phoneResult.status,
            passwordError = passwordResult.status
        )

        allValidationPassed.value = nameResult.status && mailResult.status && phoneResult.status && passwordResult.status
    }

    private fun createUser( mail:String, pass:String,navController: NavHostController){
        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(mail,pass)
                .addOnCompleteListener { task->
                    if(task.isSuccessful){
                        signupInProgress.value = false
                        navController.navigate(Screen.LoginScreen.route){
                            popUpTo(Screen.LoginScreen.route){
                                inclusive = true
                            }
                        }
                    }else{
                        signupInProgress.value = false
                    }

                }

    }



}