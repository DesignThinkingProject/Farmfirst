package com.example.farmfirst

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farmfirst.databinding.ActivityBuysellloginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class BuySellLogin : AppCompatActivity() {

    private val RC_SIGN_IN: Int = 123
    private lateinit var auth: FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth
    private val TAG = "SignInActivity Tag"
    private var email = ""
    private var password = ""
    private lateinit var googleSignInClient : GoogleSignInClient
    private lateinit var binding: ActivityBuysellloginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuysellloginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        //firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth = Firebase.auth


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)


        binding.noAccountTv.setOnClickListener {
            Log.d(TAG, "googlesign: ok")
            val i= Intent(this, BuySellSignUp::class.java)
            startActivity(i)
        }

        binding.loginBtn.setOnClickListener{
            email = binding.usernameEt.text.toString().trim()
            password = binding.passwordEt.text.toString().trim()
            signIn(email,password)
        }

        binding.signInbutton.setOnClickListener {
            googlesign()
        }

    }

    private fun googlesign() {
        Log.d(TAG, "googlesign: ok")
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>?) {
        try {
            val account =
                completedTask!!.getResult(ApiException::class.java)!!
            Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
            firebaseAuthWithGoogle(account.idToken!!)
        } catch (e: ApiException) {
            
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            Log.d(TAG, "handleSignInResult: $e")
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = firebaseAuth.currentUser
                    if (user != null) {
                        updateUI(user)
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)

                }
            }
    }

    private fun updateUI(firebaseUser: Any) {
        startActivity(Intent(this, MainActivity::class.java))

    }

    private fun signIn(email: String, password: String) {
        Log.d("TAG", "signIn: $email $password")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { signIn ->
                if (signIn.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this,"sign in success",Toast.LENGTH_SHORT).show()
                    finish()
                }
                Log.d("TAG", "signIn: ${signIn.isSuccessful}")
            }
    }

}