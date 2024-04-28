package com.example.exp7.navigate

sealed class Screen(val route:String) {
    object SignupScreen: Screen("SignUP_Screen")
    object LoginScreen: Screen("Login_Screen")
    object HomeScreen: Screen("Home_Screen")
}