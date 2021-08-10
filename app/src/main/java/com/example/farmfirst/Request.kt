package com.example.farmfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.farmfirst.databinding.ActivityRequestBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


private const val TAGLOG = "SignTag"



class Request : AppCompatActivity() {

    private lateinit var binding: ActivityRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            saveCropToFirebaseDatabase()
            val i= Intent(this, Buysell::class.java)
            startActivity(i)
        }

    }


    private fun saveCropToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid
        val ref1 = FirebaseDatabase.getInstance().getReference("/cropsbuysell/")

        val cropdetail = Cropdetails(
            binding.NameEt.text.toString(),
            binding.LocationEt.text.toString(),
            binding.cropwtEt.text.toString().toInt(),
            binding.priceamountEt.text.toString().toInt(),
                binding.noteEt.text.toString(),
                binding.dateEt.text.toString()
            )
        //Log.d("TAGLOG", "saveCropToFirebaseDatabase: $name $location $weight $amount $note $date")
        ref1.child("$uid").setValue(cropdetail)
    }

}
data class Cropdetails(val name1: String?=null, val location: String?=null, val weight1: Int?=null, val amount: Int?=null, val note1: String?=null, val date: String?=null)