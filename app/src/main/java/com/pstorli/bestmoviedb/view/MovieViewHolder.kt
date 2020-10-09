package com.pstorli.bestmoviedb.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pstorli.bestmoviedb.R
import com.pstorli.bestmoviedb.loadImage
import com.pstorli.bestmoviedb.model.Movie
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieViewHolder (inflater: LayoutInflater, val parent: ViewGroup, val movieListFragment: MovieListFragment) :
    RecyclerView.ViewHolder (inflater.inflate(R.layout.movie_list_item, parent, false)) {

    var movie:Movie? = null

    fun bind (movie: Movie) {
        // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Remember the movie.
        // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        this.movie = movie

        // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Load the image.
        // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Set the title.
        itemView.title.text =  movie.title

        // Load the image
        itemView.image.loadImage(
            movie,
            movieListFragment.activity
        )

        // Listen for item click events.
        itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Set this as the seletced movie.
                movieListFragment.movieViewModel.movie.value = movie

                // Navigate to the detail fragment.
                parent.findNavController().navigate(R.id.action_movieListFragment_to_movieDetailFragment)
            }
        })
    }
}
