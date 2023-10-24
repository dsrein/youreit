package com.example.composepractice

import io.reactivex.rxjava3.core.Observable
//import retrofit2.http.*

interface MessageApi {


//    @GET("/chat/{room_id}/messages")
//    fun getMessages(@Path("room_id") room_id: String) : Observable<List<Message>>
    fun getMessages(room_id: String): Observable<List<Message>>

//    @POST("chat/{room_id}/messages")
    fun postMessage(
//        @Path("room_id") room_id: String,
//        @Query("id") id: Int,
//        @Query("sender_id") sender_id: String,
//        @Query("text") text: String,
        room_id: String, id: Int, sender_id: String, text: String,
    ) : Observable<Message>
}