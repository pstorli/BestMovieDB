package com.pstorli.bestmoviedb.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.pstorli.bestmoviedb.R
import com.pstorli.bestmoviedb.logInfo
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getString(R.string.starting).logInfo()

        setContentView(R.layout.activity_main)
        setSupportActionBar (mainToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_launcher)

        mainToolbar.setNavigationIcon(R.drawable.ic_back)
        mainToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onSupportNavigateUp() = findNavController(this, R.id.navController).navigateUp()

    /**
     * Inflate/Add options menu
     */
    override fun onCreateOptionsMenu (menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * handle toolbar option menu
     */
    override fun onOptionsItemSelected (item: MenuItem): Boolean {
        // Handle item selection
        return when (item.getItemId()) {
            R.id.action_refresh -> {
                val movieViewModel = ViewModelProvider (this).get (MovieViewModel::class.java)

                // Delete all cached movies.
                movieViewModel.deleteAll()
                true // We handled it.
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}