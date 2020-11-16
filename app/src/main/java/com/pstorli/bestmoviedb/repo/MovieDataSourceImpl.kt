package com.pstorli.bestmoviedb.repo

import android.content.Context
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.model.Movies

/**
 * This class implements DataSource
 *
 * If methods are unimplemented by the subclasss, then fill in with StaticDataSource
 */
open class MovieDataSourceImpl : MovieDS {

    override suspend fun loadMovies(context: Context, page:Int): Movies {
        return StaticDS.loadMovies(context, page)
    }

    override suspend fun loadGenres(context: Context): Genres {
        return StaticDS.loadGenres(context)
    }

    override suspend fun deleteAll (context: Context) : Boolean {
        return StaticDS.deleteAll (context)
    }
}