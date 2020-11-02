package com.pstorli.bestmoviedb.util

import com.pstorli.bestmoviedb.BuildConfig
import com.pstorli.bestmoviedb.model.Genre

object Consts {
    const val TAG               = "BestMovieDB"
    const val TMDB_PHOTO_URL    = "https://image.tmdb.org/t/p/w185"
    const val TMDB_BASE_URL     = "https://api.themoviedb.org/3/"
    const val TMDB_API_URL      = TMDB_BASE_URL + "movie/popular?api_key="+BuildConfig.TMDB_API_KEY+"&language=en-US&page=1"

    val SOME_GENRES = listOf<Genre> (
        Genre (35,    "Comedy"),
        Genre (18,    "Drama"),
        Genre (37,    "Western"),
        Genre (10765, "Sci-Fi & Fantasy"),
        Genre (10759, "Action & Adventure"),
        Genre (99,    "Documentary")
    )
}