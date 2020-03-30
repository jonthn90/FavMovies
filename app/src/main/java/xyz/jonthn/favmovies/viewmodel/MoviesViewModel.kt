package xyz.jonthn.favmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.launch
import timber.log.Timber
import xyz.jonthn.favmovies.model.data.Movie
import xyz.jonthn.favmovies.model.paging.MoviesDataSource
import xyz.jonthn.favmovies.model.repositories.MoviesRepository

class MoviesViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    var moviesLiveData: LiveData<PagedList<Movie>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setEnablePlaceholders(false)
            .build()
        moviesLiveData = initializedPagedListBuilder(config).build()
    }

    fun getMovies(): LiveData<PagedList<Movie>> = moviesLiveData

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, Movie> {

        val dataSourceFactory = object : DataSource.Factory<Int, Movie>() {
            override fun create(): DataSource<Int, Movie> {
                return MoviesDataSource(viewModelScope)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    fun getMoviesWS() {
        viewModelScope.launch {
            Timber.d("+++ Movies 1 = ${moviesRepository.getMoviesWS(1)}")
            Timber.d("+++ Movies 2 = ${moviesRepository.getMoviesWS(2)}")
        }
    }
}