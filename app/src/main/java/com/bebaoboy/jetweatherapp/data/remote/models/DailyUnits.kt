package com.bebaoboy.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyUnits(
    @SerialName("sunrise")
    val sunrise: String = "", // unixtime
    @SerialName("sunset")
    val sunset: String = "", // unixtime
    @SerialName("temperature_2m_max")
    val temperature2mMax: String = "", // °C
    @SerialName("temperature_2m_min")
    val temperature2mMin: String = "", // °C
    @SerialName("time")
    val time: String = "", // unixtime
    @SerialName("uv_index_max")
    val uvIndexMax: String = "",
    @SerialName("weather_code")
    val weatherCode: String = "", // wmo code
    @SerialName("wind_direction_10m_dominant")
    val windDirection10mDominant: String = "", // °
    @SerialName("wind_speed_10m_max")
    val windSpeed10mMax: String = "" // km/h
)