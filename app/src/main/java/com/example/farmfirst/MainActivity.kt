package com.example.farmfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            val i= Intent(this,Buysell::class.java)
        }


        btn2.setOnClickListener {
            val i= Intent(this,Buysell::class.java)
        }

        btn3.setOnClickListener {
            val i= Intent(this,Buysell::class.java)
        }
    }
}