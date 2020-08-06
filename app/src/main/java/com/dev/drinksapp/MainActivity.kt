package com.dev.drinksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import com.dev.drinksapp.db.DrinkDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var drinkDao: DrinkDao

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Agregar boton atras en bar
        //navController = findViewById(R.id.nav_host_fragment)
        //NavigationUI.setupActionBarWithNavController(this, navController)
        Log.d("DRINKSDAO", "onCreate: ${drinkDao.hashCode()}")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}