package com.example.firstapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.addCallback
import com.google.firebase.auth.FirebaseAuth

class MainActivity3 : AppCompatActivity() {

    lateinit var auth : FirebaseAuth

    @SuppressLint("MissingInflatedId")
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
        val signIn = findViewById<Button>(R.id.buttonsignin)
        val signup = findViewById<TextView>(R.id.textView7)

        auth = FirebaseAuth.getInstance()

        signIn.setOnClickListener {
            if(emailA.text.isEmpty() && passW.text.isEmpty())
            {
                Toast.makeText(this, "all field *required", Toast.LENGTH_SHORT).show()
            }
            else
            {
                auth.signInWithEmailAndPassword(emailA.text.toString(),passW.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful)
                    {
                        Toast.makeText(this, "Signed-In", Toast.LENGTH_SHORT).show()
                        intent = Intent(this, MainActivity4::class.java)
                        startActivity(intent)
                        finish()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        signup.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
}