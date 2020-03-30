package xyz.jonthn.favmovies.model.repositories

import xyz.jonthn.favmovies.model.apis.RequestMovies
import xyz.jonthn.favmovies.model.data.Movie

class MoviesRepostoryImpl(private val requestMovies: RequestMovies) : MoviesRepository {

    override suspend fun getMoviesWS(page: Int): List<Movie> {

        val response = requestMovies.getMovies(page).body()
        response.let {
            if (it != null) {
                return it.results
            }
        }

        return listOf()
    }
}