package com.bebaoboy.jetweatherapp.domain.models

data class WeatherModel(
    val currentWeatherModel: CurrentWeatherModel,
    val dailyWeatherModel: DailyWeatherModel,
    val hourlyWeatherModel: HourlyWeatherModel
)
