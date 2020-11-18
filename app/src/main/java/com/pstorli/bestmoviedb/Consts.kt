package com.pstorli.bestmoviedb

import android.util.Log

/**
 * The main reason to use constants, is
 * that we can keep everyone in agreement -
 * even it is wrong, we are all wrong and if
 * right we are allright.
 *
 * In either case both still work.
 *
 */
object Consts {

    const val GENRE_TABLE_NAME      = "genre"
    const val GENRES_TABLE_NAME     = "genres"

    const val ID                    = "id"
    const val ID_DEF                = 1
    const val ID_DEF_NAME           = "default"
    const val ID_FK                 = "idFk"
    const val ID_FK_DEF             = ID_DEF

    const val TAG                   = "BestMovieDB"

    const val MOVIE_DB_NAME         = "best_movie_db"

    const val MOVIE_TABLE_NAME      = "movie"
    const val MOVIES_TABLE_NAME     = "movies"

    const val NAME                  = "name"
    const val NO_SIZE               = 0

    const val PAGE                  = "page"
    const val PAGE_FIRST            = 1
    const val PAGE_FK               = "pageFk"
    const val PAGE_FK_DEF           = PAGE_FIRST
    const val PREF_NAME             = "bmdb_prefs"

    const val PRIVATE_MODE          = 0

    const val TMDB_NAME_API_KEY     = "api_key"
    const val TMDB_BASE_URL         = "https://api.themoviedb.org/3/"
    const val TMDB_PHOTO_URL        = "https://image.tmdb.org/t/p/w185"
    const val TMDB_API_URL          = TMDB_BASE_URL + "movie/popular?"+TMDB_NAME_API_KEY+"="+ BuildConfig.TMDB_API_KEY

    /**
     * Log an error message.
     */
    fun logError (ex: Exception)
    {
        logError (ex.toString())
    }

    /**
     * Log an error message.
     */
    fun logError (msg:String)
    {
        Log.e (TAG, msg)
    }

    /**
     * Log an error message.
     */
    fun logWarning (msg:String)
    {
        Log.w (TAG, msg)
    }

    /**
     * Log an info message.
     */
    fun logInfo (msg:String)
    {
        Log.i (TAG, msg)
    }

    /**
     * Log a debug message.
     */
    fun debug (msg:String)
    {
        Log.w (TAG, msg)
    }



}