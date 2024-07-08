package com.bebaoboy.jetweatherapp.data.remote

import com.bebaoboy.jetweatherapp.data.remote.models.ApiCurrentWeatherModel
import com.bebaoboy.jetweatherapp.data.remote.models.ApiDailyWeatherModel
import com.bebaoboy.jetweatherapp.data.remote.models.ApiHourlyWeatherModel
import com.bebaoboy.jetweatherapp.data.remote.models.ApiWeatherModel
import com.bebaoboy.jetweatherapp.utils.ApiParams
import com.bebaoboy.jetweatherapp.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET(K.END_POINT)
    suspend fun getWeather(
        @Query(ApiParams.LATITUDE) latitude: Double = 0.0,
        @Query(ApiParams.LONGITUDE) longitude: Double = 0.0,
        @Query(ApiParams.DAILY) apiDailyWeatherModel: List<String> =
            ApiDailyWeatherModel::class.java.declaredFields.map { it -> it.name },
        @Query(ApiParams.CURRENT_WEATHER) apiCurrentWeatherWeatherModel: List<String> =
            ApiCurrentWeatherModel::class.java.declaredFields.map { it -> it.name },
        @Query(ApiParams.DAILY) apiHourlyWeatherModel: List<String> =
            ApiHourlyWeatherModel::class.java.declaredFields.map { it -> it.name },
        @Query(ApiParams.TIME_FORMAT) timeFormat: String = "unixtime",
        @Query(ApiParams.TIMEZONE) timezone: String = "Asia/Bangkok"
    
    ): ApiWeatherModel
    
}