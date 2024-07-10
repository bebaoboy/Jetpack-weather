package com.bebaoboy.jetweatherapp.data.repository

import com.bebaoboy.jetweatherapp.data.remote.WeatherApiInterface
import com.bebaoboy.jetweatherapp.domain.mappers_impl.ApiWeatherMapper
import com.bebaoboy.jetweatherapp.domain.models.WeatherModel
import com.bebaoboy.jetweatherapp.domain.repository.WeatherRepository
import com.bebaoboy.jetweatherapp.utils.Response.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApiInterface: WeatherApiInterface,
    private val weatherMapper: ApiWeatherMapper
) : WeatherRepository {
    override fun getWeather(): Flow<Response<WeatherModel>> = flow {
        emit(Response.Loading())
        
        val apiWeather = weatherApiInterface.getWeather(
//            latitude = location.latitude,
//            longitude = location.longitude
        )
        val weather = weatherMapper.mapToDomain(apiWeather)
        emit(Response.Success(weather))
    }.catch {
        it.printStackTrace()
        emit(Response.Error(it.message.orEmpty()))
    }
    
}
