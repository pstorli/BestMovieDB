package com.pstorli.bestmoviedb.repo

import com.pstorli.bestmoviedb.model.Movie

/**
 * This interface defines the methods needed too qualify as a DataSource
 */
interface DataSource {

    /**
     * Return a list of movies.
     */
    suspend fun loadMovies () : List<Movie>
}