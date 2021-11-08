package com.example.gitapp

import android.app.Application
import android.content.Context
import com.example.gitapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: Application? = null

        fun applicationContext() : Context? {
            instance?.let {
                return it.applicationContext
            } ?: run {
                return null
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                listOf(
                    retrofitModule, apiModule, repositoriesMapperImplModule,
                    mainRepositoryModule, getRepositoriesUseCaseModule, mainViewModelModule
                )
            )
        }
    }
}