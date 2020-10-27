package com.pstorli.bestmoviedb.repo

import com.pstorli.bestmoviedb.ds.MovieDataSource
import com.pstorli.bestmoviedb.ds.MovieDataSourceImpl
import com.pstorli.bestmoviedb.ds.StaticDataSource
import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.network.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository ()  : MovieDataSourceImpl()
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // The currently selected DataSource, aka the Default Data Source
    // /////////////////////////////////////////////////////////////////////////////////////////////
    var selectedDS : MovieDataSource? = RemoteDataSource ()

    /**
     * Load some movies on the IO thread.
     */
    override suspend fun loadMovies(): Movies {
        return withContext(Dispatchers.IO) {
            selectedDS?.loadMovies() ?: StaticDataSource.loadMovies()
        }
    }
}