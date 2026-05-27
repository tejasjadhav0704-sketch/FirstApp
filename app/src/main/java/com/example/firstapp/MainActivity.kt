package com.example.firstapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Switch

class MainActivity : AppCompatActivity() {

    lateinit var switch1 : Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        switch1 = findViewById<Switch>(R.id.switch1)


        switch1.setOnClickListener {
            if (switch1.isChecked) {
                val intent = Intent(this, MainActivity3::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        switch1.isChecked = false
    }
}
