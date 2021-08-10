package com.example.farmfirst

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.farmfirst.databinding.ActivityMakeprofileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Makeprofile : AppCompatActivity() {

    private lateinit var binding: ActivityMakeprofileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakeprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerme.setOnClickListener {
            saveUserToFirebaseDatabase()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun saveUserToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user = User(uid!!,
            binding.NameEt.text.toString(),
            binding.PhoneNumberEt.text.toString(),
            binding.LocationEt.text.toString()
        )

        ref.setValue(user)
    }
}
class User(val uid: String ,val username: String, val mobileno: String, val location: String)