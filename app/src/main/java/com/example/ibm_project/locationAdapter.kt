package com.example.ibm_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.view.*

class locationAdapter(var items:ArrayList<String>):RecyclerView.Adapter<locationAdapter.MyViewHolder>() {
    lateinit var itemclicklistener:OnItemClickListener

    interface OnItemClickListener{
        fun itemClick(hoder:MyViewHolder,view:View, data:String, position:Int)
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var listView: TextView =itemView.findViewById(R.id.address)
        init{
            listView.setOnClickListener {
                itemclicklistener.itemClick(this,it,items[adapterPosition],adapterPosition)
            }
        }
    }

    fun refresh(){
        refresh()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.addresslist,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.listView.text=items[position]
    }
}