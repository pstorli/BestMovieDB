package com.pstorli.bestmoviedb.network

import com.pstorli.bestmoviedb.model.Movies
import com.pstorli.bestmoviedb.model.Genres
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * The MovieService Interface
 */
interface MovieService {

    /**
     * Get popular movies - See document JSON_PopularMovies.json for sample data
     *      https://api.themoviedb.org/3/movie/popular?api_key=BuildConfig.TMDB_API_KEY&language=en-US&page=1
     */
    @GET("movie/popular") // Page defaults to 1, page range is 1-1000
    fun getPopularMovies(@Query("api_key") api_key: String, @Query("page") page: Int=1): Call<Movies>

    /**
     * Get movie genres - See document JSON_MovieGenres.json for sample data
     *      https://api.themoviedb.org/3/genre/tv/list?api_key=BuildConfig.TMDB_API_KEY&language=en-US
     */
    @GET("genre/tv/list") // Page defaults to 1, page range is 1-1000
    fun getMovieGenres(@Query("api_key") api_key: String): Call<Genres>

}
