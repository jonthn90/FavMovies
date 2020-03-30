package xyz.jonthn.favmovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import xyz.jonthn.favmovies.model.repositories.MoviesRepository

class ViewModelFactory(private val moviesRepository: MoviesRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(MoviesViewModel::class.java) -> {
                    MoviesViewModel(moviesRepository)
                }

                isAssignableFrom(FavsViewModel::class.java) -> {
                    FavsViewModel(moviesRepository)
                }

                else -> throw IllegalArgumentException("Unknown ViewModel class you must add it") as Throwable
            }
        } as T
    }
}