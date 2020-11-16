package com.pstorli.bestmoviedb.model

import androidx.room.*
import com.pstorli.bestmoviedb.repo.StaticDS.MOVIE_2001
import com.pstorli.bestmoviedb.Consts.MOVIE_TABLE_NAME
import com.pstorli.bestmoviedb.Consts.PAGE
import com.pstorli.bestmoviedb.Consts.PAGE_FK
import com.pstorli.bestmoviedb.Consts.PAGE_FK_DEF

/**
 * Note: pageFk is a foreign key, the parent is Movies
 */
@Entity(
    tableName = MOVIE_TABLE_NAME,
    foreignKeys = [ForeignKey (
        entity = Movies::class,
        parentColumns = [PAGE],
        childColumns = [PAGE_FK],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(PAGE_FK)]
)
data class Movie (
    // Fields handled by Room
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
    val vote_count:         Int,

    // NOTE: This field IS NOT part of the json record.
    // It was added solely to support ROOM sql lite db indexing.
    // PAGE Links us back to the parent class so that we can be queried.
    var pageFk:             Int = PAGE_FK_DEF // Page foreign key from Movies
)

{
    constructor () : this (MOVIE_2001)
    constructor (movie: Movie) : this (movie.adult, movie.backdrop_path, movie.genre_ids, movie.id, movie.original_language, movie.original_title, movie.overview, movie.popularity, movie.poster_path, movie.release_date, movie.title, movie.video, movie.vote_average, movie.vote_count)
}