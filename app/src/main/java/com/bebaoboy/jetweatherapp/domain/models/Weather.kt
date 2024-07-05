package com.bebaoboy.jetweatherapp.domain.models

data class Weather(
    val currentWeather: CurrentWeather,
    val daily: DailyWeather,
    val hourly: HourlyWeather
)
