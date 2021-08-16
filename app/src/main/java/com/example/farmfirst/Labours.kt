package com.example.farmfirst

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class
Labours : AppCompatActivity() {

    private lateinit var dbRef : DatabaseReference
    val uid = FirebaseAuth.getInstance().uid
    private lateinit var myrecyclerview : RecyclerView
    private lateinit var mylist1 : ArrayList<Labourdetails>
    private lateinit var labdetail : Labourdetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_labours)

        myrecyclerview = findViewById(R.id.listofrequests1)
        myrecyclerview.layoutManager = LinearLayoutManager(this)

        mylist1 = arrayListOf<Labourdetails>()
        getFarmerRequestData()


    }

    private fun getFarmerRequestData() {

        dbRef = FirebaseDatabase.getInstance().getReference("/labourRequest/")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        labdetail = userSnapshot.getValue(Labourdetails::class.java)!!
                        mylist1.add(labdetail)
                    }
                    myrecyclerview.adapter = MyLabourAdapter(mylist1)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun openNewTask(view: View) {
        startActivity(Intent(this, LabourRequest::class.java))
    }
}
