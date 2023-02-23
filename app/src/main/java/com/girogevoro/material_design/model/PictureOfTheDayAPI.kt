package com.girogevoro.material_design.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey:String): Call<PictureOfTheDayResponseData>

    @GET("planetary/apod")
    fun getPictureOfTheDayByDate(@Query("api_key") apiKey:String,@Query("date") date:String):Call<PictureOfTheDayResponseData>
}
