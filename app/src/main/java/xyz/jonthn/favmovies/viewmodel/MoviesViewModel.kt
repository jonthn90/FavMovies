package xyz.jonthn.favmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagedList
import androidx.paging.toLiveData
import kotlinx.coroutines.launch
import timber.log.Timber
import xyz.jonthn.favmovies.model.data.Movie
import xyz.jonthn.favmovies.model.paging.MoviesDataSourceFactory
import xyz.jonthn.favmovies.model.repositories.MoviesRepository

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {



    fun getMoviesWS() {
        viewModelScope.launch {
            Timber.d("+++ Movies 1 = ${moviesRepository.getMoviesWS(1)}")
            Timber.d("+++ Movies 2 = ${moviesRepository.getMoviesWS(2)}")
        }
    }
}