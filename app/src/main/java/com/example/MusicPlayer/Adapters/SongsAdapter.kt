package com.example.MusicPlayer.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.MusicPlayer.Model.Songs
import com.example.MusicPlayer.R


class SongsAdapter(private val context: Context, private var songDataset: List<Songs>, private val itemClick: (Songs)-> Unit) :
    RecyclerView.Adapter<SongsAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View, val itemClick: (Songs) -> Unit) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.song_image)
        val textView: TextView = view.findViewById(R.id.song_name)
        val textView1: TextView = view.findViewById(R.id.song_artist)

        fun bindCategory(songs: Songs, context: Context) {
            itemView.setOnClickListener { itemClick(songs) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.song_item, parent, false)

        return ItemViewHolder(adapterLayout, itemClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = songDataset[position]
        Glide.with(context).load(item.ImageURL).into(holder.imageView)
        holder.textView.text = context.resources.getString(item.songName)
        holder.textView1.text = context.resources.getString(item.songArtists)

        holder.bindCategory(songDataset[position],context)
    }

    override fun getItemCount() = songDataset.size

}

