package com.example.composepractice.screens

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composepractice.components.NormalTextComponent
import com.example.composepractice.navigation.AppRouter
import com.example.composepractice.navigation.Screen
import com.example.composepractice.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionsScreen() {
    Surface() {
        NormalTextComponent(text = "Terms And Conditions",
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            )
        )
    }

    SystemBackButtonHandler {
        AppRouter.navigateTo(Screen.SignupScreen)
    }
}