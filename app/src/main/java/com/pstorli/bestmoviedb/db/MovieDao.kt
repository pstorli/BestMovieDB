package com.pstorli.bestmoviedb.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pstorli.bestmoviedb.model.Genre
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.model.Movies

/**
 * This is our dao (data access object) for the movies database.
 *
 * We are using co-routines, so add suspend to each dao method.
 */
@Dao
abstract class MovieDao
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Query
    // /////////////////////////////////////////////////////////////////////////////////////////////

    @Query("SELECT * FROM movie WHERE pageFk LIKE :page")
    @Transaction
    abstract fun getMovieList (page:Int): List<Movie>?

    @Query("SELECT * FROM movies WHERE page LIKE :page")
    @Transaction
    abstract fun getMoviesList (page:Int): List<Movies>?

    /**
     * Add/Insert into db
     */
    fun getMovies (page:Int): Movies?
    {
        var movies : Movies? = null
        val moviesList : List<Movies>? = getMoviesList (page)
        if (null != moviesList && moviesList.size>0) {
            movies         = moviesList[0]
            movies.results = getMovieList (page)
        }

        return movies
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Add/Insert
    // /////////////////////////////////////////////////////////////////////////////////////////////

    @Insert
    abstract fun insert (movies: Movies?)

    @Insert
    abstract fun insert (movies: List<Movie>?)

    /**
     * Add/Insert into db
     */
    fun add (movies: Movies)
    {
        // Insert "Movies" in pieces. This, sadly, does not do anything with the results.
        insert (movies)

        // Insert the list of results. Room? Room? R U the Walrus?
        insert (movies.results)
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Delete
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Delete the movie table.
     */
    @Query ("DELETE FROM movie")
    fun deleteMovie() {}

    /**
     * Delete the movies table.
     */
    @Query ("DELETE FROM movies")
    fun deleteMovies() {}
}