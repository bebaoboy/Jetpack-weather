package com.bebaoboy.jetweatherapp.domain.models

import com.bebaoboy.jetweatherapp.utils.WeatherInfo

data class CurrentWeatherModel(
    val temperature: Double,
    val windSpeed: Double,
    val windDirection: String,
    val isDay: Boolean,
    val time: Long,
    val weatherInfo: WeatherInfo,
)
