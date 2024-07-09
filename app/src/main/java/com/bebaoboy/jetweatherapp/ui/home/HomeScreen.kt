package com.bebaoboy.jetweatherapp.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (homeViewModel.homeState.isLoading) {
            CircularProgressIndicator()
        } else {
            homeViewModel.homeState.weather?.let {
                CurrentWeatherItem(
                    currentWeatherModel = it.currentWeatherModel,
                    modifier = Modifier.align(Alignment.TopCenter)
                )
                HourlyWeatherItem(
                    hourlyWeatherModel = it.hourlyWeatherModel,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                )
            }
            homeViewModel.homeState.dailyWeatherInfo?.let {
                DailyWeatherItem(
                    modifier = modifier.align(Alignment.Center),
                    dailyWeatherModel = it
                )
            }
            
        }
        
    }
}