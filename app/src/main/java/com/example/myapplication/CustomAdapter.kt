package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class activity_character : AppCompatActivity() {


    class CustomAdapter(private val characters: List<Character>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
            val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_layout, viewGroup, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
            val character = characters[i]
            viewHolder.itemNombre.text = character.fullName
            viewHolder.itemId.text = character.id.toString()
            viewHolder.itemTitle.text = character.title
            viewHolder.itemFamily.text = character.family
            Picasso.get().load(character.imageUrl).into(viewHolder.itemImage)
        }

        override fun getItemCount(): Int {
            return characters.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var itemImage: ImageView
            var itemNombre: TextView
            var itemId: TextView
            var itemTitle: TextView
            var itemFamily: TextView

            init {
                itemImage = itemView.findViewById(R.id.item_image)
                itemNombre = itemView.findViewById(R.id.item_nombre)
                itemId = itemView.findViewById(R.id.item_id)
                itemTitle = itemView.findViewById(R.id.item_title)
                itemFamily = itemView.findViewById(R.id.item_family)
            }

        }
    }
}