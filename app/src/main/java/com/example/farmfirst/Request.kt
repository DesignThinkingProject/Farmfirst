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
        val ref1 = FirebaseDatabase.getInstance().getReference("/cropsbuysell/$uid")

        val name = NameEt.text.toString()
        val location = LocationEt.text.toString()
        val weight = cropwtEt.text.toString().toInt()
        val amount = priceamountEt.text.toString().toInt()
        val note = noteEt.text.toString()
        val date = dateEt.text

        val cropdetail = Cropdetails(name, location, weight, amount, note, date)
        Log.d("TAGok", "saveCropToFirebaseDatabase: $name $location $weight $amount $note $date")
        ref1.setValue(cropdetail)
    }

}
class Cropdetails(val name1: String, val location: String, val weight1: Int, val amount: Int, val note1: String, val date: Editable?)