package com.ekite.android.testdaggerhilt.nasa.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekite.android.testdaggerhilt.nasa.data.model.Apod
import com.ekite.android.testdaggerhilt.nasa.data.repository.NasaRepository
import com.ekite.android.testdaggerhilt.utils.Resource
import kotlinx.coroutines.launch

class NasaViewModel @ViewModelInject constructor(private val nasaRepository: NasaRepository) : ViewModel() {

    private val _pictureOfTheDay = MutableLiveData<Resource<Apod>>()
    val pictureOfTheDay: LiveData<Resource<Apod>> = _pictureOfTheDay

    fun getPictureOfTheDay() {
        viewModelScope.launch {
            _pictureOfTheDay.postValue(Resource.loading())
            nasaRepository.getApod().let {
                if (it.isSuccessful) {
                    _pictureOfTheDay.postValue(Resource.success(it.body()))
                } else {
                    _pictureOfTheDay.postValue(Resource.error(it.errorBody().toString()))
                }
            }
        }
    }
}