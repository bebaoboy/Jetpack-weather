package com.bebaoboy.jetweatherapp.data.remote

import com.bebaoboy.jetweatherapp.data.remote.models.ApiWeatherModel
import com.bebaoboy.jetweatherapp.utils.ApiParams
import com.bebaoboy.jetweatherapp.utils.K
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiInterface {
    @GET(K.END_POINT)
    suspend fun getWeather(
        @Query(ApiParams.LATITUDE) latitude: Double = 10.755846,
        @Query(ApiParams.LONGITUDE) longitude: Double = 106.707996,
        @Query(ApiParams.DAILY) daily: Array<String> = arrayOf(
            "weather_code",
            "temperature_2m_max",
            "temperature_2m_min",
            "wind_speed_10m_max",
            "wind_direction_10m_dominant",
            "sunrise",
            "sunset",
            "uv_index_max",
        ),
        @Query(ApiParams.CURRENT_WEATHER) currentWeather: Array<String> = arrayOf(
            "temperature_2m",
            "is_day",
            "weather_code",
            "wind_speed_10m",
            "wind_direction_10m",
        ),
        @Query(ApiParams.HOURLY) hourlyWeather: Array<String> = arrayOf(
            "weather_code",
            "temperature_2m",
        ),
        @Query(ApiParams.TIME_FORMAT) timeFormat: String = "unixtime",
        @Query(ApiParams.TIMEZONE) timezone: String = "Asia/Bangkok"
    
    ): ApiWeatherModel
    
}
