package com.pstorli.bestmoviedb.service

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pstorli.bestmoviedb.BuildConfig
import com.pstorli.bestmoviedb.util.Consts
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory{

    // The TheMovieDB api and auth key.
    val tmdbApi : TmdbApi = retrofit(Consts.TMDB_BASE_URL).create(TmdbApi::class.java)

    /**
     * Create a Network Interceptor to add api_key query in front of all the requests.
     */
    private val authInterceptor = Interceptor {chain->
        // Get URL?
        val newUrl = chain.request().url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
                .build()

        // Create Request
        val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

        // Go
        chain.proceed(newRequest)
    }

    /**
     * HttpLoggingInterceptor
     */
    private val loggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Create a networking client using OkHttp and
     * add our authInterceptor.
     * Not logging the authkey if not debug
     */
    private val client =
        if (BuildConfig.DEBUG){
             OkHttpClient().newBuilder()
                    .addInterceptor(authInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()
        } else {
            OkHttpClient().newBuilder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(authInterceptor)
                    .build()
        }

    /**
     * Create our HTTP Request builder and handler using Retrofit.
     * Add our previously created networking client, base URL,
     * and add a converter and an adapter factory.
     */
    fun retrofit (baseUrl : String) : Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())  // Assists in JSON parsing and converts Response JSON into Kotlin data class
        .addCallAdapterFactory(CoroutineCallAdapterFactory()) // A Retrofit2 CallAdapter.Factory for Kotlin coroutine's Deferred
        .build()
}