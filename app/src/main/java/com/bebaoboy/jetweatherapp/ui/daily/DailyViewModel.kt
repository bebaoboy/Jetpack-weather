package com.bebaoboy.jetweatherapp.ui.daily

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bebaoboy.jetweatherapp.domain.models.DailyWeatherModel
import com.bebaoboy.jetweatherapp.domain.repository.WeatherRepository
import com.bebaoboy.jetweatherapp.utils.Response.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    var dailyState: DailyState by mutableStateOf(DailyState())
        private set
    
    init {
        viewModelScope.launch {
            repository.getWeather().collect { response ->
                when (response) {
                    is Response.Loading -> {
                        dailyState = dailyState.copy(isLoading = true)
                    }
                    
                    is Response.Success -> {
                        dailyState = dailyState.copy(
                            dailyWeatherInfo = response.data?.dailyWeatherModel,
                            isLoading = false
                        )
                    }
                    
                    is Response.Error -> {
                        dailyState = dailyState.copy(error = response.message, isLoading = false)
                    }
                }
            }
        }
    }
    
    
}

data class DailyState(
    val isLoading: Boolean = false,
    val dailyWeatherInfo: DailyWeatherModel? = null,
    val error: String? = null
)
