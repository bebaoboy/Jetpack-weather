package com.bebaoboy.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Daily(
    @SerialName("sunrise")
    val sunrise: List<Int> = listOf(),
    @SerialName("sunset")
    val sunset: List<Int> = listOf(),
    @SerialName("temperature_2m_max")
    val temperature2mMax: List<Double> = listOf(),
    @SerialName("temperature_2m_min")
    val temperature2mMin: List<Double> = listOf(),
    @SerialName("time")
    val time: List<Int> = listOf(),
    @SerialName("uv_index_max")
    val uvIndexMax: List<Double> = listOf(),
    @SerialName("weather_code")
    val weatherCode: List<Int> = listOf(),
    @SerialName("wind_direction_10m_dominant")
    val windDirection10mDominant: List<Int> = listOf(),
    @SerialName("wind_speed_10m_max")
    val windSpeed10mMax: List<Double> = listOf()
)