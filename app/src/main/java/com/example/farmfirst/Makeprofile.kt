package com.example.farmfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_makeprofile.*

class Makeprofile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makeprofile)

        registerme.setOnClickListener {
            saveUserToFirebaseDatabase()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun saveUserToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid!!,
            NameEt.text.toString(),
            PhoneNumberEt.text.toString(),
            LocationEt.text.toString()
        )

        ref.setValue(user)
    }
}
class User(val uid: String ,val username: String, val mobileno: String, val location: String)