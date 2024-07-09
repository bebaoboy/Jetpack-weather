package com.bebaoboy.jetweatherapp.ui.home

import android.icu.text.DateFormat
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bebaoboy.jetweatherapp.R
import com.bebaoboy.jetweatherapp.domain.models.CurrentWeatherModel
import com.bebaoboy.jetweatherapp.utils.Util
import java.util.Date

const val degreeTxt = "°"

@Composable
fun CurrentWeatherItem(modifier: Modifier = Modifier, currentWeatherModel: CurrentWeatherModel) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = modifier.size(120.dp),
            painter = painterResource(id = currentWeatherModel.weatherInfo.icon),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = buildString {
                append(currentWeatherModel.temperature)
                append(" ")
                append(degreeTxt)
                append(" C")
            },
            style = MaterialTheme.typography.displayMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = buildString {
                append(LocalContext.current.getString(R.string.weather_status))
                append(": ")
                append(currentWeatherModel.weatherInfo.info)
            },
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = buildString {
                append(LocalContext.current.getString(R.string.weather_wind_speed))
                append(": ")
                append(currentWeatherModel.windSpeed)
                append(" Km/h")
            },
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CurrentWeatherItemPreview() {
    CurrentWeatherItem(
        currentWeatherModel = CurrentWeatherModel(
            windSpeed = 12.0,
            temperature = 28.3,
            windDirection = "",
            isDay = true,
            time = Util.formatNormalDate(DateFormat.ABBR_WEEKDAY, Date().time),
            weatherInfo = Util.getWeatherInfo(1)
        )
    )
}