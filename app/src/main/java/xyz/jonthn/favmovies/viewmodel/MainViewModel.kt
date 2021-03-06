package xyz.jonthn.favmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.launch
import timber.log.Timber
import xyz.jonthn.favmovies.model.data.Movie
import xyz.jonthn.favmovies.model.paging.MoviesDataSourceFactory
import xyz.jonthn.favmovies.model.repositories.MoviesRepository

class MainViewModel(val moviesRepository: MoviesRepository) : ViewModel() {

    private val dataSourceFactory = MoviesDataSourceFactory(viewModelScope, moviesRepository)

    private val moviesLiveData: LiveData<PagedList<Movie>> =
        dataSourceFactory.toLiveData(
            pageSize = 20
        )

    val favMoviesLiveData = moviesRepository.getFavMoviesLive()

    val movieDetail = MutableLiveData<Movie>()

    fun invalidateDataSource() {
        dataSourceFactory.sourceLiveData.value?.invalidate()
    }

    fun getMovies(): LiveData<PagedList<Movie>> = moviesLiveData

    fun insertMovie(movie: Movie, invalidate: Boolean = false) {
        viewModelScope.launch {

            movie.isFav = true

            moviesRepository.insertFavMovie(movie)

            val favMovies = moviesRepository.getFavMovies()

            Timber.d("+++ FavMovies[${favMovies.size}] ${favMovies}")

            if (invalidate)
                invalidateDataSource()
        }
    }

    fun deleteFavMovie(movie: Movie, invalidate: Boolean = false) {
        viewModelScope.launch {
            movie.isFav = false
            moviesRepository.deleteFavMovie(movie.id)

            if (invalidate)
                invalidateDataSource()
        }
    }

    fun getFavMovies(): LiveData<List<Movie>> = favMoviesLiveData
}