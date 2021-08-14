package com.example.farmfirst

import com.example.farmfirst.databinding.ActivityLabourRequestBinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.farmfirst.databinding.ActivityRequestBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


private const val TAGLOG = "SignTag"



class LabourRequest : AppCompatActivity() {

    private lateinit var binding: ActivityLabourRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLabourRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn1.setOnClickListener {
            saveLabourToFirebaseDatabase()
            val i= Intent(this, Labours::class.java)
            startActivity(i)
        }

    }


    private fun saveLabourToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid
        val ref1 = FirebaseDatabase.getInstance().getReference("/labourRequest/")

        val labourdetail = Labourdetails(
            binding.NameEt1.text.toString(),
            binding.LocationEt1.text.toString(),
            binding.daysEt1.text.toString().toInt(),
            binding.wageEt2.text.toString().toInt(),
            binding.noteEt1.text.toString(),
            binding.dateEt1.text.toString()
        )
        //Log.d("TAGLOG", "saveCropToFirebaseDatabase: $name $location $weight $amount $note $date")
        ref1.child("$uid").setValue(labourdetail)
    }

}
data class Labourdetails(val name1: String?=null, val location: String?=null, val days: Int?=null,
                         val wage: Int?=null, val phone: String?=null, val date : String?=null)