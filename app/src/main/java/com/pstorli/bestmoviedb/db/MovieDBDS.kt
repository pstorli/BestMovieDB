package com.pstorli.bestmoviedb.db

import android.content.Context
import com.pstorli.bestmoviedb.logError
import com.pstorli.bestmoviedb.logWarning
import com.pstorli.bestmoviedb.repo.MovieDataSourceImpl
import com.pstorli.bestmoviedb.repo.StaticDS
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.repo.MovieRepository
import com.pstorli.bestmoviedb.Consts.logError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This data source class servers up data from remote urls for use by those in need of it.
 */
class MovieDBDS () : MovieDataSourceImpl() {
    // /////////////////////////////////////////////////////////////////////////////////////////////
    // There be vars here, Ahoy!
    // /////////////////////////////////////////////////////////////////////////////////////////////
    var movieDB:  MovieDB? = null
    var movieDao: MovieDao?      = null
    var genreDao: GenreDao?      = null

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Database
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Delete the db.
     */
    override suspend fun deleteAll (context: Context) : Boolean = withContext(Dispatchers.IO) {
        var result = true

        try {
            // Dayo, me say dayo
            setup(context)

            // Got DB now? Then delete all
            movieDB?.clearAllTables()

        } catch (ex: Exception) {
            result = false
            logError(ex)
        }

        return@withContext result
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Dayo, me say dayo
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Dayo, me say dayo
     *
     * Set up the movieDB, the movieDao and the genreDao
     *
     */
    fun setup (context: Context) {
        // Got MovieDB?
        if (null == movieDB) {

            // Get the db
            movieDB = MovieDB.getInstance(context)
        }

        // Got MovieDB Now?
        if (null != movieDB) {
            // Got Movie Dao?
            if (null == movieDao) {
                // Get the dao
                movieDao = movieDB?.movieDao()
            }

            // Got Genre Dao?
            if (null == genreDao) {
                // Get the dao
                genreDao = movieDB?.genreDao()
            }
        }
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Genres
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * When we fetch from the remote db, the pageFk is not set.
     */
    fun normalizeIdFk (genres: Genres? = null) : Genres?
    {
        // Fix idFk (Make it match with id.)
        if (null != genres) {
            var pos  = 0
            val size = genres.results?.size?:0

            while (pos<size) {
                genres.results?.get(pos)?.idFk = genres.id // Set to parent id
                pos++
            }
        }

        return genres
    }

    /**
     * Load genres - Do This on the IO context, not the main context.
     *
     * Note: Makes blocking call to TMDB to get list of movie genres.
     */
    override suspend fun loadGenres(context: Context): Genres = withContext(Dispatchers.IO)
    {

        // List of movies we have retrieved.
        var genres: Genres? = null

        try {
            // Dayo, me say dayo
            setup(context)

            // Got Dao now?
            if (null != genreDao) {

                // Get the genres.
                genres = genreDao?.getGenres()

                // Got genres?
                if (null == genres || 0 == genres.results?.size) {
                    // Load from network. (Blocking call to network data source.)
                    genres = MovieRepository.remoteDS?.loadGenres(context)

                    // normalizePageFk
                    genres = normalizeIdFk (genres)

                    // Save to db
                    if (null != genres && null != genres.results) {
                        // Add in new genres
                        try {
                            // Call add instead of insert so results are saved correctly, room does not like kids.
                            genreDao?.add(genres)
                        }
                        catch (ex1: Exception) {
                            try {
                                // Delete db and try again.
                                deleteAll(context)

                                // One more time, with gusto!
                                genreDao?.insert(genres)
                            }
                            catch (ex2: Exception) {
                                logError(ex2.toString())
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            ex.toString().logError()
        }

        return@withContext genres ?: StaticDS.loadGenres(context)
    }

    // /////////////////////////////////////////////////////////////////////////////////////////////
    // Movies
    // /////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * When we fetch from the remote db, the pageFk is not set.
     */
    fun normalizePageFk(movies: Movies? = null): Movies? {
        // Fix page FK (Make it match with page.)
        if (null != movies) {
            var pos = 0
            val size = movies.results?.size ?: 0

            while (pos < size) {
                movies.results?.get(pos)?.pageFk = movies.page // Set to parent page
                pos++
            }
        }

        return movies
    }

    /**
     * Load Popular Movies - Do This on the IO context, not the main context.
     *
     * Note: Makes blocking call to TMDB to get list of popular movies.
     *
     * @param context - needed to get db instance.
     * @param page
     */
    override suspend fun loadMovies(context: Context, page: Int): Movies = withContext(Dispatchers.IO)
    {
        // List of movies we have retrieved.
        var movies: Movies? = null

        try {
            // Dayo, me say dayo
            setup(context)

            // Got Dao now?
            if (null != movieDao) {
                // Get movies for current page.
                movies = movieDao?.getMovies (page)

                // Got movies?
                if (null == movies || 0 >= movies.results?.size!!) {
                    // Get them from the Remote Data source then.
                    movies = MovieRepository.remoteDS?.loadMovies(context, page)

                    // normalizePageFk
                    movies = normalizePageFk(movies)

                    // Save to db
                    if (null != movies && null != movies.results) {
                        // Add in new movies
                        try {
                            // Call add instead of insert so results are saved correctly, room does not like kids.
                            movieDao?.add(movies)
                        }
                        catch (ex1: Exception) {
                            try {
                                // Delete db and try again.
                                deleteAll(context)

                                // One more time, with gusto!
                                movieDao?.add (movies)
                            }
                            catch (ex2: Exception) {
                                logError(ex2.toString())
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            ex.toString().logError()
        }

        // Then return them back to block buster. :)
        return@withContext movies ?: StaticDS.loadMovies(context, page)
    }
}