package com.example.farmfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var dbRef : DatabaseReference
    val uid = FirebaseAuth.getInstance().uid

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readData();

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
    private fun readData() {
        dbRef = FirebaseDatabase.getInstance().getReference("users")
        if (uid != null) {
            dbRef.child(uid).get().addOnSuccessListener {

                if(it.exists()){
                    val location = it.child("location").value
                    val mobileno = it.child("mobileno").value
                    var username = it.child("username").value
                    username = " Hi, $username \n Mobile no: $mobileno \n Location: $location"
                    detailsid.text = username.toString()

                }
                else{
                    Toast.makeText(this, "User Doesn't Exist ! ",Toast.LENGTH_SHORT).show()
                }


            }.addOnFailureListener{
                Toast.makeText(this, "Failed",Toast.LENGTH_SHORT).show()
            }
        }


    }
}