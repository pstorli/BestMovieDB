package com.pstorli.bestmoviedb.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.pstorli.bestmoviedb.Consts.MOVIES_TABLE_NAME
import com.pstorli.bestmoviedb.Consts.PAGE

/**
 * Note: page is a key used to match up with Movie.pageFk
 * to fetch all movies with the same page number.
 */
@Entity (
    tableName   = MOVIES_TABLE_NAME,
    indices     = [Index(value = [PAGE], unique = true)])
open class Movies (
    // Fields handled by Room
    @PrimaryKey
    var page:           Int             = 0,
    var total_pages:    Int             = 0,
    var total_results:  Int             = 0,

    /**
     * The list of movies. Comes free this way with json.
     * When fetching fropm, db, copy to this field from DBMovies.results
     */
    @Ignore
    var results: List<Movie>?           = null
)