package com.example.farmfirst

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Labours : AppCompatActivity() {

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


        initSwipe()

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

    fun initSwipe() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                    GlobalScope.launch() {
                        mylist1.removeAt(position);
                        startActivity(Intent(this@Labours,Buysell::class.java))
                    }
                } else if (direction == ItemTouchHelper.RIGHT) {

                    val perm:Int= ContextCompat.checkSelfPermission(this@Labours, Manifest.permission.CALL_PHONE)
                    if(perm == PackageManager.PERMISSION_GRANTED){
                        val number = mylist1[position].phone
                        val intent= Intent()
                        intent.action=Intent.ACTION_CALL
                        intent.data= Uri.parse("tel:$number")
                        startActivity(intent)
                    }
                    else {
                        //var pms:String=Manifest.permission.CALL_PHONE
                        ActivityCompat.requestPermissions(this@Labours, arrayOf(Manifest.permission.CALL_PHONE),200)
                        val perm:Int= ContextCompat.checkSelfPermission(this@Labours, Manifest.permission.CALL_PHONE)
                    }

                }
                //startActivity(Intent(this@Buysell,Buysell::class.java))
            }

            override fun onChildDraw(
                canvas: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    val itemView = viewHolder.itemView

                    val paint = Paint()
                    val icon: Bitmap

                    if (dX > 0) {

                        icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_check_white_png)

                        paint.color = Color.parseColor("#388E3C")

                        canvas.drawRect(
                            itemView.left.toFloat(), itemView.top.toFloat(),
                            itemView.left.toFloat() + dX, itemView.bottom.toFloat(), paint
                        )

                        canvas.drawBitmap(
                            icon,
                            itemView.left.toFloat(),
                            itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - icon.height.toFloat()) / 2,
                            paint
                        )


                    } else {
                        icon = BitmapFactory.decodeResource(resources, R.mipmap.ic_delete_white_png)

                        paint.color = Color.parseColor("#D32F2F")

                        canvas.drawRect(
                            itemView.right.toFloat() + dX, itemView.top.toFloat(),
                            itemView.right.toFloat(), itemView.bottom.toFloat(), paint
                        )

                        canvas.drawBitmap(
                            icon,
                            itemView.right.toFloat() - icon.width,
                            itemView.top.toFloat() + (itemView.bottom.toFloat() - itemView.top.toFloat() - icon.height.toFloat()) / 2,
                            paint
                        )
                    }
                    viewHolder.itemView.translationX = dX


                } else {
                    super.onChildDraw(
                        canvas,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }


        }

        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(myrecyclerview)
    }

    fun openNewTask(view: View) {
        startActivity(Intent(this, LabourRequest::class.java))
    }
}
