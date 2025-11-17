package com.example.mentalhealthchat.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.mentalhealthchat.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // <-- THIS USES XML

        val chatBox = findViewById<TextView>(R.id.chatBox)
        val userInput = findViewById<EditText>(R.id.inputMsg)
        val sendButton = findViewById<Button>(R.id.sendBtn)


        chatBox.text = "Welcome to Mental Health Chat App!"

        sendButton.setOnClickListener {
            val message = userInput.text.toString().trim()

            if (message.isNotEmpty()) {
                chatBox.append("\nYou: $message")
                userInput.text.clear()
                chatBox.append("\nBot: This is a sample response!")
            }
        }
    }
}
