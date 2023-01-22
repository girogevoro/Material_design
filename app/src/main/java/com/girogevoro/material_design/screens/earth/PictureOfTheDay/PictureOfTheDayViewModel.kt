package com.girogevoro.material_design.screens.earth.PictureOfTheDay

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.girogevoro.material_design.BuildConfig
import com.girogevoro.material_design.model.PictureOfTheDayResponseData
import com.girogevoro.material_design.repository.RepositoryImpl
import com.girogevoro.material_design.util.DEBUG
import com.girogevoro.material_design.util.TAG
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl()
) :
    ViewModel() {
    fun getLiveData(): LiveData<AppState> {
        //
        return liveData
    }


    fun sendRequest(agoDay:Int) {
        liveData.postValue(AppState.Loading)
        val currentDate = getShiftDate(agoDay)
        if (DEBUG) {
            Log.d(TAG, currentDate)
        }
        repositoryImpl.getPictureOfTheDayApi()
            .getPictureOfTheDayByDate(BuildConfig.NASA_API_KEY, currentDate)
            .enqueue(callback)
    }

    private fun getShiftDate(ago: Int): String {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -ago)
        return sdf.format(calendar.time)
    }


    private val callback = object : Callback<PictureOfTheDayResponseData> {
        override fun onResponse(
            call: Call<PictureOfTheDayResponseData>,
            response: Response<PictureOfTheDayResponseData>
        ) {
            if (response.isSuccessful) {
                liveData.postValue(AppState.Success(response.body()!!))
            } else {
                liveData.postValue(AppState.Error(IllegalStateException("что-то пошло не так")))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponseData>, t: Throwable) {
            liveData.postValue(AppState.Error(IllegalStateException("что-то пошло не так")))
        }
    }
}
