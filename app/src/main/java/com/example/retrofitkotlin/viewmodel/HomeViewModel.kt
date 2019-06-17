package com.example.retrofitkotlin.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.retrofitkotlin.MarsApi
import com.example.retrofitkotlin.MyApiFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MarshApiStatus{LOADING,ERROR,DONE}

class HomeViewModel : ViewModel() {

    private val viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getAllProperties(MyApiFilter.SHOW_ALL)
    }

    private val _status = MutableLiveData<MarshApiStatus>()

    val status: LiveData<MarshApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()

    val properties:LiveData<List<MarsProperty>>
    get() = _properties

    private fun getAllProperties(filter:MyApiFilter) {
        coroutineScope.launch {
            try {
                _status.value = MarshApiStatus.LOADING
                val listResutl = MarsApi.retrofitService.getProperties(filter.value).await()
                if (listResutl.size>0)
                _properties.value = listResutl
                _status.value = MarshApiStatus.DONE

            } catch (t: Throwable) {
                _status.value = MarshApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    fun updateFilter(filter:MyApiFilter){
        getAllProperties(filter)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}