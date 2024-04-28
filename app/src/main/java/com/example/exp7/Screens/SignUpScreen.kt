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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.exp7.Component.ButtonComponent
import com.example.exp7.Component.ClickableLoginText
import com.example.exp7.Component.DividerTextComponent
import com.example.exp7.Component.InputField
import com.example.exp7.Component.NormalText
import com.example.exp7.Component.PassField
import com.example.exp7.R
import com.example.exp7.data.SignupViewModel
import com.example.exp7.data.SignUpUiEvent

@Composable
fun SignUpScreen(navController: NavHostController, signupViewModel: SignupViewModel = viewModel()){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(24.dp)){
        Column (modifier=Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            ){
            Spacer(modifier = Modifier.fillMaxHeight(.1f))
            NormalText(value = stringResource(R.string.Create),24, FontWeight.Bold)
            NormalText(value = stringResource(R.string.connect_with_your_friends_today),14, FontWeight.SemiBold)
            InputField(title = "Enter your Name", hint = "Harshdeep Singh",R.drawable.user, KeyboardType.Text, onText = {
                    signupViewModel.onEvent(SignUpUiEvent.NameChanged(it), navController = navController)
            },errorStatus = signupViewModel.registrationUiState.value.nameError)
            InputField(title = "Enter your Email", hint = "No1Noob@gmail.com", R.drawable.email,
                KeyboardType.Email, onText = {
                    signupViewModel.onEvent(SignUpUiEvent.MailChanged(it), navController = navController)
                },errorStatus = signupViewModel.registrationUiState.value.mailError)
            InputField(title = "Enter your Phone Number", hint = "123232151", R.drawable.phone,
                KeyboardType.Phone, onText = {
                    signupViewModel.onEvent(SignUpUiEvent.PhoneChanged(it), navController = navController)
                },errorStatus = signupViewModel.registrationUiState.value.phoneError)
            PassField(title = "Enter your Password",R.drawable.lock,check = true, onText = {
                signupViewModel.onEvent(SignUpUiEvent.PassChanged(it), navController=navController)
            },errorStatus = signupViewModel.registrationUiState.value.passwordError)
            if(signupViewModel.signupInProgress.value){
                Spacer(modifier = Modifier.height(20.dp))
                CircularProgressIndicator()
                Spacer(modifier = Modifier.fillMaxHeight(.2f))
            }else{
                Spacer(modifier = Modifier.fillMaxHeight(.3f))
            }
            ButtonComponent(value = R.string.register, onButtonClicked = {
                signupViewModel.onEvent(SignUpUiEvent.registerButtonClicked,navController=navController)
            }, isEnabled = signupViewModel.allValidationPassed.value)
            DividerTextComponent()
            ClickableLoginText(init = "Already have an account? ", click = " Login",navController=navController)

        }

    }
}