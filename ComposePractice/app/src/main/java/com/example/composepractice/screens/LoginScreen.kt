package com.example.composepractice.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepractice.components.*
import com.example.composepractice.navigation.AppRouter
import com.example.composepractice.navigation.Screen

@Preview
@Composable
fun LoginScreen() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NormalTextComponent(
                text = "Login",
                textStyle = headerTextStyle
            )
            Spacer(modifier = Modifier.height(20.dp))
            NormalTextFieldComponent("Email", Icons.Outlined.Email)
            PasswordTextFieldComponent("Password")
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComponent("Login")
            DividerTextComponent()
            ClickableLoginTextComponent(
                questionText = "Don't have an account yet?",
                navigationText = "Register"
            ) {
                AppRouter.navigateTo(Screen.SignupScreen)
            }
        }
    }
}
