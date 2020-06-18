package com.ekite.android.testdaggerhilt.nasa.data.repository

import com.ekite.android.testdaggerhilt.nasa.data.api.NasaService
import com.ekite.android.testdaggerhilt.nasa.data.model.Apod
import javax.inject.Inject

class NasaRepository @Inject constructor(private val nasaService: NasaService) {

    suspend fun getApod() = nasaService.getApod()
}