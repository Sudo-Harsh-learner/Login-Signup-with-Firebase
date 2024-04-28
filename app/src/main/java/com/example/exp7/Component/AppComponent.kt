package com.example.exp7.Component

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.exp7.R
import com.example.exp7.navigate.Screen
import com.example.exp7.ui.theme.Purple40
import com.example.exp7.ui.theme.Purple80
import com.example.exp7.ui.theme.inputFieldLable


@Composable
fun NormalText(value:String,size: Int,weight: FontWeight,align:TextAlign= TextAlign.Center){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        color = inputFieldLable,
        style = TextStyle(
            fontSize = size.sp,
            fontWeight = weight,
            fontStyle = FontStyle.Normal
        ),
        textAlign = align
    )
}

@Composable
fun InputField(title:String,hint:String,icon:Int,type:KeyboardType, onText: (String) -> Unit, errorStatus:Boolean=false){
    val text = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        value = text.value,
        onValueChange ={
            text.value = it
            onText(it)
        },
        placeholder = {
            Text(text = hint)
        },
        label = {
            Text(text = title,
                color = inputFieldLable,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = type,
            imeAction = ImeAction.Next
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = Color.Black,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary

        ),
        singleLine = true,
        leadingIcon = {
            Icon(painter = painterResource(id = icon), contentDescription = null )
        },
        isError = !errorStatus
        )
}

@Composable
fun PassField(title:String,icon:Int,check:Boolean=false, onText: (String) -> Unit, errorStatus:Boolean=false){
    val pass = remember {
        mutableStateOf("")
    }
    val passVisible = remember {
        mutableStateOf(false)
    }
    val localFocus = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        value = pass.value,
        onValueChange = {
            pass.value = it
            onText(it)
        },
        label = {
            Text(text = title,
                color = inputFieldLable,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                fontFamily = FontFamily.Monospace
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                localFocus.clearFocus()
            }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = Color.Black,
            unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.secondary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary),
        singleLine = true,
        leadingIcon = {
            Icon(painter = painterResource(id = icon), contentDescription = null)
        },
        trailingIcon = {
            val iconImage = if(passVisible.value){
                Icons.Filled.Visibility
            }else{
                Icons.Filled.VisibilityOff
            }

            val description = if(passVisible.value)
                "Hide Password"
            else{
                "Show Password"
            }
            IconButton(onClick = { passVisible.value = !passVisible.value }) {
               Icon( imageVector = iconImage, contentDescription = description)
            }
        },
        visualTransformation = if(passVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus
    )
    if(check){
        CheckBoxComponent(value = R.string.check)

    }

}

@Composable
fun CheckBoxComponent(value: Int){
    Row(modifier= Modifier
        .fillMaxWidth()
        .heightIn(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val checkState = remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkState.value, onCheckedChange = {
            checkState.value = !checkState.value
        })
        NormalText(value = stringResource(id = value), size = 15, weight = FontWeight.Normal,
            TextAlign.Left)
    }
}

@Composable
fun ButtonComponent(value: Int, isEnabled:Boolean=false, onButtonClicked: () -> Unit){
    Button(onClick = { onButtonClicked.invoke() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            Color.Transparent
        ),
        enabled = isEnabled
        ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Purple80, Purple40)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
            ){
                Text(text = stringResource(id = value),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                    )
        }
    }
}

@Composable
fun DividerTextComponent(){
    Row (modifier= Modifier
        .fillMaxWidth()
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically){
        Divider(modifier= Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Gray,
            thickness = 1.dp)
        Text(text = "OR", fontSize = 14.sp, color = Color.Black)
        Divider(modifier= Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Gray,
            thickness = 1.dp)
    }
}

@Composable
fun ClickableLoginText(init:String,click:String,navController: NavHostController,login:Boolean=false){
    val annotatedString = buildAnnotatedString {
        append(init)
        withStyle(style = SpanStyle(color = Purple40)){
            pushStringAnnotation(tag = click, annotation = click)
            append(click)
        }
    }
    ClickableText(text = annotatedString) {
        if (login){
            navController.navigate(Screen.SignupScreen.route){
                popUpTo(Screen.SignupScreen.route){
                    inclusive = true
                }
            }
        }else{
            navController.navigate(Screen.LoginScreen.route){
                popUpTo(Screen.LoginScreen.route){
                    inclusive = true
                }
            }
        }
    }
}

@Composable
fun UnderlineNormalText(value:String,size: Int,weight: FontWeight,align:TextAlign= TextAlign.Center){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = size.sp,
            fontWeight = weight,
            fontStyle = FontStyle.Normal
        ),color = Purple40,
        textAlign = align,
        textDecoration = TextDecoration.Underline
    )
}
