package com.example.farmfirst

import android.content.Intent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Buysell : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buysell)

    }

    fun openNewTask(view: View) {
        startActivity(Intent(this, Request::class.java))
    }
}