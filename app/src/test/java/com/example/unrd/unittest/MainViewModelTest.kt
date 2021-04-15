package com.example.unrd.unittest

import com.example.unrd.data.repository.UnrdRepository
import com.example.unrd.ui.main.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var unrdRepository: UnrdRepository

    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.mainViewModel = MainViewModel(this.unrdRepository)
    }
    //...

    @Test
    fun fetchUserRepositories_positiveResponse() {
        // Mock API response
        Mockito.`when`(this.unrdRepository.getStories(ArgumentMatchers.anyString())).thenAnswer {
            return@thenAnswer Maybe.just(ArgumentMatchers.anyList<Repository>())
        }
        // Attacch fake observer
        val observer = mock(Observer::class.java) as Observer<LiveDataResult<List<Repository>>>
        this.mainViewModel.repositoriesLiveData.observeForever(observer)
        // Invoke
        this.mainViewModel.fetchUserRepositories(ArgumentMatchers.anyString())
        // Verify
        assertNotNull(this.mainViewModel.repositoriesLiveData.value)
        assertEquals(LiveDataResult.Status.SUCCESS, this.mainViewModel.repositoriesLiveData.value?.status)
    }
}