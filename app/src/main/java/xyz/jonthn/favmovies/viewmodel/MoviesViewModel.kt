package xyz.jonthn.favmovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import timber.log.Timber
import xyz.jonthn.favmovies.model.repositories.MoviesRepository

class MoviesViewModel (private val moviesRepository: MoviesRepository) : ViewModel() {

    fun getMoviesWS(){

        viewModelScope.launch{

            val moviesWS = moviesRepository.getMoviesWS()

            Timber.d("+++ Movies = $moviesWS")
        }
    }
}