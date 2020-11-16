package com.pstorli.bestmoviedb.view

import android.app.Application
import android.content.SharedPreferences

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pstorli.bestmoviedb.logError
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.repo.MovieRepository
import com.pstorli.bestmoviedb.Consts.PAGE
import com.pstorli.bestmoviedb.Consts.PAGE_DEF
import com.pstorli.bestmoviedb.Consts.PREF_NAME
import com.pstorli.bestmoviedb.Consts.PRIVATE_MODE
import kotlinx.coroutines.*
import java.util.*

class MovieViewModel (application: Application)  : Observer, AndroidViewModel(application)
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Vars
    // /////////////////////////////////////////////////////////////////////////////////////////////
    var sharedPrefs: SharedPreferences
    var app:         Application

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Movie Stuff
    // /////////////////////////////////////////////////////////////////////////////////////////////
    var page = 0 // What page are we on?

    // When the movies change, let the listeners know.
    lateinit var movies: Movies

    // The selected movie, as live data.
    var movie = MutableLiveData<Movie> ()

    // The movie genres
    private lateinit var genres: Genres

    init {
        app         = application
        sharedPrefs = app.getSharedPreferences (PREF_NAME, PRIVATE_MODE)

        page        = sharedPrefs.getInt(PAGE, PAGE_DEF)
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Helpful Movie Stuff
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Match the genre id to its name
     */
    fun getGenre (id:Int):String {
        var genreName = ""
        // Search thru list
        val list = genres.results?.listIterator()
        if (null != list) {
            for (genre in list) {
                if (id == genre.id) {
                    genreName = genre.name
                }
            }
        }
        return genreName
    }

    /**
     * Return a comma separated string of genres
     */
    fun getGenres (movie: Movie):String
    {
        val genres = StringBuffer ()
        for (id in movie.genre_ids) {
            val genreId = getGenre (id)
            if (!genreId.isEmpty()) {
                // If not first, put in a comma.
                if (!genres.isEmpty()) {
                    genres.append(" , ")
                }
                genres.append(getGenre(id))
            }
        }
        return genres.toString()
    }

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
            // Load the movie genres.
            //
            // NOTE: This blocks and returns only when done.
            // Refresh from repo on IO thread.
            // /////////////////////////////////////////////////////////////////////////////////////
            this@MovieViewModel.genres = MovieRepository.loadGenres(this@MovieViewModel.getApplication())

            // /////////////////////////////////////////////////////////////////////////////////////
            // Load the movies.
            //
            // NOTE: This blocks and returns only when done.
            // Refresh from repo on IO thread.
            // /////////////////////////////////////////////////////////////////////////////////////
            this@MovieViewModel.movies = MovieRepository.loadMovies(app.applicationContext, page)

            // /////////////////////////////////////////////////////////////////////////////////////
            // Now that we have the data, notify the observers. Notify of update by
            // setting selected movie to the first movie.
            // /////////////////////////////////////////////////////////////////////////////////////

            // TODO: Try to keep selected movie the same between reloads, if possible.
            movie.value = this@MovieViewModel.movies.results?.get(0)
        }
    }

    /**
     * Delete all cached movies.
     */
    fun deleteAll () {

        // Launch on main thread.
        coroutineScope.launch (Dispatchers.Main) {
            // Delete all cached movies.
            MovieRepository.deleteAll(app.applicationContext)

            // Load new movies.
            loadMovies ()
        }
    }
}

