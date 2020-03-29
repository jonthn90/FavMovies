package xyz.jonthn.favmovies.model.apis

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {

    const val REQUEST_MOVIES = "https://api.themoviedb.org/3/movie/"

    private fun createInstance(networkConnectionInterceptor: NetworkConnectionInterceptor, baseUrl: String): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(networkConnectionInterceptor)
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
            .client(okHttpClient)
            .build()
    }

    fun instanceMovies(networkConnectionInterceptor: NetworkConnectionInterceptor) : RequestMovies {
        return createInstance(networkConnectionInterceptor, REQUEST_MOVIES).create(RequestMovies::class.java)
    }
}

