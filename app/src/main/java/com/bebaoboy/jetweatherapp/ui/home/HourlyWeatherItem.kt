package com.bebaoboy.jetweatherapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bebaoboy.jetweatherapp.R
import com.bebaoboy.jetweatherapp.domain.models.HourlyWeatherModel
import com.bebaoboy.jetweatherapp.utils.Util
import java.util.Date

@Composable
fun HourlyWeatherItem(modifier: Modifier = Modifier, hourlyWeatherModel: HourlyWeatherModel) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.today),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = Util.formatNormalDate("MMMM, dd", Date().time),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        
        HorizontalDivider(
            thickness = 2.dp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        LazyRow(modifier = modifier.padding(30.dp)) {
            items(hourlyWeatherModel.hourlyWeatherInfo) {
                HourlyWeatherInfoItem(
                    hourlyWeatherInfoItem = it
                )
            }
        }
        
    }
    
}

@Composable
fun HourlyWeatherInfoItem(
    modifier: Modifier = Modifier,
    hourlyWeatherInfoItem: HourlyWeatherModel.HourlyWeatherInfo
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${hourlyWeatherInfoItem.temperature2m} $degreeTxt",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            painter = painterResource(id = hourlyWeatherInfoItem.weatherInfo.icon),
            contentDescription = null
        )
        Text(
            text =
            hourlyWeatherInfoItem.time,
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        
    }
}

@Preview(showBackground = true)
@Composable
private fun HourlyRow() {
    HourlyWeatherItem(
        hourlyWeatherModel = HourlyWeatherModel(
            time = listOf(
                "00:00",
                "00:00",
                "00:00",
                "00:00",
                "00:00",
                "00:00",
                "00:00",
            ),
            temperature2m = listOf(
                23.0,
                23.0,
                23.0,
                23.0,
                23.0,
                23.0,
                23.0,
            ),
            weatherInfo = listOf(
                Util.getWeatherInfo(1),
                Util.getWeatherInfo(2),
                Util.getWeatherInfo(3),
                Util.getWeatherInfo(3),
                Util.getWeatherInfo(80),
                Util.getWeatherInfo(81),
                Util.getWeatherInfo(82),
            )
        )
    )
}