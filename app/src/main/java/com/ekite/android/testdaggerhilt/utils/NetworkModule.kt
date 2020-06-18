package com.ekite.android.testdaggerhilt.utils

import com.ekite.android.testdaggerhilt.nasa.data.api.NasaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.nasa.gov/")
            .build()
    }

    @Provides
    fun provideNasaService(retrofit: Retrofit): NasaService {
        return retrofit.create(NasaService::class.java)
    }

}