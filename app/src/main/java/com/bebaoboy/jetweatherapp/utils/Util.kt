package com.bebaoboy.jetweatherapp.utils

import android.icu.text.DateFormat
import androidx.annotation.DrawableRes
import com.bebaoboy.jetweatherapp.R
import java.text.SimpleDateFormat
import java.util.*

object Util {
    private val DIRECTIONS = listOf(
        "North",
        "North East",
        "East",
        "South East",
        "South",
        "South West",
        "West",
        "North West"
    )

    fun formatNormalDate(pattern: String, time: Long, unix: Boolean = false): String {
        return SimpleDateFormat(pattern, Locale.getDefault()).format(time * if (unix) 1000 else 1)
    }

    fun getWindDirection(windDirection: Double): String {
        return DIRECTIONS[(windDirection % 360 / 45 % 8).toInt()]
    }

    fun getWeatherInfo(code: Int): WeatherInfo {
        return when (code) {
            0 -> WeatherInfo("Clear sky", R.drawable.clear_sky)
            1 -> WeatherInfo("Mainly clear", R.drawable.mainly_clear)
            2 -> WeatherInfo("partly cloudy", R.drawable.mainly_clear)
            3 -> WeatherInfo("overcast", R.drawable.over_cast)
            45, 48 -> WeatherInfo("Fog", R.drawable.fog)
            51, 53, 55,
            -> WeatherInfo("Drizzle", R.drawable.drizzle)

            56, 57 -> WeatherInfo("Freezing Drizzle", R.drawable.freezing_drizzle)
            61,
            -> WeatherInfo("Rain: Slight", R.drawable.rain_slight)

            63 -> WeatherInfo("Rain: Moderate", R.drawable.rain_heavy)
            65 -> WeatherInfo("Rain: Heavy", R.drawable.rain_heavy)
            66, 67 -> WeatherInfo("Freezing Rain", R.drawable.freezing_rain)
            71 -> WeatherInfo("Snow fall: Slight", R.drawable.snow_fall_slight)
            73 -> WeatherInfo("Snow fall: moderate", R.drawable.snow_fall_slight)
            75 -> WeatherInfo("Snow fall: Heavy", R.drawable.snow_fall)
            77 -> WeatherInfo("Snow grains", R.drawable.snow_fall)
            80, 81, 82 -> WeatherInfo("Rain showers: Slight", R.drawable.rain_slight)
            85, 86 -> WeatherInfo("Snow showers slight", R.drawable.snow_fall_slight)
            95, 96, 99 -> WeatherInfo("Thunderstorm: Slight", R.drawable.thunder_storm)
            else -> WeatherInfo("Unknown", R.drawable.clear_sky)
        }
    }

    fun isTodayDate(day: String): Boolean {
        val todayDate = formatNormalDate(DateFormat.ABBR_WEEKDAY, Date().time)
        return todayDate.lowercase() == day.lowercase()
    }

}

data class WeatherInfo(
    val info: String,
    @DrawableRes val icon: Int
)