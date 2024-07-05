package com.bebaoboy.jetweatherapp.domain.models

import com.bebaoboy.jetweatherapp.utils.WeatherInfo

data class DailyWeather(
    private val time: List<Int>,
    private val temperature2mMax: List<Double>,
    private val temperature2mMin: List<Double>,
    private val uvIndexMax: List<Double>,
    private val windDirection10mDominant: List<Int>,
    private val windSpeed10mMax: List<Double>,
    private val sunrise: List<Int>,
    private val sunset: List<Int>,
    private val weatherInfo: List<WeatherInfo>,
) {
    val dailyWeatherInfo: List<DailyWeatherInfo>
        get() = temperature2mMin.indices.map { i ->
            DailyWeatherInfo(
                temperature2mMin = temperature2mMin[i],
                temperature2mMax = temperature2mMax[i],
                uvIndexMax = uvIndexMax[i],
                windDirection10mDominant = windDirection10mDominant[i],
                windSpeed10mMax = windSpeed10mMax[i],
                sunrise = sunrise[i],
                sunset = sunset[i],
                weatherInfoItem = weatherInfo[i],
                time = time[i]
            )
        }

    data class DailyWeatherInfo(
        val time: Int,
        val temperature2mMax: Double,
        val temperature2mMin: Double,
        val uvIndexMax: Double,
        val windDirection10mDominant: Int,
        val windSpeed10mMax: Double,
        val sunrise: Int,
        val sunset: Int,
        val weatherInfoItem: WeatherInfo,
    )
}
