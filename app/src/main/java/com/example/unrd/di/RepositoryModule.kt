package com.example.unrd.di

import com.example.unrd.data.remote.UnrdService
import com.example.unrd.data.repository.UnrdRepository
import org.koin.dsl.module

var repositoryModule = module {
    fun provideUnrdRepository(unrdService: UnrdService): UnrdRepository {
        return UnrdRepository(unrdService)
    }

    single { provideUnrdRepository(get()) }
}