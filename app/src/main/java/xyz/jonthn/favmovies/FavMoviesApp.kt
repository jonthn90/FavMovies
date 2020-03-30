package xyz.jonthn.favmovies

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import xyz.jonthn.favmovies.utils.databaseModule
import xyz.jonthn.favmovies.utils.moviesModule
import xyz.jonthn.favmovies.utils.viewModelsModule

class FavMoviesApp : Application() {

    companion object {
        var appContext: Context? = null
        var favMoviesApp: FavMoviesApp? = null
    }

    override fun onCreate() {
        super.onCreate()

        appContext = this.applicationContext
        favMoviesApp = this

        /**
         * Koin DI init
         */
        startKoin {
            androidLogger()
            androidContext(this@FavMoviesApp)
            modules(
                listOf(
                    databaseModule,
                    moviesModule,
                    viewModelsModule
                )
            )
        }

        /**
         * Timber init config
         */
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        /**
         * Fresco init
         */
        Fresco.initialize(this)
    }
}