package xyz.jonthn.favmovies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(MoviesViewModel::class.java) -> {
                    MoviesViewModel()
                }

                isAssignableFrom(FavsViewModel::class.java) -> {
                    FavsViewModel()
                }

                else -> throw IllegalArgumentException("Unknown ViewModel class you must add it") as Throwable
            }
        } as T
    }
}