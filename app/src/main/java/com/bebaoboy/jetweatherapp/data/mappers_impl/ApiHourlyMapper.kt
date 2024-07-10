package com.bebaoboy.jetweatherapp.domain.mappers_impl

import com.bebaoboy.jetweatherapp.data.remote.models.ApiHourlyWeatherModel
import com.bebaoboy.jetweatherapp.domain.mappers.ApiMapper
import com.bebaoboy.jetweatherapp.domain.models.HourlyWeatherModel
import com.bebaoboy.jetweatherapp.utils.Util

class ApiHourlyMapper : ApiMapper<HourlyWeatherModel, ApiHourlyWeatherModel> {
    override fun mapToDomain(entity: ApiHourlyWeatherModel): HourlyWeatherModel {
        return HourlyWeatherModel(
            time = entity.time.map { time ->
                Util.formatNormalDate(
                    "MM/dd HH:mm",
                    time,
                    unix = true,
                )
            },
            temperature2m = entity.temperature2m,
            weatherInfo = entity.weatherCode.map { weatherCode ->
                Util.getWeatherInfo(weatherCode)
            }
        
        
        )
    }
    
}
