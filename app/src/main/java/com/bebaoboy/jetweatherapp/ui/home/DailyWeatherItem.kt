package com.bebaoboy.jetweatherapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bebaoboy.jetweatherapp.R
import com.bebaoboy.jetweatherapp.domain.models.DailyWeatherModel
import com.bebaoboy.jetweatherapp.utils.Util

@Composable
fun DailyWeatherItem(
    modifier: Modifier = Modifier,
    dailyWeatherModel: DailyWeatherModel.DailyWeatherInfo
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
    ) {
        item {
            Row(modifier = Modifier.fillParentMaxWidth()) {
                SunRiseItem(
                    modifier = Modifier.weight(1f),
                    dailyWeatherModel = dailyWeatherModel
                )
                UVIndexItem(
                    modifier = Modifier.weight(1f),
                    dailyWeatherModel = dailyWeatherModel
                )
            }
        }
    }
}

@Composable
fun SunRiseItem(
    modifier: Modifier = Modifier,
    dailyWeatherModel: DailyWeatherModel.DailyWeatherInfo
) {
    Card(modifier = modifier.padding(horizontal = 8.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.sunrise),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = dailyWeatherModel.sunrise,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = "${stringResource(R.string.sunset)} ${dailyWeatherModel.sunset}",
                style = MaterialTheme.typography.headlineSmall
            )
            
        }
    }
}


@Composable
fun UVIndexItem(
    modifier: Modifier = Modifier,
    dailyWeatherModel: DailyWeatherModel.DailyWeatherInfo
) {
    Card(modifier = modifier.padding(horizontal = 8.dp)) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                
                text = stringResource(R.string.uv_index),
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = dailyWeatherModel.uvIndexMax.toString(),
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = dailyWeatherModel.weatherInfoItem.info,
                style = MaterialTheme.typography.headlineSmall
            )
            
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DailyWeatherItemPreview() {
    DailyWeatherItem(
        dailyWeatherModel = DailyWeatherModel.DailyWeatherInfo(
            sunset = "00:00",
            sunrise = "00:00",
            uvIndexMax = 6.75,
            weatherInfoItem = Util.getWeatherInfo(45),
            time = "00:00",
            temperature2mMax = 0.0,
            temperature2mMin = 0.0,
            windSpeed10mMax = 100.0,
            windDirection10mDominant = Util.getWindDirection(100.0)
        
        )
    )
}