package com.example.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.databinding.ItemAdapterBinding

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemAdapterBinding.bind(view)

    /*
    val superHero = view.findViewById<TextView>(R.id.tvSuperHero)
    val realName = view.findViewById<TextView>(R.id.tvrealName)
    val publiser = view.findViewById<TextView>(R.id.tvpublisher)
    val photo = view.findViewById<ImageView>(R.id.ivSuperHero)
     */


    fun render(ListItem: ListItem, onClickListener: (ListItem) -> Unit) {
        binding.tvSuperHero.text = ListItem.superhero
        binding.tvrealName.text = ListItem.realName
        binding.tvpublisher.text = ListItem.publisher
        Glide.with(binding.ivSuperHero.context).load(ListItem.photo).into(binding.ivSuperHero)

        /*binding.ivSuperHero.setOnClickListener {
            Toast.makeText(
                binding.ivSuperHero.context,
                ListItem.realName,
                Toast.LENGTH_SHORT
            ).show()
        }*/
        /*itemView.setOnClickListener {
            Toast.makeText(
                binding.ivSuperHero.context,
                ListItem.superhero,
                Toast.LENGTH_SHORT)
        }*/

        itemView.setOnClickListener { onClickListener(ListItem) }

    }
}