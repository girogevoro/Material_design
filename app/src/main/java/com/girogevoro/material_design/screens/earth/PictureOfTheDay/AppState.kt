package com.girogevoro.material_design.screens.earth.PictureOfTheDay

import com.girogevoro.material_design.model.PictureOfTheDayResponseData

sealed class AppState {
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
