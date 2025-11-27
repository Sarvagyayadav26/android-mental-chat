package com.sarvagya.mentalhealthchat.ui

data class ChatResponse(
    val allowed: Boolean,
    val reply: String?,
    val usage_now: Int?,
    val limit: Int?,
    val processing_time: Double?,
    val error: String?
)
