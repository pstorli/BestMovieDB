package com.pstorli.bestmoviedb.ds

import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.model.Genres

/**
 * This interface defines the methods needed too qualify as a DataSource
 */
interface MovieDataSource {

    /**
     * Fetch some movies.
     */
    suspend fun loadMovies (): Movies

    /**
     * Fetch movie genres.
     */
    suspend fun loadGenres (): Genres
}