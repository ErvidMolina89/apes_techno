package com.example.apes_techno.Presentations.Fragments

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.apes_techno.Base.BaseActivity
import com.example.apes_techno.R

class MainActivity : BaseActivity() {

    var navView: BottomNavigationView? = null
    var navController: NavController? = null
    var appBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        conf_navigation()
    }

    private fun conf_navigation(){
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home_movies,
                R.id.navigation_detail_movie
            )
        )

        setupActionBarWithNavController(navController!!, appBarConfiguration!!)
        navView?.setupWithNavController(navController!!)
    }
}
