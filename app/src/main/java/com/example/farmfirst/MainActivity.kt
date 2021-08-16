package com.example.farmfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.farmfirst.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


var farmername: String = ""
class MainActivity : AppCompatActivity() {

    private lateinit var dbRef : DatabaseReference
    val uid = FirebaseAuth.getInstance().uid
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readData()




        binding.btn1.setOnClickListener {
            val i= Intent(this, Buysell::class.java)
            startActivity(i)
        }


        binding.btn2.setOnClickListener {
            val i= Intent(this,Labours::class.java)
            startActivity(i)
        }

        binding.btn3.setOnClickListener {
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
                    farmername = "$username"
                    //username = " Hi, $username \n Mobile no: $mobileno \n Location: $location"
                    binding.userid.text = "Hi, $username"
                    binding.locationid.text = "$location"
                    binding.phoneid.text = "$mobileno"

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