package com.example.unrd.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unrd.data.repository.UnrdRepository
import com.example.unrd.utils.getResult
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UnrdRepository) : ViewModel() {

}