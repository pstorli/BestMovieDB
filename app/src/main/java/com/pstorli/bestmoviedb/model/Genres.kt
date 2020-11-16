package com.pstorli.bestmoviedb.model

import androidx.room.*
import com.pstorli.bestmoviedb.Consts
import com.pstorli.bestmoviedb.Consts.GENRES_TABLE_NAME
import com.pstorli.bestmoviedb.Consts.ID
import com.pstorli.bestmoviedb.Consts.ID_DEF

@Entity (
    tableName   = GENRES_TABLE_NAME,
    indices     = [Index(value = [ID], unique = true)]
)
data class Genres (
    // Fields handled by Room
    @PrimaryKey
    var id: Int = ID_DEF,

    /**
     * The list of movies
     */
    @Ignore
    var results: List<Genre>? = null
)
{
    constructor (list: List<Genre>?) : this (ID_DEF, list)
}