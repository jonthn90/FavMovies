package xyz.jonthn.favmovies.model.repositories

import xyz.jonthn.favmovies.model.apis.RequestMovies
import xyz.jonthn.favmovies.model.data.MoviesResponse

class MoviesRepostoryImpl(private val requestMovies: RequestMovies) : MoviesRepository {

    override suspend fun getMoviesWS(): MoviesResponse? {

        val response = requestMovies.getMovies().body()

        response.let {
            return it!!
        }

        return null
    }
}