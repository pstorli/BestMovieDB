package com.pstorli.bestmoviedb.service

import com.pstorli.bestmoviedb.util.Consts

object ApiFactory{

    val tmdbApi : TmdbApi =
        RetrofitFactory.retrofit(Consts.TMDB_BASE_URL).create(TmdbApi::class.java)
}