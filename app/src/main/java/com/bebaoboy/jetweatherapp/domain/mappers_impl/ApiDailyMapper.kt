package com.bebaoboy.jetweatherapp.domain.mappers_impl

import android.icu.text.DateFormat
import com.bebaoboy.jetweatherapp.data.remote.models.ApiDailyWeatherModel
import com.bebaoboy.jetweatherapp.domain.mappers.ApiMapper
import com.bebaoboy.jetweatherapp.domain.models.DailyWeatherModel
import com.bebaoboy.jetweatherapp.utils.Util


class ApiDailyMapper : ApiMapper<DailyWeatherModel, ApiDailyWeatherModel> {
    override fun mapToDomain(entity: ApiDailyWeatherModel): DailyWeatherModel {
        return DailyWeatherModel(
            time = entity.time.map { time ->
                Util.formatNormalDate(
                    DateFormat.ABBR_WEEKDAY,
                    time,
                    unix = true
                )
            },
            temperature2mMax = entity.temperature2mMax,
            temperature2mMin = entity.temperature2mMin,
            uvIndexMax = entity.uvIndexMax,
            windDirection10mDominant = entity.windDirection10mDominant.map { windDirection ->
                Util.getWindDirection(
                    windDirection.toDouble()
                )
            },
            windSpeed10mMax = entity.windSpeed10mMax,
            sunrise = entity.sunrise.map { time -> Util.formatNormalDate("HH:mm", time.toLong()) },
            sunset = entity.sunset.map { time -> Util.formatNormalDate("HH:mm", time.toLong()) },
            weatherInfo = entity.weatherCode.map { weatherCode ->
                Util.getWeatherInfo(weatherCode)
            }
        )
    }
}