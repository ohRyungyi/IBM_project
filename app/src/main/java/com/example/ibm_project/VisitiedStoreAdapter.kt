package com.example.ibm_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VisitiedStoreAdapter(var items:ArrayList<StoreData>):RecyclerView.Adapter<VisitiedStoreAdapter.MyViewHolder>() {

    lateinit var onitemclick:OnItemClickListener

    interface OnItemClickListener{
        fun itemclick(viewHolder: MyViewHolder,view:View, data:StoreData,position: Int)
    }
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var total:LinearLayout=itemView.findViewById(R.id.totalView)
        var imageView:ImageView=itemView.findViewById<ImageView>(R.id.storeimage)
        var nameView: TextView=itemView.findViewById(R.id.storeName)
        var addressView:TextView=itemView.findViewById(R.id.location)
        var distanceView:TextView=itemView.findViewById(R.id.distance)
        var stateView:TextView=itemView.findViewById(R.id.state)
        var dayView:TextView=itemView.findViewById(R.id.daysago)

        init{
            total.setOnClickListener {
                onitemclick.itemclick(this,it,items[adapterPosition],adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.hasvisitedstorlist,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if(items[position].image==null){
            holder.imageView.setImageResource(R.drawable.samplesotre)
        }
        holder.nameView.text=items[position].name
        holder.addressView.text=items[position].address
        holder.distanceView.text=items[position].distance.toString()
        when(items[position].state){
            -1->{
                holder.stateView.text="휴업"
            }
            0->{
                holder.stateView.text="운영 시간 종료"
            }
            1->{
                holder.stateView.text="운영 중"
            }
        }
        holder.dayView.text=items[position].daysago.toString()
    }

}