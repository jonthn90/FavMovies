package xyz.jonthn.favmovies.model.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import xyz.jonthn.favmovies.model.apis.RequestMovies
import xyz.jonthn.favmovies.model.daos.MoviesDAO
import xyz.jonthn.favmovies.model.data.Movie

class MoviesRepostoryImpl(private val requestMovies: RequestMovies, private val moviesDAO: MoviesDAO) : MoviesRepository {

    override suspend fun getMoviesWS(page: Int): List<Movie> {

        val response = requestMovies.getMovies(page).body()
        response.let {
            if (it != null) {
                return it.results
            }
        }
        return listOf()
    }

    override suspend fun getFavMovies(): List<Movie> {
        return moviesDAO.getAll()
    }

    override suspend fun insertFavMovie(movie: Movie) {
        moviesDAO.insert(movie)
    }

    override suspend fun deleteFavMovie(id: Int) {
        moviesDAO.deleteFavMovie(id)
    }

    override fun getFavMoviesLive(): LiveData<List<Movie>> {
        return moviesDAO.getAllLive()
    }
}