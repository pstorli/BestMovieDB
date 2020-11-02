package com.pstorli.bestmoviedb.db

import androidx.room.*
import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.model.Movies

/**
 * We are using co-routines, so add suspend to each dao method.
 */
@Dao
interface MovieDao {

    // Insert
    @Insert
    suspend fun insert(movies: Movies)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(news: Movies)

    // Update
    @Update
    suspend fun update(movies: Movies)

    // Delete
    @Delete
    suspend fun delete(movies: Movies)

    // Query
    @Query("DELETE FROM movies")
    @Transaction
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM Movie WHERE id LIKE :id")
    @Transaction
    suspend fun getMovieById(id:Int): Movie

    @Query("SELECT * FROM Movies WHERE page LIKE :page")
    @Transaction
    suspend fun getMoviesByPage(page:Int): List<Movies>

    @Query("SELECT * FROM Movie")
    @Transaction
    suspend fun getMovies(): List<Movie>
}