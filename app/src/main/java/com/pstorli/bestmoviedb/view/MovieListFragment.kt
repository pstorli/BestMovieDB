package com.pstorli.bestmoviedb.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.pstorli.bestmoviedb.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_list.*

class MovieListFragment : Fragment () {

    lateinit var _this: MovieListFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        parent: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Who are we?
        _this = this

        // Defines the xml file for the fragment
        val movieListView = inflater.inflate(R.layout.movie_list, parent, false)

        return movieListView
    }

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        var movieViewModel: MovieViewModel = ViewModelProvider (_this.requireActivity()).get (MovieViewModel::class.java)

        val lm = LinearLayoutManager(this.activity)

        movieListRecyclerView?.layoutManager = lm

        // Listen for the movie to change.
        movieViewModel.movie.observe(viewLifecycleOwner, { _ ->
            // Update the page.
            if (_this.requireActivity() is MainActivity) {
                _this.requireActivity().mainToolbar.title = movieViewModel.page.toString()
            }
            // Update when movie changes.
            movieListRecyclerView?.adapter = MovieAdapter(movieViewModel, _this)
        })
    }
}

