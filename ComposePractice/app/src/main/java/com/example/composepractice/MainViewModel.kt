package com.example.composepractice

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    var id = 0
    private var messages = mutableStateListOf<Message>()
    private val _messagesFlow = MutableStateFlow(messages)

    val messagesFlow: StateFlow<List<Message>> get() = _messagesFlow


    // We can retrieve an entire new list
    fun updateList(newList: List<Message>) {
        messages = newList.toMutableStateList()
        _messagesFlow.value = messages
    }
}