package com.girogevoro.material_design.screens.mars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.girogevoro.material_design.BuildConfig
import com.girogevoro.material_design.model.mars.PictureOfMarsResponseData
import com.girogevoro.material_design.repository.RepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarsViewModel(
    private val liveData: MutableLiveData<MarsAppState> = MutableLiveData(),
    private val repository: RepositoryImpl = RepositoryImpl()
) : ViewModel() {

    fun getLiveData(): LiveData<MarsAppState> {
        return liveData
    }

    fun request(data: String) {
        repository.getPictureOfMarsAPI()
            .getPictureOfMars(BuildConfig.NASA_API_KEY, data)
            .enqueue(callback)

    }

    private val callback = object : Callback<PictureOfMarsResponseData> {
        override fun onResponse(
            call: Call<PictureOfMarsResponseData>,
            response: Response<PictureOfMarsResponseData>
        ) {
            if (response.isSuccessful) {
                liveData.postValue(MarsAppState.Success(response.body()!!))
            } else {
                liveData.postValue(MarsAppState.Error(IllegalStateException("что то пошло не так")))
            }
        }

        override fun onFailure(call: Call<PictureOfMarsResponseData>, t: Throwable) {
            liveData.postValue(MarsAppState.Error(IllegalStateException("что-то пошло не так")))
        }

    }
}


