package com.example.farmfirst

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*


class MyAdapter( val listener: SchemeItemClicked) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    // View holder class whose objects represent each list item

    val dataModelList: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate out card list item
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
        val viewHolder = MyViewHolder(itemView)
        itemView.setOnClickListener{
            listener.onItemClicked(dataModelList[viewHolder.adapterPosition])
        }
        // Return a new view holder
        return viewHolder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // Bind data for the item at position
        holder.itemView.card_title.text =  dataModelList[position].title
        //holder.itemView.card_subtitle.text =  dataModelList[position].author
        holder.itemView.card_body.text =  dataModelList[position].content
        Glide.with(holder.itemView.context).load(dataModelList[position].imageUrl).into(holder.image)

    }

    fun updateNews(updatedNews: ArrayList<News>) {
        dataModelList.clear()
        dataModelList.addAll(updatedNews)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        // Return the total number of items
        return dataModelList.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val titleView: TextView = itemView.findViewById(R.id.title)
        val image: ImageView = itemView.findViewById(R.id.image)
        //val content: TextView = itemView.findViewById(R.id.content)
    }
}

interface SchemeItemClicked {
    fun onItemClicked(item: News)
}
