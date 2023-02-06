package com.girogevoro.material_design.screens.mars

import com.girogevoro.material_design.model.mars.PictureOfMarsResponseData

sealed class MarsAppState {
    data class Success(val pictureMarsResponseData: PictureOfMarsResponseData) : MarsAppState()
    data class Error(val error: Throwable) : MarsAppState()
    object Loading : MarsAppState()
}