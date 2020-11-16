package com.pstorli.bestmoviedb.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.pstorli.bestmoviedb.Consts.ID_FK_DEF
import com.pstorli.bestmoviedb.model.Genre
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.model.Movies

/**
 * This is our dao (data access object) for the movies database.
 *
 * We are using co-routines, so add suspend to each dao method.
 */
@Dao
abstract class GenreDao
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Query
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Query Genres - get all
     */
    @Query("SELECT * FROM Genres")
    @Transaction
    abstract fun getGenresList(): List<Genres>?

    /**
     * Query Genres - get by id
     */
    @Query("SELECT * FROM genres WHERE id LIKE :id")
    @Transaction
    abstract fun getGenresListById (id:Int): List<Genres>?

    /**
     * Query Genres - get by idFk
     */
    @Query("SELECT * FROM genre WHERE idFk LIKE :id")
    @Transaction
    abstract fun getGenreListById (id:Int): List<Genre>?


    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Add/Insert
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Insert genres into db
     */
    @Insert
    abstract fun insert (genres: Genres?)

    /**
     * Insert genres list into db
     */
    @Insert
    abstract fun insert (genres: List<Genre>?)

    /**
     * Add/Insert the results into db
     */
    fun add (genres: Genres?)
    {
        // Insert "Genres" in pieces. This, sadly, does not do anything with the results.
        insert (genres)

        // Insert the list of results. Room? Room? R U the Walrus?
        insert (genres?.results)
    }

    /**
     * Helper getGenres function will add in real results.
     */
    fun getGenres () : Genres?
    {
        var genres: Genres? = null
        val genresList = getGenresList()
        if (null != genresList && genresList.size > 0) {
            genres = genresList.get(0)

            // Got Genres?
            if (null == genres.results || (0 == genres.results!!.size)) {
                // Assign the results.
                genres.results = getGenreListById (genres.id)
            }
        }

        return genres
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Delete
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Delete the genre table.
     */
    @Query ("DELETE FROM genre")
    fun deleteGenre() {}

    /**
     * Delete the genres table.
     */
    @Query ("DELETE FROM genres")
    fun deleteGenres() {}
}