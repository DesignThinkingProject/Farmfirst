package com.example.farmfirst

import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.farmfirst.databinding.ActivityBuysellBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Buysell : AppCompatActivity() {

    private lateinit var dbRef : DatabaseReference
    val uid = FirebaseAuth.getInstance().uid
    private lateinit var myrecyclerview : RecyclerView
    private lateinit var mylist : ArrayList<Cropdetails>
    private lateinit var cropdetail : Cropdetails
    private lateinit var binding :ActivityBuysellBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuysellBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myrecyclerview = findViewById(R.id.listofrequests)
        myrecyclerview.layoutManager = LinearLayoutManager(this)

        mylist = arrayListOf<Cropdetails>()
        getCropdata()

        initSwipe()


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
                        startActivity(Intent(this@Buysell,Buysell::class.java))
                    }
                } else if (direction == ItemTouchHelper.RIGHT) {
                    GlobalScope.launch() {

//                        dbRef = FirebaseDatabase.getInstance().getReference("cropsbuysell").child()
//                        dbRef.removeValue();
//                        startActivity(Intent(this@Buysell,Buysell::class.java))
                    }
                }
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