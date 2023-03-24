package com.example.MusicPlayer.Controllers

import android.content.res.Resources
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.MusicPlayer.Adapters.SongsAdapter
import com.example.MusicPlayer.R
import com.example.MusicPlayer.Services.SongDatasource
import java.io.IOException


class SongsFragment : Fragment() {
    var mediaPlayer: MediaPlayer? = null
    var isPlaying = false
    var resource: String? = null

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

        var adapter = context?.let {
            SongsAdapter(it, songDataset) { songs ->
                //got stringId
                val name = songs.getName()
                val audioId= songs.getAudio()
                println(audioId)

                //gives string name
                //val songName= resources.getResourceEntryName(name)
                //val audioName= resources.getResourceEntryName(audioId)

                //converted it to string value
                resource = resources.getString(name)
                //for extracting location
                val audio= resources.getString(audioId)
                println(audio)

                //playing songs from raw
                val song= audio.split('/','.')
                //getting song Name
                val songName= song[2]
                println(songName)

                val res: Resources = resources
                val soundId: Int = res.getIdentifier(songName, "raw", requireContext().packageName)

                //if resource folder doesnot have the song, play from online link
                println(soundId)
                if (soundId==0){
                    isPlaying = if (isPlaying) {
                        pauseAudio()
                        false
                    } else {
                        playAudio(audio)
                        true
                    }
                }else{
                    //if found, play from res
                    isPlaying = if (isPlaying) {
                        pauseSong()
                        false
                    } else {
                        playSong(soundId)
                        true
                    }
                }


               /* isPlaying = if (isPlaying) {
                    pauseAudio()
                    false
                } else {
                    Toast.makeText(context, "Be patient. Playing of the song takes few seconds", Toast.LENGTH_SHORT).show()
                    playAudio(audio)
                    true
                }*/
            }

        }
        recyclerView.adapter = adapter
        return v
    }

    //for raw files
    private fun playSong(soundId: Int) {
        var ring = MediaPlayer.create(context, soundId)
        mediaPlayer=ring
        try {
            ring.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Toast.makeText(context, "$resource started playing", Toast.LENGTH_SHORT).show()
    }

    private fun pauseSong() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
            Toast.makeText(context, "Audio is paused", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Audio has not started playing", Toast.LENGTH_SHORT).show()
        }
    }


    //for Url
    private fun playAudio(audioURL: String) {

        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mediaPlayer!!.setDataSource(audioURL)
            mediaPlayer!!.prepare()
            mediaPlayer!!.isLooping = false
            mediaPlayer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Toast.makeText(context, "$resource started playing", Toast.LENGTH_SHORT).show()
    }

    private fun pauseAudio() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
            Toast.makeText(context, "Audio is paused", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Audio has not started playing", Toast.LENGTH_SHORT).show()
        }
    }

}