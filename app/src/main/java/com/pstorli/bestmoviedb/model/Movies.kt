package com.pstorli.bestmoviedb.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class Movies (
    // Fields handled by Room
    @PrimaryKey
    var page:           Int             = 0,
    var total_pages:    Int             = 0,
    var total_results:  Int             = 0,

    // Field handled by me. :( Room why don't you like relationships? R U A Walrus?
    @Ignore
    var results:        List<Movie>?    = null
)