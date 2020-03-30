package xyz.jonthn.favmovies.model.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import xyz.jonthn.favmovies.model.data.Movie

interface MoviesRepository {
    suspend fun getMoviesWS(page: Int): List<Movie>

    suspend fun getFavMovies(): List<Movie>

    suspend fun insertFavMovie(movie: Movie)

    suspend fun deleteFavMovie(id: Int)

    fun getFavMoviesLive(): LiveData<List<Movie>>
}