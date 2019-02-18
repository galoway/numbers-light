package com.tapptic.numberslight.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tapptic.numberslight.model.Number
import com.tapptic.numberslight.service.ApiService
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class ListViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {

    private val numbers: MutableLiveData<List<Number>> = MutableLiveData()

    fun numbers(): LiveData<List<Number>> = numbers

    fun loadNumbers() {
        apiService.getNumbers().enqueue(object : Callback, retrofit2.Callback<List<Number>> {
            override fun onResponse(call: Call<List<Number>>?, response: Response<List<Number>>?) {
                numbers.postValue(response?.body())
            }

            override fun onFailure(call: Call<List<Number>>?, t: Throwable?) {
                numbers.postValue(null)
            }
        })
    }
}