package com.example.MusicPlayer.Controllers

import android.media.AudioManager
import android.media.Image
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MusicPlayer.Adapters.SongsAdapter
import com.example.MusicPlayer.R
import com.example.MusicPlayer.Services.SongDatasource


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

        val image= v.findViewById<ImageView>(R.id.song_image)

      /*  playIB = findViewById(R.id.idIBPlay)
        pauseIB = findViewById(R.id.idIBPause)*/

        // on below line we are
        // initializing our media player
       /* val mediaPlayer = MediaPlayer()

        recyclerView.setOnClickListener {

            // on below line we are creating a variable for our audio url
            var audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

            // on below line we are setting audio stream
            // type as stream music on below line.
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

            // on below line we are running a try
            // and catch block for our media player.
            try {
                // on below line we are setting audio
                // source as audio url on below line.
                mediaPlayer.setDataSource(audioUrl)

                // on below line we are
                // preparing our media player.
                mediaPlayer.prepare()

                // on below line we are
                // starting our media player.
                mediaPlayer.start()

            } catch (e: Exception) {

                // on below line we are handling our exception.
                e.printStackTrace()
            }
            // on below line we are displaying a toast message as audio player.
            Toast.makeText(context, "Audio started playing..", Toast.LENGTH_SHORT).show()

        }*/

        var adapter = context?.let {
            SongsAdapter(it, songDataset) { songs ->
                //got stringId
                val name = songs.getName()

                //gives string name
                println(resources.getResourceEntryName(songs.getName()))

                //converted it to string value
                val resource = resources.getString(name)

                Toast.makeText(context, resource, Toast.LENGTH_SHORT).show()

                //val productIntent = Intent(context, MainActivity::class.java)
                //productIntent.putExtra(EXTRA_CATEGORY,category.title)
                //startActivity(productIntent)


                //println(R.string.songs2)
                // val length=songDataset.size
                //println(songDataset.get(2))
                //println(getString(R.string.songs2))
                //println( resources.getString(R.string.songs2))
                /* var position=0
                 val item = songDataset[position]
                 val songkonaam = context?.resources?.getString(item.songName)
                 Toast.makeText(context, songkonaam, Toast.LENGTH_SHORT).show()*/

            }
        }
        recyclerView.adapter = adapter

        return v
    }

}