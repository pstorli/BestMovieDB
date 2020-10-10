package com.pstorli.bestmoviedb.service

import com.pstorli.bestmoviedb.model.MovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface TmdbApi{

    @GET("movie/popular")
    fun getPopularMovie(): Deferred<Response<MovieResponse>>
}