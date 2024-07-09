package com.bebaoboy.jetweatherapp.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bebaoboy.jetweatherapp.domain.models.DailyWeatherModel
import com.bebaoboy.jetweatherapp.domain.models.WeatherModel
import com.bebaoboy.jetweatherapp.domain.repository.WeatherRepository
import com.bebaoboy.jetweatherapp.utils.Response.Response
import com.bebaoboy.jetweatherapp.utils.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    var homeState by mutableStateOf(HomeState())
        private set
    
    init {
        viewModelScope.launch {
            weatherRepository.getWeather().collect { response ->
                homeState = when (response) {
                    is Response.Loading -> {
                        homeState.copy(isLoading = true)
                    }
                    
                    is Response.Success -> {
                        homeState.copy(
                            weather = response.data,
                            isLoading = false,
                            error = null,
                            dailyWeatherInfo =
                            response.data?.dailyWeatherModel?.dailyWeatherInfo?.find {
                                Util.isTodayDate(it.time)
                            })
                    }
                    
                    is Response.Error -> {
                        homeState.copy(error = response.message, isLoading = true)
                    }
                }
                Log.d("MONGOCODE", homeState.toString())
                
            }
        }
    }
}

data class HomeState(
    val weather: WeatherModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val dailyWeatherInfo: DailyWeatherModel.DailyWeatherInfo? = null
)