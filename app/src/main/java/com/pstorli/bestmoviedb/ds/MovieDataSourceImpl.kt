package com.pstorli.bestmoviedb.ds

import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.model.Genres

/**
 * This class implements DataSource
 *
 * If methods are unimplemented by the subclasss, then fill in with StaticDataSource
 */
open class MovieDataSourceImpl  : MovieDataSource {

    override suspend fun loadMovies(): Movies {
        return StaticDataSource.loadMovies()
    }

    override suspend fun loadGenres(): Genres {
        return StaticDataSource.loadGenres()
    }
}