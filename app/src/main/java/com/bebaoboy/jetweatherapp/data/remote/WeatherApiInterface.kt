package com.bebaoboy.jetweatherapp.data.remote

import com.bebaoboy.jetweatherapp.data.remote.models.Current
import com.bebaoboy.jetweatherapp.data.remote.models.Daily
import com.bebaoboy.jetweatherapp.data.remote.models.Hourly
import com.bebaoboy.jetweatherapp.data.remote.models.WeatherApi
import com.bebaoboy.jetweatherapp.utils.ApiParams
import com.bebaoboy.jetweatherapp.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET(K.END_POINT)
    suspend fun getWeather(
        @Query(ApiParams.LATITUDE) latitude: Double,
        @Query(ApiParams.LONGITUDE) longitude: Double,
        @Query(ApiParams.DAILY) daily: List<String> =
            Daily::class.java.declaredFields.map { it -> it.name },
        @Query(ApiParams.CURRENT_WEATHER) currentWeather: List<String> =
            Current::class.java.declaredFields.map { it -> it.name },
        @Query(ApiParams.DAILY) hourly: List<String> =
            Hourly::class.java.declaredFields.map { it -> it.name },
        @Query(ApiParams.TIME_FORMAT) timeFormat: String = "unixtime",
        @Query(ApiParams.TIMEZONE) timezone: String = "Asia/Bangkok"

    ): WeatherApi

}