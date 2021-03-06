package com.pstorli.bestmoviedb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.pstorli.bestmoviedb.R
import com.pstorli.bestmoviedb.loadImage
import com.pstorli.bestmoviedb.model.Movie
import kotlinx.android.synthetic.main.movie_detail.view.*
import kotlinx.android.synthetic.main.movie_detail.view.image
import kotlinx.android.synthetic.main.movie_detail.view.title

class MovieDetailFragment : Fragment () {

    override fun onCreateView (
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Defines the xml file for the fragment
        val movieDetailView = inflater.inflate(R.layout.movie_detail, parent, false)

        // Get our view model.
        var movieViewModel = ViewModelProvider (this).get (MovieViewModel::class.java)

        // Update the details with the selected Movie
        // Was something selected?
        if (null != movieViewModel.movie.value) {

            // Got movie?
            val selectedMovie: Movie?                = movieViewModel.movie.value
            if (null != selectedMovie) {
                // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // Set the title, subtitle and description.
                // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                // Title
                movieDetailView.title.text          = selectedMovie.title

                // Release date
                movieDetailView.release_date.text   = selectedMovie.release_date

                // Genre
                movieDetailView.genre.text          = movieViewModel.getGenres (selectedMovie)

                // Dec
                movieDetailView.description.text    = selectedMovie.overview

                // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // Load the image.
                // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                movieDetailView.image.loadImage (selectedMovie, activity)
            }
        }

        // Go to movie list frag if they click the movieDetailButton
        movieDetailView.image.setOnClickListener {
            // Navigate to the movie list fragment.
            movieDetailView.findNavController().navigate(R.id.action_movieDetailFragment_to_movieListFragment)
        }

        return movieDetailView
    }
}

