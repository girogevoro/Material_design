package com.girogevoro.material_design.screens.mars

import com.girogevoro.material_design.model.PictureOfTheDayResponseData
import com.girogevoro.material_design.screens.earth.PictureOfTheDay.AppState

sealed class MarsAppState {
    data class Success(val pictureOfTheDayResponseData: PictureOfTheDayResponseData) : MarsAppState()
    data class Error(val error: Throwable) : MarsAppState()
    object Loading : MarsAppState()
}