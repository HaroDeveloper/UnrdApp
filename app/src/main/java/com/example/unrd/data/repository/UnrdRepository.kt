package com.example.unrd.data.repository

import com.example.unrd.data.model.Story
import com.example.unrd.data.remote.UnrdService
import com.example.unrd.utils.wraper.ResultWrapper

class UnrdRepository(private val unrdService: UnrdService) {
    suspend fun getStories(): ResultWrapper<Story> {
        return unrdService.getStories()
    }
}