package com.example.farmfirst

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Labours : AppCompatActivity() {
    private lateinit var dbRef : DatabaseReference
    val uid = FirebaseAuth.getInstance().uid
    private lateinit var myrecyclerview : RecyclerView
    private lateinit var mylist : ArrayList<Labourdetails>
    private lateinit var labourdetail : Labourdetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_labours)
        myrecyclerview = findViewById(R.id.listofrequests)
        myrecyclerview.layoutManager = LinearLayoutManager(this)

        mylist = arrayListOf<Labourdetails>()
        getLabourdata()
    }
    private fun getLabourdata() {

        dbRef = FirebaseDatabase.getInstance().getReference("/labourRequest/")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        labourdetail = userSnapshot.getValue(Labourdetails::class.java)!!
                        mylist.add(labourdetail)
                    }
                    myrecyclerview.adapter = MyAdapterBuysell(mylist)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun openNewTask(view: View) {

        startActivity(Intent(this, LabourRequest :: class.java))

    }
}