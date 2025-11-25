package com.example.mentalhealthchat.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("app", MODE_PRIVATE)
        val email = prefs.getString("email", "")

        if (email.isNullOrEmpty()) {
            startActivity(Intent(this, RegisterActivity::class.java))
        } else {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        finish()
    }
}
