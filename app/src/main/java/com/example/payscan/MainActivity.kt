package com.example.payscan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val login_button = findViewById<Button>(R.id.login_btn)
        login_button.setOnClickListener {
            val intent = Intent(this, home::class.java)
            intent.putExtra("EXIT", true)
            startActivity(intent)


        }
    }
}
