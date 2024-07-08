package com.bebaoboy.jetweatherapp.domain.mappers_impl

import com.bebaoboy.jetweatherapp.data.remote.models.ApiCurrentWeatherModel
import com.bebaoboy.jetweatherapp.data.remote.models.ApiDailyWeatherModel
import com.bebaoboy.jetweatherapp.data.remote.models.ApiHourlyWeatherModel
import com.bebaoboy.jetweatherapp.data.remote.models.ApiWeatherModel
import com.bebaoboy.jetweatherapp.domain.mappers.ApiMapper
import com.bebaoboy.jetweatherapp.domain.models.CurrentWeatherModel
import com.bebaoboy.jetweatherapp.domain.models.DailyWeatherModel
import com.bebaoboy.jetweatherapp.domain.models.HourlyWeatherModel
import com.bebaoboy.jetweatherapp.domain.models.WeatherModel
import com.bebaoboy.jetweatherapp.utils.ApiCurrentWeatherMapperAnnotation
import com.bebaoboy.jetweatherapp.utils.ApiDailyMapperAnnotation
import com.bebaoboy.jetweatherapp.utils.ApiHourlyMapperAnnotation
import javax.inject.Inject

class ApiWeatherMapper @Inject constructor(
    @ApiDailyMapperAnnotation private val appDailyMapper: ApiMapper<DailyWeatherModel, ApiDailyWeatherModel>,
    @ApiHourlyMapperAnnotation private val appHourlyMapper: ApiMapper<HourlyWeatherModel, ApiHourlyWeatherModel>,
    @ApiCurrentWeatherMapperAnnotation private val appCurrentWeatherMapper: ApiMapper<CurrentWeatherModel, ApiCurrentWeatherModel>
) : ApiMapper<WeatherModel, ApiWeatherModel> {
    override fun mapToDomain(entity: ApiWeatherModel): WeatherModel {
        return WeatherModel(
            currentWeatherModel = appCurrentWeatherMapper.mapToDomain(entity.apiCurrentWeatherModel),
            dailyWeatherModel = appDailyMapper.mapToDomain(entity.apiDailyWeatherModel),
            hourlyWeatherModel = appHourlyMapper.mapToDomain(entity.apiHourlyWeatherModel)
        )
    }
}