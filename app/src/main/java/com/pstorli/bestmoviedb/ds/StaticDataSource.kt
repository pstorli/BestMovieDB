package com.pstorli.bestmoviedb.ds

import com.pstorli.bestmoviedb.model.Genre
import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.util.Consts.SOME_GENRES

/**
 * This data service class servers up static / test data for use by those in need of it.
 */
object StaticDataSource : MovieDataSourceImpl()
{
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Some of Pete's Favorite movies used for testing.
    // /////////////////////////////////////////////////////////////////////////////////////////////
    var favorites : List<Movie> = listOf (
        Movie (false, "", listOf (10765),   1, "",   "2001 A Space odessy",     "Humanity finds a mysterious object buried beneath the lunar surface and sets off to find its origins with the help of HAL 9000, the worlds most advanced super computer ...",                          1.0, "a2001",       "1968", "2001 A Space Odessy",      true,   1.0, 1),
        Movie (false, "", listOf (10759),   2, "",   "The Hunt for Red October","A new, technologically-superior Soviet sub, the Red October, is heading for the U.S. coast under the command of Captain Marko Ramius. The American government thinks Ramius is planning to attack...", 1.0, "red_oct",     "1990", "The Hunt for Red October", true,   1.0, 1),
        Movie (false, "", listOf (35),      3, "",   "Young Frankenstein",      "A young neurosurgeon inherits the castle of his grandfather, the famous Dr. Victor von Frankenstein. In the castle he finds a funny hunchback, a pretty lab assistant and the elder ...",              1.0, "young_frank", "1974", "Young Frankenstein",       true,   1.0, 1),
        Movie (false, "", listOf (99),      4, "",   "The Fog of War",          "Depicts life and work of Robert McNamara, from working as a War Using archival footage, United States Cabinet conversation recordings, THE FOG OF WAR depicts his life, from working as a War ...",    1.0, "fog_of_war",  "2003", "The Fog of War",       true,       1.0, 1)
    )

    val movies = Movies (1, favorites, 1, 1)

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Implement interface MovieDataSource
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Return a cached set of movies we like.
     */
    override suspend fun loadMovies(): Movies {
        return movies
    }

    /**
     * Return some of the movie genres that we are using.
     */
    override suspend fun loadGenres(): Genres {
        return Genres (SOME_GENRES)
    }
}