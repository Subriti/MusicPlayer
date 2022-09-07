package com.example.MusicPlayer.Controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.MusicPlayer.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bottom navigation bar
        val bottomNavigationView= findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        //fragment view that would change every time
        val navController= findNavController(R.id.nav_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.songsFragment, R.id.artistsFragment, R.id.albumsFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        //mapping ig nav view with fragments
        bottomNavigationView.setupWithNavController(navController)
    }
}