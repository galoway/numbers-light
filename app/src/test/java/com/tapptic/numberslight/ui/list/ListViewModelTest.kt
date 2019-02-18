package com.tapptic.numberslight.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tapptic.numberslight.model.Number
import com.tapptic.numberslight.service.ApiService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito.verify
import retrofit2.Response



class ListViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var mockedCall: Call<List<Number>>

    @Mock
    lateinit var viewStateObserver: Observer<List<Number>>

    @Captor
    lateinit var argumentCapture: ArgumentCaptor<Callback<List<Number>>>

    lateinit var listViewModel: ListViewModel

    private val fakeNumbers = listOf(Number("fake", "1", "fake"))

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        this.listViewModel = ListViewModel(apiService)
        listViewModel.numbers().observeForever(viewStateObserver)
    }

    @Test
    fun loadNumbersSuccess() {
        Mockito.`when`(this.apiService.getNumbers()).thenReturn(mockedCall)
        val res = Response.success(fakeNumbers)

        this.listViewModel.loadNumbers()

        verify(mockedCall).enqueue(argumentCapture.capture())
        argumentCapture.value.onResponse(mockedCall, res)

        verify(viewStateObserver).onChanged(fakeNumbers)
    }

    @Test
    fun loadNumbersFail() {
        Mockito.`when`(this.apiService.getNumbers()).thenReturn(mockedCall)
        val throwable = Throwable(RuntimeException())

        this.listViewModel.loadNumbers()

        verify(mockedCall).enqueue(argumentCapture.capture())
        argumentCapture.value.onFailure(null, throwable)

        verify(viewStateObserver).onChanged(null)
    }
}