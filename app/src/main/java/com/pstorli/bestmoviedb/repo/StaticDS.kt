package com.pstorli.bestmoviedb.repo

import android.content.Context
import com.pstorli.bestmoviedb.Consts.ID_DEF
import com.pstorli.bestmoviedb.Consts.NO_SIZE
import com.pstorli.bestmoviedb.Consts.PAGE_FIRST
import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.model.Genre

/**
 * This data source class servers up static / test data for use by those in need of it.
 */
object StaticDS : MovieDataSourceImpl()
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Some of Pete's Favorite movies used for testing.
    // /////////////////////////////////////////////////////////////////////////////////////////////
    val MOVIE_2001      = Movie (false, "", listOf (10765),   1, "",   "2001 A Space odessy",     "Humanity finds a mysterious object buried beneath the lunar surface and sets off to find its origins with the help of HAL 9000, the worlds most advanced super computer ...",                          1.0, "a2001",       "1968", "2001 A Space Odessy",      true,   1.0, 1)
    val MOVIE_HUNT      = Movie (false, "", listOf (10759),   2, "",   "The Hunt for Red October","A new, technologically-superior Soviet sub, the Red October, is heading for the U.S. coast under the command of Captain Marko Ramius. The American government thinks Ramius is planning to attack...", 1.0, "red_oct",     "1990", "The Hunt for Red October", true,   1.0, 1)
    val MOVIE_YOUNG     = Movie (false, "", listOf (35),      3, "",   "Young Frankenstein",      "A young neurosurgeon inherits the castle of his grandfather, the famous Dr. Victor von Frankenstein. In the castle he finds a funny hunchback, a pretty lab assistant and the elder ...",              1.0, "young_frank", "1974", "Young Frankenstein",       true,   1.0, 1)
    val MOVIE_FOG       = Movie (false, "", listOf (99),      4, "",   "The Fog of War",          "Depicts life and work of Robert McNamara, from working as a War Using archival footage, United States Cabinet conversation recordings, THE FOG OF WAR depicts his life, from working as a War ...",    1.0, "fog_of_war",  "2003", "The Fog of War",       true,       1.0, 1)

    //  MY, MY
    val FAVORITES       : List<Movie> = listOf (MOVIE_2001, MOVIE_HUNT, MOVIE_YOUNG, MOVIE_FOG)
    val MOVIES          = Movies (PAGE_FIRST, FAVORITES.size, FAVORITES.size, FAVORITES)
    val MOVIES_NONE     = Movies (PAGE_FIRST, NO_SIZE, NO_SIZE, null)

    val GENRES_NONE     = Genres ()
    val GENRES_SOME = listOf<Genre> (
        Genre (35,        "Comedy"),
        Genre (18,        "Drama"),
        Genre (37,        "Western"),
        Genre (10765,     "Sci-Fi & Fantasy"),
        Genre (10759,     "Action & Adventure"),
        Genre (99,        "Documentary")
    )

    val GENRES = Genres (ID_DEF, GENRES_SOME)

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Implement interface MovieDataSource
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Return a cached set of movies we like.
     */
    override suspend fun loadMovies (context: Context, page:Int): Movies {
        return if (MovieRepository.selectedDS is StaticDS) MOVIES else {MOVIES_NONE}
    }

    /**
     * Return some of the movie genres that we are using.
     */
    override suspend fun loadGenres (context: Context): Genres {
        return if (MovieRepository.selectedDS is StaticDS) GENRES else {GENRES_NONE}
    }

    /**
     * Delete all movies.
     */
    override suspend fun deleteAll (context: Context) : Boolean
    {
        return true
    }
}