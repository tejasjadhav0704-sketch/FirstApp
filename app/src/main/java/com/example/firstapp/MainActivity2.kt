package com.example.firstapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.TextView
import androidx.activity.addCallback
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {

        lateinit var database : DatabaseReference

        object tejas{
            val auth : FirebaseAuth = FirebaseAuth.getInstance()
        }

    @SuppressLint("WrongViewCast", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@MainActivity2, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }

        val emailA = findViewById<EditText>(R.id.editTextemail)
        val passW = findViewById<EditText>(R.id.editTextPassword)
        val confirmPassw = findViewById<EditText>(R.id.editTextConfirmPassword)
        val buttonSignUp = findViewById<Button>(R.id.buttonsignUp)
        val login = findViewById<TextView>(R.id.textView7)
        var intent : Intent

        buttonSignUp.setOnClickListener {
            val email = emailA.text.toString()
            val pass = passW.text.toString()
            val confirmPass = confirmPassw.text.toString()

            val store = User(email,pass)

            if(email.isEmpty() && pass.isEmpty() && confirmPass.isEmpty())
            {
                Toast.makeText(this, "all field *required", Toast.LENGTH_SHORT).show()
            }
            else
            {
                if (pass == confirmPass)
                {
                    //below code is for Authentication
                    tejas.auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful)
                        {
                            Toast.makeText(this, "User Created ", Toast.LENGTH_SHORT).show()
                            intent = Intent(this, MainActivity3::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }.addOnFailureListener {
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }

                    // below code is for Data storing in Firebase Realtime Database
                    database = FirebaseDatabase.getInstance().getReference("Users")
                    database.orderByChild("email").equalTo(email).get().addOnSuccessListener {
                        if (it.exists())
                        {
                            Toast.makeText(this, "Email Already Exists", Toast.LENGTH_SHORT).show()
                        } else
                        {
                            database.push().setValue(store)
                                .addOnSuccessListener {
                                    emailA.setText("")
                                    passW.setText("")
                                    confirmPassw.setText("")
                                    Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                        .addOnFailureListener {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        }
                }
                else
                {
                    Toast.makeText(this, "Password Must Match", Toast.LENGTH_SHORT).show()
                }
            }
        }
        login.setOnClickListener {
            intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
        }
    }
}