package com.example.MusicPlayer.Controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MusicPlayer.Adapters.SongsAdapter
import com.example.MusicPlayer.R
import com.example.MusicPlayer.Services.SongDatasource
import kotlin.reflect.typeOf


class SongsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_songs, container, false)
        // Initialize data.
        val songDataset = SongDatasource().loadSongs()

        val recyclerView = v.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)


       /* var position=0
        val item = songDataset[position]
        val songkonaam = context?.resources?.getString(item.songName)
        Toast.makeText(context, songkonaam, Toast.LENGTH_SHORT).show()*/


        var adapter = context?.let {
            SongsAdapter(it, songDataset) { songs ->
                //got stringId
                val name= songs.getName()

                //gives string name
                println(resources.getResourceEntryName(songs.getName()))

                //converted it to string value
                val resource= resources.getString(name)

                Toast.makeText(context, resource, Toast.LENGTH_SHORT).show()




                //val productIntent = Intent(context, MainActivity::class.java)
                //productIntent.putExtra(EXTRA_CATEGORY,category.title)
                //startActivity(productIntent)

                //println(R.string.songs2)
                // val length=songDataset.size
                //println(songDataset.get(2))
                //println(getString(R.string.songs2))
                //println( resources.getString(R.string.songs2))
            }
        }

        recyclerView.adapter = adapter

        return v
    }
}