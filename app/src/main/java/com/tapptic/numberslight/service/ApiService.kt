package com.tapptic.numberslight.service

import com.tapptic.numberslight.model.Number
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("test/json.php")
    fun getNumbers(): Call<List<Number>>

    @GET("test/json.php")
    fun getNumberDetail(@Query("name") name: String): Call<Number>
}