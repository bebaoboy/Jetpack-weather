package com.bebaoboy.jetweatherapp.di

import com.bebaoboy.jetweatherapp.data.remote.models.ApiCurrentWeatherModel
import com.bebaoboy.jetweatherapp.data.remote.models.ApiDailyWeatherModel
import com.bebaoboy.jetweatherapp.data.remote.models.ApiHourlyWeatherModel
import com.bebaoboy.jetweatherapp.data.remote.models.ApiWeatherModel
import com.bebaoboy.jetweatherapp.domain.mappers.ApiMapper
import com.bebaoboy.jetweatherapp.domain.mappers_impl.ApiCurrentWeatherMapper
import com.bebaoboy.jetweatherapp.domain.mappers_impl.ApiDailyMapper
import com.bebaoboy.jetweatherapp.domain.mappers_impl.ApiHourlyMapper
import com.bebaoboy.jetweatherapp.domain.mappers_impl.ApiWeatherMapper
import com.bebaoboy.jetweatherapp.domain.models.CurrentWeatherModel
import com.bebaoboy.jetweatherapp.domain.models.DailyWeatherModel
import com.bebaoboy.jetweatherapp.domain.models.HourlyWeatherModel
import com.bebaoboy.jetweatherapp.domain.models.WeatherModel
import com.bebaoboy.jetweatherapp.utils.ApiCurrentWeatherMapperAnnotation
import com.bebaoboy.jetweatherapp.utils.ApiDailyMapperAnnotation
import com.bebaoboy.jetweatherapp.utils.ApiHourlyMapperAnnotation
import com.bebaoboy.jetweatherapp.utils.ApiWeatherMapperAnnotation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @ApiDailyMapperAnnotation
    @Provides
    fun provideApiDailyMapper(): ApiMapper<DailyWeatherModel, ApiDailyWeatherModel> =
        ApiDailyMapper()
    
    @ApiHourlyMapperAnnotation
    @Provides
    fun provideApiHourlyMapper(): ApiMapper<HourlyWeatherModel, ApiHourlyWeatherModel> =
        ApiHourlyMapper()
    
    @ApiCurrentWeatherMapperAnnotation
    @Provides
    fun provideApiCurrentWeatherMapper(): ApiMapper<CurrentWeatherModel, ApiCurrentWeatherModel> =
        ApiCurrentWeatherMapper()
    
    @ApiWeatherMapperAnnotation
    @Provides
    fun provideApiWeatherMapper(
        appDailyMapper: ApiMapper<DailyWeatherModel, ApiDailyWeatherModel>,
        appHourlyMapper: ApiMapper<HourlyWeatherModel, ApiHourlyWeatherModel>,
        appCurrentWeatherMapper: ApiMapper<CurrentWeatherModel, ApiCurrentWeatherModel>
    ): ApiMapper<WeatherModel, ApiWeatherModel> = ApiWeatherMapper(
        appHourlyMapper = appHourlyMapper,
        appCurrentWeatherMapper = appCurrentWeatherMapper,
        appDailyMapper = appDailyMapper
    )
}