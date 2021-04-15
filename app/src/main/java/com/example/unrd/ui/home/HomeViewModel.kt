package com.example.unrd.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unrd.data.model.Story
import com.example.unrd.data.repository.UnrdRepository
import com.example.unrd.utils.getResult
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UnrdRepository) : ViewModel() {
    var story: MutableLiveData<Story> = MutableLiveData()
    var status: MutableLiveData<Boolean> = MutableLiveData()

    fun getStory() {
        viewModelScope.launch {
            repository.getStories().getResult(
                success = {
                    story.postValue(it)
                    status.postValue(true)
                },
                networkError = {
                    status.postValue(false)
                }
            )
        }
    }
}