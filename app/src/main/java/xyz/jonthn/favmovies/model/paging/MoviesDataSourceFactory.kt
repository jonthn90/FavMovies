package xyz.jonthn.favmovies.model.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber
import xyz.jonthn.favmovies.model.data.Movie
import xyz.jonthn.favmovies.model.repositories.MoviesRepository

class MoviesDataSourceFactory(
    private val scope: CoroutineScope,
    val moviesRepository: MoviesRepository
) : DataSource.Factory<Int, Movie>() {

    lateinit var latestSource: MoviesDataSource
    val sourceLiveData: MutableLiveData<MoviesDataSource> = MutableLiveData<MoviesDataSource>()

    override fun create(): DataSource<Int, Movie> {
        latestSource = MoviesDataSource(scope, moviesRepository)
        sourceLiveData.postValue(latestSource)
        return latestSource
    }

}