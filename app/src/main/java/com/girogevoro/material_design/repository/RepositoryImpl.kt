package com.girogevoro.material_design.repository

import com.girogevoro.material_design.model.PictureOfTheDayAPI
import com.girogevoro.material_design.model.mars.PictureOfMarsAPI
import com.girogevoro.material_design.util.NASA_BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RepositoryImpl : Repository {


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(NASA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    fun getPictureOfTheDayApi(): PictureOfTheDayAPI {
        return retrofit.create(PictureOfTheDayAPI::class.java)
    }

    fun getPictureOfMarsAPI(): PictureOfMarsAPI{
        return retrofit.create(PictureOfMarsAPI::class.java)
    }
}