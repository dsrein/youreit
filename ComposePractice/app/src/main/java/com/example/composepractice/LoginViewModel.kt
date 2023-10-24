package com.example.composepractice

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {
    private var username by mutableStateOf("")
    private val _usernameFlow = MutableStateFlow(username)
    val usernameFlow: StateFlow<String> get() = _usernameFlow

    private var password by mutableStateOf("")
    private val _passworFlow = MutableStateFlow(password)
    val passwordFlow: StateFlow<String> get() = _passworFlow

    private var passwordVisible by mutableStateOf(false)
    private val _passwordVisibleFlow = MutableStateFlow(passwordVisible)
    val passwordVisibleFlow: StateFlow<Boolean> get() = _passwordVisibleFlow

    fun inputUsername(text: String) {
        username = text
        _usernameFlow.value = username
    }

    fun inputPassword(text: String) {
        password = text
        _passworFlow.value = username
    }

    fun setPasswordVisibility(isVisible: Boolean) {
        passwordVisible = isVisible
        _passwordVisibleFlow.value = passwordVisible
    }
}