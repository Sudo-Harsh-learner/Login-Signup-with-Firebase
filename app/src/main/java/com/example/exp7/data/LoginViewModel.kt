package com.example.exp7.data

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.exp7.data.rules.Validator
import com.example.exp7.navigate.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {

    var loginUiState = mutableStateOf(LoginUiState())
    val allValidationPassed = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)


    fun onEvent(event: LoginUiEvent, navController: NavHostController, context: Context){
        when(event){

            is LoginUiEvent.MailChanged -> {
                loginUiState.value = loginUiState.value.copy(
                    mail = event.mail
                )
            }

            is LoginUiEvent.PassChanged -> {
                loginUiState.value = loginUiState.value.copy(
                    password = event.password
                )
            }

            is LoginUiEvent.loginButtonClicked -> {
                loginInProgress.value = true
                login(mail = loginUiState.value.mail, pass = loginUiState.value.password, navController = navController, context=context)
            }
        }
        validateData()
    }

    private fun validateData(){
        val mailResult = Validator.validateMail(mail = loginUiState.value.mail)
        val passwordResult = Validator.validatePassword(password = loginUiState.value.password)

        loginUiState.value = loginUiState.value.copy(
            mailError = mailResult.status,
            passwordError = passwordResult.status
        )

        allValidationPassed.value =  mailResult.status && passwordResult.status
    }

    private fun login(mail:String,pass:String,navController: NavHostController, context: Context){
            FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(mail,pass)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        loginInProgress.value = false
                        navController.navigate(Screen.HomeScreen.route){
                            popUpTo(Screen.HomeScreen.route){
                                inclusive = true
                            }
                        }
                    }else{
                        loginInProgress.value = false
                        Toast.makeText(context,"Invalid Credentials!!",Toast.LENGTH_SHORT).show()

                    }

                }
    }

}