package com.example.farmfirst

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MyAdapter(private val dataModelList: List<DataModel>, private val mContext: Context) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    // View holder class whose objects represent each list item
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bodyTextView: TextView
        var titleTextView: TextView
        var subTitleTextView: TextView
        fun bindData(dataModel: DataModel, context: Context?) {
            titleTextView.text = dataModel.title
            subTitleTextView.text = dataModel.subTitle
            bodyTextView.text = dataModel.body
        }

        init {
            titleTextView = itemView.findViewById(R.id.card_title)
            subTitleTextView = itemView.findViewById(R.id.card_subtitle)
            bodyTextView = itemView.findViewById(R.id.card_body)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate out card list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        // Return a new view holder
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Bind data for the item at position
        holder.bindData(dataModelList[position], mContext)
    }

    override fun getItemCount(): Int {
        // Return the total number of items
        return dataModelList.size
    }
}