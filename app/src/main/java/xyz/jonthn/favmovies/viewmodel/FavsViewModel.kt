package xyz.jonthn.favmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.jonthn.favmovies.model.data.Movie
import xyz.jonthn.favmovies.model.repositories.MoviesRepository

class FavsViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {


}