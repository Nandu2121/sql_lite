package com.example.sql_lite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(data: ArrayList<Subject>, var context: Context, val listener: OnMyClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    var data: List<Subject>

    init {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false)
        return ViewHolder(layout)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    OnClickListener{
        val id: TextView
        val title: TextView
        val desc: TextView
        val delBtn: Button

        init {
            id = itemView.findViewById(R.id.id_textview)
            title = itemView.findViewById(R.id.title_textview)
            desc = itemView.findViewById(R.id.desc_textview)
            delBtn = itemView.findViewById(R.id.delete_btn)
            //itemView.setOnClickListener(this)
            delBtn.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                //listener.onItemClick(position)
                listener.deleteAction(position)
            }
        }
    }

    interface OnMyClickListener{
        //fun onItemClick(position: Int)
        fun deleteAction(position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = data[position].id
        holder.title.text = data[position].title
        holder.desc.text = data[position].desc
    }

    override fun getItemCount(): Int {
        return data.size
    }
}