package com.bebaoboy.jetweatherapp.ui.daily

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bebaoboy.jetweatherapp.R
import com.bebaoboy.jetweatherapp.ui.home.SunRiseItem
import com.bebaoboy.jetweatherapp.ui.home.UVIndexItem
import com.bebaoboy.jetweatherapp.ui.home.degreeTxt

@SuppressLint("LogConditional")
@Composable
fun DailyScreen(modifier: Modifier = Modifier, dailyViewModel: DailyViewModel = hiltViewModel()) {
    val dailyState: DailyState = dailyViewModel.dailyState
    var selectedWeatherIndex by remember {
        mutableIntStateOf(0)
    }
    var currentDailyWeatherItem = remember(key1 = selectedWeatherIndex, key2 = dailyState) {
        dailyState.dailyWeatherInfo?.dailyWeatherInfo?.get(selectedWeatherIndex)
    }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        when (dailyState.isLoading) {
            true -> {
                CircularProgressIndicator()
            }
            
            else -> {
                
                currentDailyWeatherItem?.let {
                    Text(
                        text = buildString {
                            append(stringResource(R.string.temperature_short))
                            append(": ")
                            append(it.temperature2mMin)
                            append(" - ")
                            append(it.temperature2mMax)
                            append(" ")
                            append(degreeTxt)
                        },
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "7 Days Forecast",
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow {
                    dailyState.dailyWeatherInfo?.let {
                        itemsIndexed(items = it.dailyWeatherInfo) { index, weatherInfo ->
                            val selectedColor =
                                if (selectedWeatherIndex == index)
                                    MaterialTheme.colorScheme.inverseSurface
                                else
                                    CardDefaults.cardColors().containerColor
                            DailyWeatherInfoItem(modifier = modifier,
                                dailyWeatherInfo = weatherInfo,
                                backgroundColor = selectedColor,
                                onItemClick = {
                                    selectedWeatherIndex = index
                                })
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                
                currentDailyWeatherItem?.let {
                    Card {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.wind_ic),
                                    contentDescription = null
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "WIND",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = it.windSpeed10mMax.toString() + " Km/h " + it.windDirection10mDominant,
                                style = MaterialTheme.typography.headlineMedium,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
                    SunRiseItem(dailyWeatherModel = it)
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    UVIndexItem(dailyWeatherModel = it)
//                    }
                }
                
            }
        }
        
    }
    
}
