package com.example.contacts.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.contacts.R

class OnBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_board)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@OnBoardActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
}