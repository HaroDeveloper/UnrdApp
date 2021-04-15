package com.example.unrd.di

import com.example.unrd.data.repository.UnrdRepository
import com.example.unrd.ui.home.HomeViewModel
import com.example.unrd.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModelModule = module {
    fun provideMainViewModel(repository: UnrdRepository): MainViewModel {
        return MainViewModel(repository)
    }

    fun provideHomeViewModel(repository: UnrdRepository): HomeViewModel {
        return HomeViewModel(repository)
    }

    viewModel { provideMainViewModel(get()) }
    viewModel { provideHomeViewModel(get()) }

}