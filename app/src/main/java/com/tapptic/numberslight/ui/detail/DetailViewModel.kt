package com.tapptic.numberslight.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tapptic.numberslight.model.Number
import com.tapptic.numberslight.service.ApiService
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class DetailViewModel @Inject constructor(private val apiService: ApiService): ViewModel() {

    private val number: MutableLiveData<Number> = MutableLiveData()

    fun number(): LiveData<Number> = number

    fun loadNumber(name: String) {
        apiService.getNumberDetail(name).enqueue(object : Callback, retrofit2.Callback<Number> {
            override fun onResponse(call: Call<Number>, response: Response<Number>) {
                number.postValue(response.body())
            }

            override fun onFailure(call: Call<Number>?, t: Throwable?) {
                number.postValue(null)
            }
        })
    }
}