package com.example.MusicPlayer.Controllers

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.MusicPlayer.R
import java.io.IOException

class AlbumsFragment : Fragment() {

    private lateinit var playBtn: Button
    var mediaPlayer: MediaPlayer?= null
    var isPlaying= false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_albums, container, false)

        playBtn= v.findViewById(R.id.play_btn)

        playBtn.setOnClickListener {
            isPlaying = if (isPlaying) {
                pauseAudio()
                playBtn.text="Play Audio"
                false
            } else {
                playAudio()
                playBtn.text="Pause Audio"
                true
            }
        }
        return v
    }

    private fun playAudio() {
        //val audioURL= "https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3"
        //val audioURL= "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3"
        val audioURL="https://pagalfree.com/musics/128-Deva%20Deva%20-%20Brahmastra%20128%20Kbps.mp3"
        mediaPlayer= MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try{
            mediaPlayer!!.setDataSource(audioURL)
            mediaPlayer!!.prepare()
            mediaPlayer!!.isLooping=false
            mediaPlayer!!.start()
        }catch (e: IOException){
            e.printStackTrace()
        }
        Toast.makeText(context, "Audio started playing", Toast.LENGTH_SHORT).show()
    }

    private fun pauseAudio() {
        if (mediaPlayer!=null && mediaPlayer!!.isPlaying){
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
            Toast.makeText(context,"Audio is paused", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Audio has not started playing", Toast.LENGTH_SHORT).show()
        }
    }

}