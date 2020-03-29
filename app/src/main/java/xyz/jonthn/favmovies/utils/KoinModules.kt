package xyz.jonthn.favmovies.utils

import org.koin.dsl.module
import xyz.jonthn.favmovies.FavMoviesApp
import xyz.jonthn.favmovies.model.apis.NetworkConnectionInterceptor
import xyz.jonthn.favmovies.model.apis.Retrofit
import xyz.jonthn.favmovies.model.repositories.MoviesRepository
import xyz.jonthn.favmovies.model.repositories.MoviesRepostoryImpl
import xyz.jonthn.favmovies.viewmodel.ViewModelFactory

val moviesModule = module {
    single { NetworkConnectionInterceptor(FavMoviesApp.appContext!!) }
    single<MoviesRepository> { MoviesRepostoryImpl(Retrofit.instanceMovies(get())) }
}

val viewModelsModule = module {
    factory { ViewModelFactory(get()) }
}

