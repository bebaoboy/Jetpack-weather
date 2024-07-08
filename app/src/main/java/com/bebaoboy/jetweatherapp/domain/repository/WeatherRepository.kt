package com.bebaoboy.jetweatherapp.domain.repository

import com.bebaoboy.jetweatherapp.domain.models.WeatherModel
import com.bebaoboy.jetweatherapp.utils.Response.Response

interface WeatherRepository {
    fun getWeather(): kotlinx.coroutines.flow.Flow<Response<WeatherModel>>
}