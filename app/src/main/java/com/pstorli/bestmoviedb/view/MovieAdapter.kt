package com.pstorli.bestmoviedb.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pstorli.bestmoviedb.model.Movie

class MovieAdapter(private val movieViewModel: MovieViewModel, private val movieListFragment: MovieListFragment) : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent, movieListFragment)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: Movie = movieViewModel.movies.results?.get(position) ?: Movie()

        // Set the view model.
        holder.bind (movie)
    }

    override fun getItemCount(): Int = movieViewModel.movies.results?.size?:0
}