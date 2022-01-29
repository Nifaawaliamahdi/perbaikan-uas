package com.informatika19100019.nifaawali.kalkulator

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.informatika.databarang.MainActivity
import com.informatika19100019.nifaawali.kalkulator.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            val  i = Intent (this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, 3000)
    }
}