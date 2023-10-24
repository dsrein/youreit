package com.example.composepractice

import io.reactivex.rxjava3.core.Observable

object StubRetrofitInstance {
    var myId = 2

    val messages = mutableMapOf<String, MutableList<Message>>().apply {
        this["alpha"] = mutableListOf(Message(0, "blue", "Hey, how are you?"),
        Message(1, "blue", "It's been a while!"))
    }

    val api = object : MessageApi {
        override fun getMessages(room_id: String): Observable<List<Message>> {
            return Observable.just(messages[room_id] ?: emptyList())
        }

        override fun postMessage(
            room_id: String,
            id: Int,
            sender_id: String,
            text: String
        ): Observable<Message> {
            val message = Message(id, sender_id, text)
            myId = id
            val list = messages[room_id] ?: mutableListOf()
            list.add(message)
            messages[room_id] = list
            println(messages)
            return Observable.just(message)
        }
    }
}