package xyz.jonthn.favmovies.utils

import org.koin.dsl.module
import xyz.jonthn.favmovies.viewmodel.ViewModelFactory

val appModule = module {

    factory { ViewModelFactory() }
}