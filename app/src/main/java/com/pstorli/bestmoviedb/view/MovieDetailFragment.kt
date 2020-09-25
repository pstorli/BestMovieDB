package com.pstorli.bestmoviedb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.pstorli.bestmoviedb.R
import kotlinx.android.synthetic.main.movie_detail.view.*

class MovieDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Defines the xml file for the fragment
        val view = inflater.inflate(R.layout.movie_detail, parent, false)

        // Go to movie list frag if they click the movieDetailButton
        view.movieDetailButton.setOnClickListener(
            {
                // Navigate to the detail fragment.
                view.findNavController().navigate(R.id.action_movieDetailFragment_to_movieListFragment)
            })

        return view
    }
}