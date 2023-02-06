package com.girogevoro.material_design.model.mars

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfMarsAPI {
    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    fun getPictureOfMars(
        @Query("api_key") apiKey: String,
        @Query("earth_date") earthDate: String
    ): Call<PictureOfMarsResponseData>
}