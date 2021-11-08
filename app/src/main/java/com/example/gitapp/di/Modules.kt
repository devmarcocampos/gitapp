package com.example.gitapp.di

import com.example.gitapp.data.mapper.RepositoriesMapperImpl
import com.example.gitapp.data.repository.MainRepositoryImpl
import com.example.gitapp.data.source.remote.Api
import com.example.gitapp.data.usecase.GetRepositoriesUseCase
import com.example.gitapp.domain.repository.MainRepository
import com.example.gitapp.presentation.main.MainViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mainViewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val getRepositoriesUseCaseModule = module {
    single { GetRepositoriesUseCase(get()) }
}

val mainRepositoryModule = module {
    single<MainRepository> { MainRepositoryImpl(get(), get()) }
}

val repositoriesMapperImplModule = module {
    single { RepositoriesMapperImpl() }
}

val apiModule = module {
    fun providesApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)

    single { providesApi(get()) }
}

val retrofitModule = module {
    fun providesRetrofit(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
            .build()

        val BASE_URL = "https://api.github.com/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { providesRetrofit() }
}