package com.pstorli.bestmoviedb.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.pstorli.bestmoviedb.R
import kotlinx.android.synthetic.main.movie_list.view.*

class MovieListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Defines the xml file for the fragment
        val view = inflater.inflate(R.layout.movie_list, parent, false)

        // Go to movie detail frag if they click the moveListButton
        view.moveListButton.setOnClickListener(
            {
                (view.moveListButton as Button).setTextColor(Color.parseColor("#FF0000"))

                // Navigate to the detail fragment.
                view.findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment)
            })

        return view
    }
}