package com.example.farmfirst

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Buysell : AppCompatActivity() {

    private lateinit var dbRef : DatabaseReference
    val uid = FirebaseAuth.getInstance().uid
    private lateinit var myrecyclerview : RecyclerView
    private lateinit var mylist : ArrayList<Cropdetails>
    private lateinit var cropdetail : Cropdetails

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buysell)

        myrecyclerview = findViewById(R.id.listofrequests)
        myrecyclerview.layoutManager = LinearLayoutManager(this)

        mylist = arrayListOf<Cropdetails>()
        getCropdata()


    }

    private fun getCropdata() {

        dbRef = FirebaseDatabase.getInstance().getReference("cropsbuysell")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        cropdetail = userSnapshot.getValue(Cropdetails::class.java)!!
                        mylist.add(cropdetail)
                    }
                    myrecyclerview.adapter = MyAdapterBuysell(mylist)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }

    fun openNewTask(view: View) {
        startActivity(Intent(this, Request::class.java))
    }
}