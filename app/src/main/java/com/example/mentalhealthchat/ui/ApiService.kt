package com.example.mentalhealthchat.ui

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class RegisterRequest(
    val email: String,
    val age: Int,
    val sex: String
)

data class BasicResponse(
    val success: String?,
    val error: String?
)

data class ChatRequest(
    val email: String,
    val message: String
)

data class ChatResponse(
    val reply: String?,
    val documents: List<String>?,
    val error: String?
)

interface ApiService {

    @POST("auth/register")
    fun register(@Body req: RegisterRequest): Call<BasicResponse>

    @POST("chat")
    fun chat(@Body req: ChatRequest): Call<ChatResponse>
}
