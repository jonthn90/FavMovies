package xyz.jonthn.favmovies.model.apis

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import xyz.jonthn.favmovies.model.data.MoviesResponse

interface RequestMovies {
    @Headers("Accept: application/json")
    @GET("top_rated?api_key=38cd1c5a719c0a7e3ac21fb3ad11f016&language=en-US&page=1")
    suspend fun getMovies(): Response<MoviesResponse>
}