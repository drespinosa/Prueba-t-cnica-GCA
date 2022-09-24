package com.example.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val superheroList: List<ListItem>,
    private val onClickListener: (ListItem) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_adapter, parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = superheroList[position]
        holder.render(item, onClickListener)

    }

    override fun getItemCount(): Int = superheroList.size
}