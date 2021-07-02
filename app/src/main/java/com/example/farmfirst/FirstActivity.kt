package com.example.farmfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        val top  =  AnimationUtils.loadAnimation(this,R.anim.top_animation)
        val bottom = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        val image : ImageView = findViewById(R.id.imageView)
        val logo : TextView = findViewById(R.id.textView2)
        val slogan : TextView = findViewById(R.id.textView3)
        image.startAnimation(top)
        logo.startAnimation(bottom)
        slogan.startAnimation(bottom)
        Handler().postDelayed({
            startActivity(Intent(this,BuySellLogin::class.java))
            finish()
        }, 6000)
    }
}