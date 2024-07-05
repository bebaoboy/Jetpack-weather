package com.bebaoboy.jetweatherapp.domain.models

import com.bebaoboy.jetweatherapp.utils.WeatherInfo

data class CurrentWeather(
    val temperature: Double,
    val windSpeed: Double,
    val windDirection: String,
    val isDay: Boolean,
    val time: Int,
    val weatherInfo: WeatherInfo,
)
