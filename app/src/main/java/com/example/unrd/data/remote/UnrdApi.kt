package com.example.unrd.data.remote

import com.example.unrd.data.model.Story
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UnrdApi {
    @GET("unrd-scratch/resp.json")
    suspend fun getStory(): Story
}