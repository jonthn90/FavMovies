package xyz.jonthn.favmovies.model.paging

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import xyz.jonthn.favmovies.model.apis.RequestMovies
import xyz.jonthn.favmovies.model.data.Movie
import xyz.jonthn.favmovies.model.repositories.MoviesRepository

class MoviesDataSource(private val scope: CoroutineScope, val moviesRepository: MoviesRepository) :
    PageKeyedDataSource<Int, Movie>(),
    KoinComponent {

    val retrofit: RequestMovies by inject()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Movie>
    ) {

        scope.launch {

            try {

                val response = retrofit.getMovies(1).body()

                response.let {

                    var movies = response!!.results

                    movies.forEachIndexed() { i, movie ->
                        if (moviesRepository.getMovieFromId(movie.id) != null) {
                            movies[i].isFav = true
                        }
                    }

                    movies.let {
                        callback.onResult(movies, null, response.page + 1)
                    }
                }
            } catch (e: Exception) {

            }

        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

        scope.launch {

            try {
                val response = retrofit.getMovies(params.key).body()

                response.let {

                    val movies = response!!.results

                    movies.forEachIndexed() { i, movie ->
                        if (moviesRepository.getMovieFromId(movie.id) != null) {
                            movies[i].isFav = true
                        }
                    }

                    movies.let {
                        callback.onResult(movies, response.page + 1)
                    }
                }
            } catch (e: Exception) {

            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }
}