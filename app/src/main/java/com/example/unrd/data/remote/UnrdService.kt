package com.example.unrd.data.remote

import com.example.unrd.utils.safeApiCall
import kotlinx.coroutines.Dispatchers

class UnrdService(private val unrdApi: UnrdApi) {

   suspend fun getStories()  =
        safeApiCall(Dispatchers.IO) {
            unrdApi.getStory()
        }

}