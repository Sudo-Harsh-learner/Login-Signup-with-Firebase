package com.example.exp7.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.exp7.navigate.Screen
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel() {
    var logoutInProcess = mutableStateOf(false)

    fun onEvent(event: HomeUiEvent, navController: NavHostController){
       when(event){
           is HomeUiEvent.logoutButtonClicked -> {
               logout(navController=navController)
           }
       }
    }

    private fun logout(navController: NavHostController){
        logoutInProcess.value = true
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListner = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                logoutInProcess.value = false
                navController.navigate(Screen.LoginScreen.route){
                    popUpTo(Screen.LoginScreen.route){
                        inclusive = true
                    }
                }
            }
        }
        firebaseAuth.addAuthStateListener(authStateListner)
    }
}