package com.pstorli.bestmoviedb.network

import com.pstorli.bestmoviedb.model.Genre

data class GenreNet(
    val id: Int,
    val name: String
)

{
    /**
     * Convert from GenreNet to Genre
     */
    fun toGenre () : Genre
    {
        return Genre (id, name)
    }
}