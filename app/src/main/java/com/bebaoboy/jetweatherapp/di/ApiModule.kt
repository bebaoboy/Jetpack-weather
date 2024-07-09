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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

//    titude=52.52&longitude=13.41&current=temperature_2m,relative_humidity_2m,is_day,rain,weather_code,wind_speed_10m,wind_direction_10m&hourly=temperature_2m,weather_code&daily=weather_code,temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,wind_speed_10m_max,wind_direction_10m_dominant&timeformat=unixtime&timezone=Asia%2FBangkok
    
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
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging)  // <-- this is the important line!
        return Retrofit.Builder()
            .baseUrl(K.API_BASE_URL)
            .addConverterFactory(json.asConverterFactory(contentType.toMediaType()))
            .client(httpClient.build())
        
    }
    
}