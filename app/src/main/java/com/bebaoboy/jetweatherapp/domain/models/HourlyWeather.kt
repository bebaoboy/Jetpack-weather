package com.bebaoboy.jetweatherapp.domain.models

import com.bebaoboy.jetweatherapp.utils.WeatherInfo

data class HourlyWeather(
    private val time: List<Int>,
    private val temperature2m: List<Double>,
    private val weatherInfo: List<WeatherInfo>,
) {
    val hourlyWeatherInfo: List<HourlyWeatherInfo>
        get() = time.indices.map { i ->
            HourlyWeatherInfo(
                time[i],
                temperature2m[i],
                weatherInfo[i]
            )
        }

    data class HourlyWeatherInfo(
        val time: Int,
        val temperature2m: Double,
        val weatherInfo: WeatherInfo,
    )
}
