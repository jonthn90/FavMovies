package xyz.jonthn.favmovies.model.paging

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import xyz.jonthn.favmovies.model.apis.RequestMovies
import xyz.jonthn.favmovies.model.data.Movie

class MoviesDataSource(private val scope: CoroutineScope) :
    PageKeyedDataSource<Int, Movie>(),
    KoinComponent {

    val retrofit: RequestMovies by inject()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {

        scope.launch {

            val response = retrofit.getMovies(1).body()

            response.let {

                val movies = response!!.results

                movies.let {
                    callback.onResult(movies, null, response.page + 1)
                }
            }

        }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        scope.launch {

            val response = retrofit.getMovies(params.key).body()

            response.let {

                val movies = response!!.results

                movies.let {
                    callback.onResult(movies, response.page + 1)
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }
}