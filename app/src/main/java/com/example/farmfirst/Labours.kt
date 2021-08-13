package com.example.farmfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Labours : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_labours)
    }

    fun openNewTask(view: View) {

        startActivity(Intent(this, LabourRequest :: class.java))

    }
}