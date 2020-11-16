package com.pstorli.bestmoviedb.network

import com.pstorli.bestmoviedb.model.Genre
import com.pstorli.bestmoviedb.model.Genres

data class GenresNet(
    val genres: List<GenreNet>
)

{
    /**
     * Convert from Movies to MoviesDB
     */
    fun toGenres () : Genres
    {
        val list = arrayListOf<Genre>()
        for (genreNet in genres) {
            list.add(genreNet.toGenre())
        }
        return Genres (list)
    }
}