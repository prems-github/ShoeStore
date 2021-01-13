package com.udacity.shoestore

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.viewmodel.ShoeListViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        this.findNavController(R.id.myNavHostFragment)
    }
    private var backPressed: Long = 0
    private val twoSecond = 2000

    //Activity level view model with activity lifecycle to be used by few fragments
    val shoeListViewModel by lazy {
        ViewModelProvider(this).get(ShoeListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setting navController with action bar for activating Up button
        NavigationUI.setupActionBarWithNavController(this, navController)

        //To disable up button whenever its not applicable
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id==R.id.shoeListFragment || destination.id==R.id.loginFragment){
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }else
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)

        // disabling menu in main login page
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Timber.i("destination change listener called")
            if (destination.id == R.id.loginFragment)
                menu?.setGroupVisible(0, false)
            else
                menu?.setGroupVisible(0, true)
        }

        return true
    }

    //giving warning to users, to exit double press the back key
    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.shoeListFragment) {
            if (backPressed + twoSecond > System.currentTimeMillis())
                super.onBackPressed()
            else
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressed = System.currentTimeMillis()
        } else
            super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            navController
        ) || super.onOptionsItemSelected(item)
    }
}
