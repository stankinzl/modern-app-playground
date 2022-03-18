package com.kinzlstanislav.playground

import androidx.multidex.MultiDexApplication
import com.kinzlstanislav.playground.di.HelpersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import timber.log.Timber

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    HelpersModule().module
                )
            )
        }.androidContext(this@App)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}