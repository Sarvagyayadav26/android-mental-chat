package com.sarvagya.mentalhealthchat.ui

data class ChatResponse(
    val reply: String?,
    val documents: List<Any>?,
    val error: String?
)
