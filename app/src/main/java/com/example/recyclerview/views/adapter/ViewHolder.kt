package com.example.recyclerview.views.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.databinding.ItemAdapterBinding
import com.example.recyclerview.objects.Members

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemAdapterBinding.bind(view)

    /**
     *  Muestra los datos del layout
     */
    fun render(members: Members, onClickListener: (Members) -> Unit) {
        binding.nameTextView.text = members.nombre
        binding.lastNameTextView.text = members.lastName
        binding.workTextView.text = members.cargo
        Glide.with(binding.photoMemberImageView.context).load(members.urlFoto)
            .into(binding.photoMemberImageView)
        itemView.setOnClickListener { onClickListener(members) }
    }
}