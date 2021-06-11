package com.example.farmfirst

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*


class MyAdapter(val dataModelList: ArrayList<DataModel>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    // View holder class whose objects represent each list item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate out card list item
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
        // Return a new view holder
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Bind data for the item at position
        holder.itemView.card_title.text =  dataModelList[position].title
        holder.itemView.card_subtitle.text =  dataModelList[position].subTitle
        holder.itemView.card_body.text =  dataModelList[position].body

    }

    override fun getItemCount(): Int {
        // Return the total number of items
        return dataModelList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}