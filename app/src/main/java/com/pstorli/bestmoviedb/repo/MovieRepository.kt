package com.pstorli.bestmoviedb.repo

import android.content.Context
import com.pstorli.bestmoviedb.db.MovieDBDS
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.network.RemoteDS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object MovieRepository : MovieDataSourceImpl()
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // The currently selected DataSource, aka the Default Data Source
    // /////////////////////////////////////////////////////////////////////////////////////////////
    var remoteDS    : RemoteDS?     = RemoteDS ()
    var dbDS        : MovieDBDS?    = MovieDBDS ()

    // Ther default data source.
    var selectedDS  : MovieDS?      = dbDS

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Fetch Genres
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Fetch movie genres.
     */
    override suspend fun loadGenres (context: Context): Genres {
        return loadGenres (context, selectedDS)
    }

    /**
     * Fetch movie genres.
     */
    suspend fun loadGenres (context: Context, ds: MovieDS?): Genres {
        return withContext(Dispatchers.IO) {
            ds?.loadGenres(context) ?: StaticDS.loadGenres(context)
        }
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Fetch Movies
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Load some movies on the IO thread.
     */
    override suspend fun loadMovies (context: Context, page:Int): Movies {
        return loadMovies (context, page, selectedDS)
    }

    /**
     * Load some movies on the IO thread.
     */
    suspend fun loadMovies (context: Context, page:Int, ds: MovieDS?): Movies {
        return withContext(Dispatchers.IO) {
            ds?.loadMovies(context, page) ?: StaticDS.loadMovies(context, page)
        }
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Delete All
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Delete all movies.
     */
    override suspend fun deleteAll (context: Context) : Boolean {
        return deleteAll (context, selectedDS)
    }

    /**
     * Delete all movies.
     */
    suspend fun deleteAll (context: Context, ds: MovieDS?) : Boolean {
        return withContext(Dispatchers.IO) {
            return@withContext ds?.deleteAll(context) ?: StaticDS.deleteAll(context)
        }
    }
}