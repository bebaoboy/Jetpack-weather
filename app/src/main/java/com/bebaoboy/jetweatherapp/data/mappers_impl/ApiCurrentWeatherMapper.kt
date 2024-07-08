package com.bebaoboy.jetweatherapp.domain.mappers_impl

import android.icu.text.DateFormat
import com.bebaoboy.jetweatherapp.data.remote.models.ApiCurrentWeatherModel
import com.bebaoboy.jetweatherapp.domain.mappers.ApiMapper
import com.bebaoboy.jetweatherapp.domain.models.CurrentWeatherModel
import com.bebaoboy.jetweatherapp.utils.Util

class ApiCurrentWeatherMapper : ApiMapper<CurrentWeatherModel, ApiCurrentWeatherModel> {
    override fun mapToDomain(entity: ApiCurrentWeatherModel): CurrentWeatherModel {
        return CurrentWeatherModel(
            temperature = entity.temperature2m,
            weatherInfo = Util.getWeatherInfo(entity.weatherCode),
            windDirection = Util.getWindDirection(entity.windDirection10m.toDouble()),
            windSpeed = entity.windSpeed10m,
            time = Util.formatNormalDate(
                DateFormat.ABBR_MONTH_DAY,
                entity.time.toLong(),
                unix = true
            ),
            isDay = entity.isDay == 1
        
        )
    }
    
}