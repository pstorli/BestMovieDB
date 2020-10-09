package com.pstorli.bestmoviedb.repo

import com.pstorli.bestmoviedb.model.Movie

/**
 * This class implements DataSource
 *
 * If methods are unimplemented by the subclasss, then fill in with StaticDataSource
 */
open class DataSourceImpl  :  DataSource {
    override suspend fun loadMovies() : List<Movie> {
        return StaticDataSource.loadMovies()
    }
}