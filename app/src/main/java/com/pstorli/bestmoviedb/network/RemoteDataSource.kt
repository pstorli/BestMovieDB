package com.pstorli.bestmoviedb.network

import com.pstorli.bestmoviedb.BuildConfig
import com.pstorli.bestmoviedb.logError
import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.ds.MovieDataSourceImpl
import com.pstorli.bestmoviedb.ds.StaticDataSource
import com.pstorli.bestmoviedb.model.Genres
import com.pstorli.bestmoviedb.util.Consts.TMDB_BASE_URL
import com.pstorli.bestmoviedb.util.Consts.TMDB_API_URL
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * This data service class servers up data from remote urls for use by those in need of it.
 */
class RemoteDataSource : MovieDataSourceImpl()
{
    private var movieService: MovieService

    private val httpClient      = OkHttpClient.Builder().build()

    // TODO: test only
    private val testRequest     = Request.Builder().url(TMDB_API_URL).build()
    // TODO: test only

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

    private fun OkHttpTest (): String
    {
        val response: okhttp3.Response = httpClient.newCall(testRequest).execute()
        val responseString = response.body?.string()?:""

        return responseString
    }

    /**
     * Load Popular Movies - Do This on the IO context, not the main context.
     *
     * Note: Makes blocking call to TMDB to get list of popular movies.
     */
    override suspend fun loadMovies() : Movies = withContext (Dispatchers.IO) {
        // List of movies we have retrieved.
        var movies: Movies? = null

        try {
            // TODO: test only
            // OkHttpTest ()
            // TODO: test only

            // /////////////////////////////////////////////////////////////////////////////////////
            // Create the api call.
            // /////////////////////////////////////////////////////////////////////////////////////
            val callSync: Call<Movies> = movieService.getPopularMovies (BuildConfig.TMDB_API_KEY)

            // /////////////////////////////////////////////////////////////////////////////////////
            // !!! Block here and wait as the .execute call is being done synchrously.           !!!
            // NOTE: Have not found way yet to get rid of warning: Inappropriate blocking method call
            // /////////////////////////////////////////////////////////////////////////////////////
            val response: Response<Movies> = callSync.execute()

            // Assign movies from response payload.
            movies = response.body()
        }
        catch (ex: Exception) {
            ex.toString().logError()
        }

        // Then return them back to block buster. :)
        return@withContext movies?:StaticDataSource.loadMovies()
    }

    /**
     * Load genres - Do This on the IO context, not the main context.
     *
     * Note: Makes blocking call to TMDB to get list of movie genres.
     */
    override suspend fun loadGenres() : Genres = withContext (Dispatchers.IO) {
        // List of movies we have retrieved.
        var genres: Genres? = null

        try {
            // /////////////////////////////////////////////////////////////////////////////////////
            // Create the api call.
            // /////////////////////////////////////////////////////////////////////////////////////
            val callSync: Call<Genres> = movieService.getMovieGenres (BuildConfig.TMDB_API_KEY)

            // /////////////////////////////////////////////////////////////////////////////////////
            // !!! Block here and wait as the .execute call is being done synchrously.           !!!
            // NOTE: Have not found way yet to get rid of warning: Inappropriate blocking method call
            // /////////////////////////////////////////////////////////////////////////////////////
            val response: Response<Genres> = callSync.execute()

            // Assign movies from response payload.
            genres = response.body()
        }
        catch (ex: Exception) {
            ex.toString().logError()
        }

        return@withContext genres?:StaticDataSource.loadGenres()
    }
}