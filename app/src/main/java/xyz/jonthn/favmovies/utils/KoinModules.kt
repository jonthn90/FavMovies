package xyz.jonthn.favmovies.utils

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import xyz.jonthn.favmovies.FavMoviesApp
import xyz.jonthn.favmovies.model.apis.NetworkConnectionInterceptor
import xyz.jonthn.favmovies.model.apis.Retrofit
import xyz.jonthn.favmovies.model.database.AppDatabase
import xyz.jonthn.favmovies.model.repositories.MoviesRepository
import xyz.jonthn.favmovies.model.repositories.MoviesRepostoryImpl
import xyz.jonthn.favmovies.viewmodel.MainViewModel
import xyz.jonthn.favmovies.viewmodel.ViewModelFactory

val databaseModule = module {
    single { AppDatabase.getDatabase(get()) }
    single { get<AppDatabase>().moviesDAO() }
}

val moviesModule = module {
    single { NetworkConnectionInterceptor(FavMoviesApp.appContext!!) }

    single { Retrofit.instanceMovies(get()) }

    single<MoviesRepository> { MoviesRepostoryImpl(get(), get()) }
}

val viewModelsModule = module {

    viewModel { MainViewModel(get()) }
    factory { ViewModelFactory(get()) }
}



