package com.pstorli.bestmoviedb.repo

import com.pstorli.bestmoviedb.model.Movie
import com.pstorli.bestmoviedb.repo.MovieDataService.movies

class MovieRepository {

    /**
     * Return the selected movie.
     */
    fun getSelectedMovie(): Movie? {
        return if (movies.size>0) {
            // TODO Get from prefs
            return movies[0]
        } else {
            // Get data from BestMovie RDS
            null // TODO
        }
    }

    fun selectMovie() {
        // TODO save to prefs
    }

    /**
     * Return a list of movies.
     */
    fun getMovies(): List<Movie>? {

        // TODO: For now, return hard coded movies. Later get from BestMovieRDS

        // Later Get data from BestMovie RDS
        return movies
    }
}