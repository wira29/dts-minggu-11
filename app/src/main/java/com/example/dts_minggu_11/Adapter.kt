package com.example.dts_minggu_11

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val data: ArrayList<Array<String>>, private val mainActivity: MainActivity) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView
        val number : TextView
        val card : CardView

        init {
            name = itemView.findViewById(R.id.txt_name)
            number = itemView.findViewById(R.id.txt_number)
            card = itemView.findViewById(R.id.card)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = data[position][0]
        holder.number.text = data[position][1]

        holder.card.setOnClickListener {
            val intent = Intent(mainActivity, DetailActivity::class.java)
            intent.putExtra("data", data[position])
            mainActivity.startActivity(intent)
        }
    }

    override fun getItemCount() = data.size
}