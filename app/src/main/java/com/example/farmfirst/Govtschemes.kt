package com.example.farmfirst

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_govtschemes.*


class Govtschemes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_govtschemes)
        val dataModelList = DataModel.genDataModels(20)
        val myadapter = MyAdapter(dataModelList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = myadapter
    }
}