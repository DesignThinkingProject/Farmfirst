package com.example.farmfirst

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyLabourAdapter(private val detailslist: ArrayList<Labourdetails>) : RecyclerView.Adapter<MyLabourAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_labour
            ,parent,false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentdetail =  detailslist[position]

        holder.showTitle1.text = currentdetail.title
        holder.showLocation1.text = currentdetail.location
        holder.showCropName1.text = currentdetail.name1
        holder.showDays1.text = currentdetail.days.toString()
        holder.showMoney1.text = currentdetail.wage.toString()
        holder.showPhone1.text = currentdetail.phone.toString()
        holder.showDate1.text = currentdetail.date
    }

    override fun getItemCount(): Int {
        return detailslist.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val showTitle1 : TextView = itemView.findViewById(R.id.txtShowTitle1)
        val showDays1 : TextView = itemView.findViewById(R.id.txtDays1)
        val showMoney1 : TextView = itemView.findViewById(R.id.txtMoney1)
        val showPhone1 : TextView = itemView.findViewById(R.id.txtPhone1)
        val showCropName1 : TextView = itemView.findViewById(R.id.txtShowCropName1)
        val showLocation1 :TextView = itemView.findViewById(R.id.txtLocation1)
        val showDate1 : TextView = itemView.findViewById(R.id.txtShowDate1)
    }
}