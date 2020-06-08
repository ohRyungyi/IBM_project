package com.example.ibm_project

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class recentTermAdapter(var items:ArrayList<researchTerms>):RecyclerView.Adapter<recentTermAdapter.MyViewHolder>() {
    lateinit var itemclick:OnItemClickListener

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var searchView: TextView =itemView.findViewById(R.id.terms)
        var deleteView:TextView=itemView.findViewById(R.id.delete)
        init{
            /*itemView.setOnClickListener{
                itemclick.onItemClick(this,it,items[adapterPosition],adapterPosition)
            }*/
            searchView.setOnClickListener {
                itemclick.onItemClick(this,it,items[adapterPosition],adapterPosition)
            }
            deleteView.setOnClickListener {
                itemclick.onItemClick(this,it,items[adapterPosition],adapterPosition)
            }
        }
    }

    fun dataCheck(terms:String):Boolean{
        for(i in 0 until items.size){
            if(items[i].term==terms){
                return true
            }
        }
        return false
    }

    fun addData(terms:String){
        if(dataCheck(terms)){
            return
        }
        else{
            items.add(researchTerms(terms))
            notifyItemInserted(items.size-1)

        }
    }

    fun deleteData(pos:Int){
        items.removeAt(pos)
        notifyItemRemoved(pos)
    }

    interface OnItemClickListener{
        fun onItemClick(holder:MyViewHolder,view:View,data:researchTerms, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.recent_term,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        Log.i("item 갯수 : ",items.size.toString())
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.searchView.text=items[position].term
    }
}