package com.ekite.android.testdaggerhilt.nasa.data.api

import com.ekite.android.testdaggerhilt.nasa.data.model.Apod
import retrofit2.Response
import retrofit2.http.GET

interface NasaService {

    @GET("planetary/apod")
    suspend fun getApod(): Response<Apod>
}