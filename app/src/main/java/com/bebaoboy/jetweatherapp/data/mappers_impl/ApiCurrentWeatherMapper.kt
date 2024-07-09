package com.bebaoboy.jetweatherapp.domain.mappers_impl

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
            time = entity.time.toLong(),
            isDay = entity.isDay == 1
        
        )
    }
    
}