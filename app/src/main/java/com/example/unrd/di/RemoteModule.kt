package com.example.unrd.di

import com.example.unrd.data.remote.UnrdApi
import com.example.unrd.data.remote.UnrdService
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val remoteModule = module {

    val baseUrl = "https://s3-eu-west-1.amazonaws.com/"

    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        .create()

    fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .build()

    fun provideRetrofit(gsonFactory: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gsonFactory))
        .client(client)
        .build()

    fun provideUnrdService(
        unrdApi: UnrdApi
    ): UnrdService {
        return UnrdService(unrdApi)
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
    single { provideUnrdService(get()) }
    single { get<Retrofit>().create(UnrdApi::class.java) }
}
