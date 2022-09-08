package com.example.MusicPlayer.Controllers

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.MusicPlayer.R


class ArtistsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v=inflater.inflate(R.layout.fragment_artists, container, false)

        var ring: MediaPlayer= MediaPlayer.create(context, R.raw.devadeva)
        val bgBtn= v.findViewById<Button>(R.id.bg_Btn)

        var count=0
        bgBtn.setOnClickListener {
            count+=1
            //plays as background music //auto plays over the previous one at every click
            ring.start()
            println(count)
            if (count%2==0){
                ring.stop()
                var ring: MediaPlayer= MediaPlayer.create(context, R.raw.devadeva)
                ring.start()
                count=2
            }
        }


        // Inflate the layout for this fragment
        return v

    }
}