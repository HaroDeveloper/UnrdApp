package com.example.unrd

import android.app.Application
import com.example.unrd.di.remoteModule
import com.example.unrd.di.repositoryModule
import com.example.unrd.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.koinApplication

class Unrd : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Unrd)

            modules(listOf(remoteModule, viewModelModule, repositoryModule))
        }
    }
}
