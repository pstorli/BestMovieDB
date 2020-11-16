package com.pstorli.bestmoviedb.network

import com.pstorli.bestmoviedb.BuildConfig.TMDB_API_KEY
import com.pstorli.bestmoviedb.Consts.PAGE
import com.pstorli.bestmoviedb.Consts.PAGE_DEF
import com.pstorli.bestmoviedb.Consts.TMDB_NAME_API_KEY
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
     *      https://api.themoviedb.org/3/movie/popular
     */
    @GET("movie/popular") // Page defaults to PAGE_DEF, page range is 1-1000
    fun getPopularMovies(@Query(TMDB_NAME_API_KEY) api_key: String, @Query(PAGE) page: Int=PAGE_DEF): Call<Movies>

    /**
     * Get movie genres - See document JSON_MovieGenres.json for sample data
     *      https://api.themoviedb.org/3/genre/movie/list
     */
    @GET("genre/movie/list") // Language defaults to en-US
    fun getGenres(@Query(TMDB_NAME_API_KEY) api_key: String): Call<GenresNet>

}
