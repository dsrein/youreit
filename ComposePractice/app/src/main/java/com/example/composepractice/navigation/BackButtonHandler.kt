package com.example.composepractice.navigation

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner

private val LocalBackPressDispatcher =
    staticCompositionLocalOf<OnBackPressedDispatcherOwner?> { null }


private class ComposableBackNavigationHandler(enabled: Boolean) : OnBackPressedCallback(enabled) {
    lateinit var onBackPressed: () -> Unit
    override fun handleOnBackPressed() {
        onBackPressed()
    }
}

@Composable
internal fun ComposableHandler(
    enabled: Boolean = true,
    onBackPressed: () -> Unit,
) {
    val dispatcher = (LocalBackPressDispatcher.current ?: return).onBackPressedDispatcher

    val handler = remember {
        ComposableBackNavigationHandler(enabled)
    }

    DisposableEffect(dispatcher) {
        dispatcher.addCallback(handler)
        onDispose { handler.remove() }
    }

    LaunchedEffect(enabled) {
        handler.isEnabled = enabled
        handler.onBackPressed = onBackPressed
    }
}

@Composable
internal fun SystemBackButtonHandler(onBackPressed: () -> Unit) {
    CompositionLocalProvider(
        LocalBackPressDispatcher provides LocalLifecycleOwner.current as ComponentActivity
    ) {
       ComposableHandler(onBackPressed = onBackPressed)
    }
}