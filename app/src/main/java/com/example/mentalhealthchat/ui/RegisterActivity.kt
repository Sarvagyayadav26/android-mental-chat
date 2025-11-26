package com.example.mentalhealthchat.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalhealthchat.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val emailEt = findViewById<EditText>(R.id.emailEt)
        val ageEt = findViewById<EditText>(R.id.ageEt)
        val sexEt = findViewById<EditText>(R.id.sexEt)
        val btn = findViewById<Button>(R.id.registerBtn)

        btn.setOnClickListener {

            val email = emailEt.text.toString().trim()
            val ageText = ageEt.text.toString().trim()
            val sex = sexEt.text.toString().trim()

            // Validation
            if (email.isEmpty() || ageText.isEmpty() || sex.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageText.toInt()

            val req = RegisterRequest(email, age, sex)

            val api = RetrofitClient.instance   // ApiService object

            api.register(req).enqueue(object : Callback<BasicResponse> {

                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    val res = response.body()

                    if (response.isSuccessful && res?.success != null) {

                        // Save email locally
                        getSharedPreferences("app", MODE_PRIVATE)
                            .edit()
                            .putString("email", email)
                            .apply()

                        Toast.makeText(
                            this@RegisterActivity,
                            "Registered successfully!",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this@RegisterActivity, ChatActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            res?.error ?: "Registration failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}
