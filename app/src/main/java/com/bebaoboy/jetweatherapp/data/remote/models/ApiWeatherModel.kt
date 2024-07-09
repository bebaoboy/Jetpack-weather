package com.bebaoboy.jetweatherapp.data.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiWeatherModel(
    @SerialName("current")
    val apiCurrentWeatherModel: ApiCurrentWeatherModel = ApiCurrentWeatherModel(),
    @SerialName("current_units")
    val currentUnits: CurrentUnits = CurrentUnits(),
    @SerialName("daily")
    val apiDailyWeatherModel: ApiDailyWeatherModel = ApiDailyWeatherModel(),
    @SerialName("daily_units")
    val dailyUnits: DailyUnits = DailyUnits(),
    @SerialName("elevation")
    val elevation: Double = 0.0, // 38.0
    @SerialName("generationtime_ms")
    val generationtimeMs: Double = 0.0, // 0.2599954605102539
    @SerialName("hourly")
    val apiHourlyWeatherModel: ApiHourlyWeatherModel = ApiHourlyWeatherModel(),
    @SerialName("hourly_units")
    val hourlyUnits: HourlyUnits = HourlyUnits(),
    @SerialName("latitude")
    val latitude: Double = 0.0, // 52.52
    @SerialName("longitude")
    val longitude: Double = 0.0, // 13.419998
    @SerialName("timezone")
    val timezone: String = "", // Asia/Bangkok
    @SerialName("timezone_abbreviation")
    val timezoneAbbreviation: String = "", // +07
    @SerialName("utc_offset_seconds")
    val utcOffsetSeconds: Int = 0 // 25200
)