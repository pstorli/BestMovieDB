package com.pstorli.bestmoviedb.model

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.pstorli.bestmoviedb.ds.StaticDataSource.MOVIE_2001

@Entity
data class Movie (
    val adult:              Boolean,
    val backdrop_path:      String,
    val genre_ids:          List<Int>,
    @PrimaryKey
    val id:                 Int,
    val original_language:  String,
    val original_title:     String,
    val overview:           String,
    val popularity:         Double,
    val poster_path:        String,
    val release_date:       String,
    val title:              String,
    val video:              Boolean,
    val vote_average:       Double,
    val vote_count:         Int
)

{
    constructor () : this (MOVIE_2001)
    constructor (movie: Movie) : this (movie.adult, movie.backdrop_path, movie.genre_ids, movie.id, movie.original_language, movie.original_title, movie.overview, movie.popularity, movie.poster_path, movie.release_date, movie.title, movie.video, movie.vote_average, movie.vote_count)
}