package com.example.ibm_project

import android.accessibilityservice.GestureDescription
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class detailVisitedStoreAdapter(var items:ArrayList<StoreData>):RecyclerView.Adapter<detailVisitedStoreAdapter.MyViewHolder>() {
    lateinit var onclicklistener:OnItemClick
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var image=itemView.findViewById<ImageView>(R.id.image)
        var name=itemView.findViewById<TextView>(R.id.name)
        var daysago=itemView.findViewById<TextView>(R.id.daysago)
        var address=itemView.findViewById<TextView>(R.id.address)
        var phone=itemView.findViewById<TextView>(R.id.phone)
        var distance=itemView.findViewById<TextView>(R.id.distance)
        var state=itemView.findViewById<TextView>(R.id.state)
        var touch=itemView.findViewById<LinearLayout>(R.id.touch)
        init{
            touch.setOnClickListener{
                onclicklistener.itemclick(this,it,items[adapterPosition],adapterPosition)
            }
        }
    }
    interface OnItemClick{
        fun itemclick(viewholder:MyViewHolder,view:View, data:StoreData,position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.detailhasvisitedstorelist,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(items[position].conjuction>=1.5){
            holder.image.setImageResource(R.drawable.congestionlowinmap)
        }else{
            holder.image.setImageResource(R.drawable.congestionhighinmap)
        }
        holder.name.text=items[position].name
        holder.daysago.text=items[position].daysago.toString()+"일 전"
        holder.address.text=items[position].address
        holder.phone.text=items[position].phone
        holder.distance.text=items[position].distance.toString()+"m"
        when(items[position].state){
            1->{
                holder.state.text="영업 중"
            }
            0->{
                holder.state.text="영업 종료"
            }
            -1->{
                holder.state.text="휴업 중"
            }
        }
    }
}