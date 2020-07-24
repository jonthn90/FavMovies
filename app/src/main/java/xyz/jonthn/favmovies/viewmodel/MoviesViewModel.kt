package xyz.jonthn.favmovies.viewmodel

import androidx.lifecycle.ViewModel
import xyz.jonthn.favmovies.model.repositories.MoviesRepository

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

}