package com.example.farmfirst

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_makeprofile.*
import kotlinx.android.synthetic.main.activity_request.*
import kotlinx.android.synthetic.main.activity_request.LocationEt
import kotlinx.android.synthetic.main.activity_request.NameEt

private const val TAGLOG = "SignTag"

class Request : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        saveBtn.setOnClickListener {
            saveCropToFirebaseDatabase()
        }

    }


    private fun saveCropToFirebaseDatabase() {
        val uid = FirebaseAuth.getInstance().uid
        val ref1 = FirebaseDatabase.getInstance().getReference("/cropsbuysell/")

        val cropdetail = Cropdetails(
                NameEt.text.toString(),
                LocationEt.text.toString(),
                cropwtEt.text.toString().toInt(),
                priceamountEt.text.toString().toInt(),
                noteEt.text.toString(),
                dateEt.text.toString()
            )
        //Log.d("TAGLOG", "saveCropToFirebaseDatabase: $name $location $weight $amount $note $date")
        ref1.child("$uid").setValue(cropdetail)
    }

}
class Cropdetails(val name1: String, val location: String, val weight1: Int, val amount: Int, val note1: String, val date: String)