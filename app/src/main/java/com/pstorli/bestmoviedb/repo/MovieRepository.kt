package com.pstorli.bestmoviedb.repo

import com.pstorli.bestmoviedb.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRepository ()  : DataSourceImpl()
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // The currently selected DataSource
    // /////////////////////////////////////////////////////////////////////////////////////////////
    var selectedDS : DataSource? = StaticDataSource

    /**
     * Load some movies on the IO thread.
     */
    override suspend fun loadMovies(): List<Movie> {
        return withContext(Dispatchers.IO) {
            selectedDS?.loadMovies() ?: StaticDataSource.loadMovies()
        }
    }
}