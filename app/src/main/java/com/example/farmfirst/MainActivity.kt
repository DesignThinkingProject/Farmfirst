package com.example.farmfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var dbRef : DatabaseReference
    val uid = FirebaseAuth.getInstance().uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbRef = FirebaseDatabase.getInstance().getReference("/users/")




        btn1.setOnClickListener {
            val i= Intent(this, Buysell::class.java)
            startActivity(i)
        }


        btn2.setOnClickListener {
            val i= Intent(this,Labours::class.java)
            startActivity(i)
        }

        btn3.setOnClickListener {
            val i= Intent(this,Govtschemes::class.java)
            startActivity(i)
        }
    }
}