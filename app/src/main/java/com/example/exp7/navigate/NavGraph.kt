package com.example.exp7.navigate

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.exp7.Screens.HomeScreen
import com.example.exp7.Screens.LoginScreen
import com.example.exp7.Screens.SignUpScreen

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route ){
            composable(route = Screen.LoginScreen.route){
                    LoginScreen(navController=navController)
            }
            composable(route=Screen.SignupScreen.route){
                SignUpScreen(navController=navController)
            }
        composable(route=Screen.HomeScreen.route ){
            HomeScreen(navController=navController)
        }
    }
}