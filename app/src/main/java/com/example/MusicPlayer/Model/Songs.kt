package com.example.MusicPlayer.Model

data class Songs(val ImageURL: String, var songName: Int, val songArtists: Int, val songAudio: Int){
    fun getName(): Int {
        return songName
    }
    fun getArtist(): Int {
        return songArtists
    }
    fun getAudio(): Int {
        return songAudio
    }
}
