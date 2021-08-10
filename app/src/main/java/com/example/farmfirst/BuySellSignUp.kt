package com.example.farmfirst

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farmfirst.databinding.ActivityBuysellsignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class BuySellSignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var email = ""
    private var password = ""
    private lateinit var binding: ActivityBuysellsignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuysellsignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.signUpBtn.setOnClickListener {
            Log.d(TAG, "onCreate: signupbtnclicked")
            email = binding.usernameEt.text.toString().trim()
            password = binding.passwordEt.text.toString().trim()
            createAccount(email, password)
        }

    }
    private fun createAccount(email: String, password: String) {
        // [START create_user_with_email]
        Log.d(TAG, "createAccount: $email $password")
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Log.d(TAG, "createAccount: failed")
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
        // [END create_user_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
        startActivity(Intent(this, Makeprofile::class.java))
    }

//    private fun reload() {
//
//    }
    companion object {
        private const val TAG = "EmailPassword"
    }
}