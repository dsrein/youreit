package com.example.composepractice

data class Message(
    val id: Int,
    val senderId: String,
    val text: String
) {
    val isMine = senderId == "red"
}