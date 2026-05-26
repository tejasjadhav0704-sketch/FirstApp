package com.example.firstapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.EditText
import androidx.activity.addCallback
import android.widget.Toast
class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@MainActivity3, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        setContentView(R.layout.activity_main3)

        val emailA =findViewById<EditText>(R.id.editTextemail)
        val passW = findViewById<EditText>(R.id.editTextPassword)
        val signup = findViewById<TextView>(R.id.textView7)

        signup.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}