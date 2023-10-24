package com.example.composepractice

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.composepractice.navigation.AppRouter
import com.example.composepractice.navigation.Screen
import com.example.composepractice.screens.LoginScreen
import com.example.composepractice.screens.SignupScreen
import com.example.composepractice.screens.TermsAndConditionsScreen

@Composable
fun ComposePracticeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
    ) {
        Crossfade(targetState = AppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.Login -> {
                    LoginScreen()
                }
                is Screen.SignupScreen -> {
                    SignupScreen()
                }
                is Screen.MessageScreen -> {
                    val viewModel = MainViewModel()
                    MessageScreen(viewModel)
                    viewModel.updateList(StubRetrofitInstance.messages["alpha"]!!)
                }
                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }
            }

        }
    }

}