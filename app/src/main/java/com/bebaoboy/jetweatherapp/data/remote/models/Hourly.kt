package com.bebaoboy.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hourly(
    @SerialName("temperature_2m")
    val temperature2m: List<Double> = listOf(),
    @SerialName("time")
    val time: List<Int> = listOf(),
    @SerialName("weather_code")
    val weatherCode: List<Int> = listOf()
)