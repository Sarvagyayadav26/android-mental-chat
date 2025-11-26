package com.example.mentalhealthchat.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalhealthchat.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatActivity : AppCompatActivity() {

    private lateinit var chatBox: TextView
    private lateinit var inputBox: EditText
    private lateinit var sendBtn: Button
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        chatBox = findViewById(R.id.chatBox)
        inputBox = findViewById(R.id.inputBox)
        sendBtn = findViewById(R.id.sendBtn)
        scrollView = findViewById(R.id.chatScroll)

        // Get email from shared preferences
        val email = getSharedPreferences("app", MODE_PRIVATE)
            .getString("email", null)

        if (email == null) {
            Toast.makeText(this, "No user email found!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val api = RetrofitClient.instance

        sendBtn.setOnClickListener {

            val message = inputBox.text.toString().trim()
            if (message.isEmpty()) return@setOnClickListener

            chatBox.append("\nYou: $message\n")

            val req = ChatRequest(email, message)

            api.chat(req).enqueue(object : Callback<ChatResponse> {
                override fun onResponse(
                    call: Call<ChatResponse>,
                    response: Response<ChatResponse>
                ) {
                    val res = response.body()
                    val botReply = res?.reply  ?: "Error: No reply"

                    chatBox.append("\nBot: $botReply\n")
                    scrollView.post { scrollView.fullScroll(ScrollView.FOCUS_DOWN) }
                }

                override fun onFailure(call: Call<ChatResponse>, t: Throwable) {
                    chatBox.append("\n[Error: ${t.message}]\n")
                }
            })
        }
    }
}
