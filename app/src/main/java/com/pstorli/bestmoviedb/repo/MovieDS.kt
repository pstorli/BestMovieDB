package com.pstorli.bestmoviedb.repo

import android.content.Context
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.model.Movies

/**
 * This interface defines the methods needed too qualify as a DataSource
 */
interface MovieDS {

    /**
     * Fetch some movies.
     */
    suspend fun loadMovies (context: Context, page:Int): Movies

    /**
     * Fetch movie genres.
     */
    suspend fun loadGenres (context: Context): Genres

    /**
     * Delete all movies.
     */
    suspend fun deleteAll (context: Context) : Boolean
}