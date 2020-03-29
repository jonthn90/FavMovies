package xyz.jonthn.favmovies

import android.app.Application
import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber

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