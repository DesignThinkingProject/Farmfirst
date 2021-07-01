package com.example.farmfirst

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.farmfirst.databinding.ActivityBuysellsignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_buysellsignup.*

class BuySellSignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buysellsignup)
        auth = Firebase.auth

        signUpBtn.setOnClickListener {
            Log.d(TAG, "onCreate: signupbtnclicked")
            email = usernameEt.text.toString().trim()
            password = passwordEt.text.toString().trim()
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
                    updateUI(null)
                }
            }
        // [END create_user_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {

    }

//    private fun reload() {
//
//    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}