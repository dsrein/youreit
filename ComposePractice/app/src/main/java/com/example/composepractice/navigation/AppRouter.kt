package com.example.composepractice.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object AppRouter {
    val currentScreen : MutableState<Screen> = mutableStateOf(Screen.SignupScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}

sealed class Screen {
    object Login : Screen()
    object MessageScreen : Screen()
    object SignupScreen : Screen()
    object TermsAndConditionsScreen : Screen()
}