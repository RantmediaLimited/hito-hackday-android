package com.allianz.hackday

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class ItemsAdapter :
    RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    private var titlesList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = titlesList[position]

        holder.title.text = item
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.title_TV
    }

    override fun getItemCount(): Int {
        return titlesList.size
    }

    fun setupData(data: MutableList<String>) {
        titlesList = data
        notifyDataSetChanged()
    }

    fun removeAt(position: Int) {
        titlesList.removeAt(position)
        notifyItemRemoved(position)
    }

}

