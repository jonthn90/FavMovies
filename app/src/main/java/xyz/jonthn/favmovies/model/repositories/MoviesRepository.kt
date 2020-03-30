package xyz.jonthn.favmovies.model.repositories

import xyz.jonthn.favmovies.model.data.Movie

interface MoviesRepository {
    suspend fun getMoviesWS(page: Int): List<Movie>
}