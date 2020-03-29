package xyz.jonthn.favmovies.model.repositories

import xyz.jonthn.favmovies.model.data.MoviesResponse

interface MoviesRepository {

    suspend fun getMoviesWS(): MoviesResponse?
}