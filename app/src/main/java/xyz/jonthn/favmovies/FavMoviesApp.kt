package xyz.jonthn.favmovies

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import xyz.jonthn.favmovies.utils.appModule

class FavMoviesApp: Application() {

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
                    appModule
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