package com.example.unrd.unittest

import com.example.unrd.data.remote.UnrdApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class UnrdApiCallTest {
    lateinit var service: UnrdApi

    companion object {
        var storyDuration = "4 days"
        var storyTitle = "My Last 3 Days"
        var minAge = "15"
    }

    @Before
    fun onSetup() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://s3-eu-west-1.amazonaws.com/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
        service = retrofit.create(UnrdApi::class.java)
    }

    @Test
    fun getData() {
        runBlocking {
            val story = service.getStory()
            Assert.assertEquals(story.result.duration, storyDuration)
            Assert.assertEquals(story.result.name, storyTitle)
            Assert.assertEquals(story.result.minAge, minAge)
        }
    }
}