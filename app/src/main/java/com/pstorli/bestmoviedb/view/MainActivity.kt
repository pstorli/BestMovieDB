package com.pstorli.bestmoviedb.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.pstorli.bestmoviedb.R
import com.pstorli.bestmoviedb.logInfo
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getString(R.string.starting).logInfo()

        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_launcher)

        mainToolbar.setTitle("")
        mainToolbar.setSubtitle("")

        mainToolbar.setNavigationIcon(R.drawable.ic_back)
        mainToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onSupportNavigateUp() = findNavController(this, R.id.navController).navigateUp()

    /**
     * Inflate/Add options menu
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * handle toolbar option menu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        // Handle item selection
        return when (item.getItemId()) {

            // Refresh cached data?
            R.id.action_refresh -> {
                // Delete all cached movies.
                movieViewModel.deleteAll()
                true // We handled it.
            }

            R.id.action_prev -> {
                // Go to the previous page.
                val errorMsg = movieViewModel.prevPage()
                if (errorMsg.isNotEmpty()) {
                    // Show the error.
                    showSnackBar(errorMsg)
                }

                true
            }

            R.id.action_next -> {
                // Go to the next page.
                val errorMsg = movieViewModel.nextPage()
                if (errorMsg.isNotEmpty()) {
                    // Show the error.
                    showSnackBar(errorMsg)
                }

                true
            }

            // Something else?
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * Show some snacks.
     */
    fun showSnackBar(resId: Int) {
        showSnackBar(getString(resId))
    }

    /**
     * Show some snacks.
     */
    fun showSnackBar(msg: String) {
        // Create the snackbar.
        val sb = Snackbar.make(mainView, msg, Snackbar.LENGTH_LONG)

        // Get the snackbat text view.
        val snackText: TextView = sb.view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)

        // Set the snackbar font size.
        snackText.textSize = 24.0f

        // Show the snackbar.
        sb.show()
    }
}