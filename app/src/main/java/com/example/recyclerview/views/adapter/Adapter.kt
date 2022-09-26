package com.example.recyclerview.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.objects.Members

class Adapter(
    private val members: List<Members>,
    private val onClickListener: (Members) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = members[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = members.size
}