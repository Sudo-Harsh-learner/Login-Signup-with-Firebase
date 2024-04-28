package com.example.exp7.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.exp7.Component.ButtonComponent
import com.example.exp7.Component.NormalText
import com.example.exp7.R
import com.example.exp7.data.HomeUiEvent
import com.example.exp7.data.HomeViewModel
import com.example.exp7.data.LoginViewModel
import com.example.exp7.data.SignupViewModel

@Composable
fun HomeScreen(navController: NavHostController, homeViewModel: HomeViewModel = viewModel()){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(24.dp)){
        Column (modifier=Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(.1f))
            NormalText(
                value = stringResource(id = R.string.welcome_home),
                size = 24,
                weight = FontWeight.Bold
            )
            if(homeViewModel.logoutInProcess.value){
                Spacer(modifier = Modifier.height(20.dp))
                CircularProgressIndicator()
                Spacer(modifier = Modifier.fillMaxHeight(.3f))
            }else{
                Spacer(modifier = Modifier.fillMaxHeight(.4f))
            }
            ButtonComponent(value = R.string.logout, onButtonClicked = {
                homeViewModel.onEvent(HomeUiEvent.logoutButtonClicked,navController=navController)
            }, isEnabled = true)

        }
    }
}