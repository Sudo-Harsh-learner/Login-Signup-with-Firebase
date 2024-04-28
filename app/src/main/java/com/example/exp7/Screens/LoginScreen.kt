package com.example.exp7.Screens

import android.content.Context
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.exp7.Component.UnderlineNormalText
import com.example.exp7.R
import com.example.exp7.data.LoginUiEvent
import com.example.exp7.data.LoginViewModel

@Composable
fun LoginScreen(navController: NavHostController, loginViewModel: LoginViewModel = viewModel()){
    val context : Context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(24.dp)){
        Column (modifier=Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(.1f))
            NormalText(value = stringResource(id = R.string.hello), size = 24, weight = FontWeight.Bold)
            NormalText(value = stringResource(R.string.connect_with_your_friends_today),14, FontWeight.SemiBold)
            InputField(title = "Enter your Email", hint = "No1Noob@gmail.com", R.drawable.email,
                KeyboardType.Email, onText = {
                    loginViewModel.onEvent(LoginUiEvent.MailChanged(it), navController = navController,context=context)
                }, errorStatus = loginViewModel.loginUiState.value.mailError)
            PassField(title = "Enter your Password",R.drawable.lock, onText = {
                loginViewModel.onEvent(LoginUiEvent.PassChanged(it),navController=navController,context=context)
            }, errorStatus = loginViewModel.loginUiState.value.passwordError)
            UnderlineNormalText(value = stringResource(id = R.string.forgot_Text), size = 18, weight = FontWeight.Normal)
            if(loginViewModel.loginInProgress.value){
                Spacer(modifier = Modifier.height(20.dp))
                CircularProgressIndicator()
                Spacer(modifier = Modifier.fillMaxHeight(.2f))
            }else{
                Spacer(modifier = Modifier.fillMaxHeight(.3f))
            }
            ButtonComponent(value = R.string.login_btn, onButtonClicked = {
                loginViewModel.onEvent(LoginUiEvent.loginButtonClicked,navController=navController, context=context)
            }, isEnabled = loginViewModel.allValidationPassed.value)
            DividerTextComponent()
            ClickableLoginText(init = "Don't have an account yet? ", click = " Register",navController=navController,login = true)
        }
    }
}