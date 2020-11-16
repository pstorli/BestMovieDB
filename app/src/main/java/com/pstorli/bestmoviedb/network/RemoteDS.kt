package com.pstorli.bestmoviedb.network

import android.content.Context
import com.pstorli.bestmoviedb.BuildConfig.TMDB_API_KEY
import com.pstorli.bestmoviedb.logError
import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.repo.MovieDataSourceImpl
import com.pstorli.bestmoviedb.repo.StaticDS
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.Consts.TMDB_BASE_URL
import com.pstorli.bestmoviedb.Consts.debug
import com.pstorli.bestmoviedb.Consts.logError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * This data source class servers up data from remote urls for use by those in need of it.
 */
class RemoteDS : MovieDataSourceImpl()
{
    var movieService: MovieService

    val httpClient = OkHttpClient.Builder().build()

    // Initialize things.
    init {
        // /////////////////////////////////////////////////////////////////////////////////////////
        // Create retrofit object to make call using OKHttp.
        // /////////////////////////////////////////////////////////////////////////////////////////
        val retrofit = Retrofit.Builder()
            .baseUrl(TMDB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()

        // Create the movie service
        movieService = retrofit.create(MovieService::class.java)
    }

    /**
     * Load Popular Movies - Do This on the IO context, not the main context.
     *
     * Note: Makes blocking call to TMDB to get list of popular movies.
     */
    override suspend fun loadMovies(context: Context, page:Int) : Movies = withContext (Dispatchers.IO) {
        // List of movies we have retrieved.
        var movies: Movies?  = null

        try {
            // /////////////////////////////////////////////////////////////////////////////////////
            // Create the api call.
            // /////////////////////////////////////////////////////////////////////////////////////
            val callSync: Call<Movies> = movieService.getPopularMovies (TMDB_API_KEY)

            // /////////////////////////////////////////////////////////////////////////////////////
            // !!! Block here and wait as the .execute call is being done synchrously.           !!!
            // NOTE: Have not found way yet to get rid of warning: Inappropriate blocking method call
            // /////////////////////////////////////////////////////////////////////////////////////
            val response: Response<Movies> = callSync.execute()

            if (response.isSuccessful) {
                // Assign movies from response payload.
                movies = response.body()
            }
            else {
                movies = null

                // Response code?
                debug ("Movies Response Code: " + response.code())

                // Log an error.
                logError ("Movies Error: " + response.errorBody().toString())

                // Response header?
                debug ("Movies Response Header: " + response.headers())

                // Response raw?
                debug ("Movies Raw Response: " + response.raw())
            }
        }
        catch (ex: Exception) {
            logError(ex.toString())
        }

        // Then return them back to block buster. :)
        return@withContext movies?: StaticDS.loadMovies(context, page)
    }

    /**
     * Load genres - Do This on the IO context, not the main context.
     *
     * Note: Makes blocking call to TMDB to get list of movie genres.
     */
    override suspend fun loadGenres(context: Context) : Genres = withContext (Dispatchers.IO) {
        // List of movies we have retrieved.
        var genres: Genres? = null

        try {
            // /////////////////////////////////////////////////////////////////////////////////////
            // Create the api call.
            // /////////////////////////////////////////////////////////////////////////////////////
            val callSync = movieService.getGenres (TMDB_API_KEY)

            // /////////////////////////////////////////////////////////////////////////////////////
            // !!! Block here and wait as the .execute call is being done synchrously.           !!!
            // NOTE: Have not found way yet to get rid of warning: Inappropriate blocking method call
            // /////////////////////////////////////////////////////////////////////////////////////
            val response : Response<GenresNet> = callSync.execute()

            // Assign genres from response payload.
            if (response.isSuccessful) {
                debug ("Genres Response Body: " + response.body())

                // get the body
                val genresNet = response.body()

                // show the body
                debug (genresNet.toString())

                if (genresNet is GenresNet) {
                    genres = genresNet.toGenres()
                }
            }
            else {
                genres = null

                // Response code?
                debug ("Genres Response Code: " + response.code())

                // Log an error.
                logError ("Genres Error: " + response.errorBody().toString())

                // Response header?
                debug ("Genres Response Header: " + response.headers())

                // Response raw?
                debug ("Genres Raw Response: " + response.raw())
            }
        }
        catch (ex: Exception) {
            ex.toString().logError()
        }

        return@withContext genres?: StaticDS.loadGenres(context)
    }
}