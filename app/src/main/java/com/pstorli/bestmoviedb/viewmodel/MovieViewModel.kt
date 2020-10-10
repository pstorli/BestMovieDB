package com.pstorli.ticketrider.model

import android.app.Application

import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.repo.MovieRepository
import androidx.lifecycle.MutableLiveData
import com.pstorli.bestmoviedb.logError
import com.pstorli.bestmoviedb.util.Consts
import kotlinx.coroutines.*
import java.util.*

class MovieViewModel (application: Application)  : Observer, AndroidViewModel(application)
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Movie Stuff
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Our repo where we get our data from.
    val movieRepo = MovieRepository ()

    // When the movies change, let the listeners know.
    var movies: List<Movie> = ArrayList ()

    // The selected movie, as live data.
    var movie = MutableLiveData<Movie> ()

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Coroutine stuff
    // /////////////////////////////////////////////////////////////////////////////////////////////

    // Got parent?
    private val parentJob                             = Job()

    // Got exception handler?
    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            coroutineScope.launch(Dispatchers.Main) {
                throwable.message.toString().logError()
            }
        }

    // Got scope?
    private val coroutineScope = CoroutineScope(
        Dispatchers.Main + parentJob + coroutineExceptionHandler)

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Now, Wake up refreshed.
    // /////////////////////////////////////////////////////////////////////////////////////////////
    init {
        loadMovies()
    }

    /**
     * refreshModelFromRepos
     */
    override fun update (p0: Observable?, p1: Any?) {
        loadMovies()
    }

    /**
     * Update the data in the model from the repos.
     *
     * Load movies from repo using coroutines
     *
     * This routine launches which, when done,
     */
    fun loadMovies () {

        // Launch on main thread.
        coroutineScope.launch (Dispatchers.Main) {
            // /////////////////////////////////////////////////////////////////////////////////////
            // NOTE: This blocks and returns only when done.
            // Refresh from repo on IO thread.
            // /////////////////////////////////////////////////////////////////////////////////////
            this@MovieViewModel.movies = movieRepo.loadMovies()

            // /////////////////////////////////////////////////////////////////////////////////////
            // Now that we have the data, notify the observers. Notify of update by
            // setting selected movie to the first movie.
            // /////////////////////////////////////////////////////////////////////////////////////

            // TODO: Try to keep selected movie the same between reloads, if possible.
            movie.value = movies.get(0)
        }
    }
}

