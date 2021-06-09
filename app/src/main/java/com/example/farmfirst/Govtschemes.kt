package com.example.farmfirst

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Govtschemes : AppCompatActivity() {
    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_govtschemes)
        mRecyclerView = findViewById(R.id.recycler_view)
        val dataModelList: MutableList<DataModel> = ArrayList()
        for (i in 1..20) {
            dataModelList.add(DataModel(i))
        }

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true)

        // use a linear layout manager
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView.setLayoutManager(mLayoutManager)

        // specify an adapter and pass in our data model list
        mAdapter = MyAdapter(dataModelList, this)
        mRecyclerView.setAdapter(mAdapter)


    }
}