package com.girogevoro.material_design.screens.system

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SystemViewModel(
    private val liveDataListDataUser: MutableLiveData<List<DataUser>> = MutableLiveData()
) :
    ViewModel() {
    private val repository by lazy {
        val list = ArrayList<DataUser>()
        SystemRepository(list)
    }

    fun getLiveDataListDataUser(): LiveData<List<DataUser>> {
        return liveDataListDataUser
    }

    fun refresh() {
        liveDataListDataUser.postValue(repository.getList())
    }


    fun addData(position: Int, dataUser: DataUser) {
        repository.add(position, dataUser)
        liveDataListDataUser.postValue(repository.getList())
    }

    fun moveData(positionOld: Int, positionNew: Int) {
        repository.move(positionOld, positionNew)
        liveDataListDataUser.postValue(repository.getList())
    }

    fun remove(position: Int) {
        repository.remove(position)
        liveDataListDataUser.postValue(repository.getList())
    }


}