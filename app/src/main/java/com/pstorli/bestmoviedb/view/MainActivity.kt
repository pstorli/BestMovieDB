package com.pstorli.bestmoviedb.view

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity
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
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_launcher)

        mainToolbar.setNavigationIcon(R.drawable.ic_back)
        mainToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    override fun onSupportNavigateUp() = findNavController(this, R.id.navController).navigateUp()
}