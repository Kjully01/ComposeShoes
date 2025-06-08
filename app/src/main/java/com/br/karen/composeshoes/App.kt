package com.br.karen.composeshoes

import android.app.Application
import com.br.karen.composeshoes.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule
            )
        }
    }
}