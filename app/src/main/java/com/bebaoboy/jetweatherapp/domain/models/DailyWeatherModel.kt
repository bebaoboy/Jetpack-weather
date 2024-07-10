package com.bebaoboy.jetweatherapp.domain.models

import com.bebaoboy.jetweatherapp.utils.WeatherInfo

data class DailyWeatherModel(
    private val time: List<String>,
    private val temperature2mMax: List<Double>,
    private val temperature2mMin: List<Double>,
    private val uvIndexMax: List<Double>,
    private val windDirection10mDominant: List<String>,
    private val windSpeed10mMax: List<Double>,
    private val sunrise: List<String>,
    private val sunset: List<String>,
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
                weatherInfo = weatherInfo[i],
                time = time[i]
            )
        }
    
    data class DailyWeatherInfo(
        val time: String,
        val temperature2mMax: Double,
        val temperature2mMin: Double,
        val uvIndexMax: Double,
        val windDirection10mDominant: String,
        val windSpeed10mMax: Double,
        val sunrise: String,
        val sunset: String,
        val weatherInfo: WeatherInfo,
    )
}
