package com.sarvagya.mentalhealthchat.ui

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class BasicResponse(
    val success: String?,
    val error: String?
)

interface ApiService {

    @POST("auth/register")
    fun register(@Body req: RegisterRequest): Call<BasicResponse>

    @POST("auth/login")
    fun login(@Body req: LoginRequest): Call<LoginResponse>

    @POST("chat")
    fun chat(@Body req: ChatRequest): Call<ChatResponse>
}
