package com.bebaoboy.jetweatherapp.di

import com.bebaoboy.jetweatherapp.data.remote.WeatherApiInterface
import com.bebaoboy.jetweatherapp.utils.K
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    
    @Provides
    @Singleton
    fun provideAPi(builder: Retrofit.Builder): WeatherApiInterface {
        return builder
            .build()
            .create(WeatherApiInterface::class.java)
    }
    
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        val contentType = "application/json"
        return Retrofit.Builder()
            .baseUrl(K.API_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType.toMediaType()))
    }
    
}