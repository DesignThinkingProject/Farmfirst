package com.example.farmfirst

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapterBuysell(private val detailslist :ArrayList<Cropdetails>) : RecyclerView.Adapter<MyAdapterBuysell.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_buysell
        ,parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentdetail =  detailslist[position]

        holder.showTitle.text = currentdetail.name1
        holder.showLocation.text = currentdetail.location
        holder.showMoney.text = currentdetail.amount.toString()
        holder.showQuantity.text = currentdetail.weight1.toString()
        holder.showDate.text = currentdetail.date
    }

    override fun getItemCount(): Int {
        return detailslist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val showTitle : TextView = itemView.findViewById(R.id.txtShowTitle)
        val showQuantity : TextView = itemView.findViewById(R.id.txtShowQuantity)
        val showMoney : TextView = itemView.findViewById(R.id.txtMoney)
        val showName : TextView = itemView.findViewById(R.id.txtShowName)
        val showLocation :TextView = itemView.findViewById(R.id.txtLocation)
        val showDate : TextView = itemView.findViewById(R.id.txtShowDate)
    }
}