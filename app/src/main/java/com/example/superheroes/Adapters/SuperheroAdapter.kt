package com.example.superheroes.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.data.SuperHero
import com.example.superheroes.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroAdapter(private var items: List<SuperHero>, val onItemClick: (Int)-> Unit) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val superHero = items[position]
        holder.render(superHero)
        holder.itemView.setOnClickListener {
            onItemClick(position) }

    }

    fun updatesItems(items: List<SuperHero>)
    {
        this.items = items
        notifyDataSetChanged()

    }
}


class ViewHolder(val binding: ItemSuperheroBinding) : RecyclerView.ViewHolder(binding.root) // cambiar atributos por binding de superheroes

{

    fun render(superHero: SuperHero)
    {
        binding.TextViewTittleSH.text=superHero.name
        Picasso.get().load(superHero.image.url).into(binding.heroImageView)


    }


}
