package com.bebaoboy.jetweatherapp.ui.daily

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bebaoboy.jetweatherapp.domain.models.DailyWeatherModel.DailyWeatherInfo
import com.bebaoboy.jetweatherapp.ui.home.degreeTxt

@Composable
fun DailyWeatherInfoItem(
    modifier: Modifier = Modifier,
    dailyWeatherInfo: DailyWeatherInfo,
    backgroundColor: Color,
    onItemClick: () -> Unit
) {
    Card(
        onClick = onItemClick, modifier = Modifier.padding(8.dp), colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "${dailyWeatherInfo.temperature2mMin} $degreeTxt",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = "${dailyWeatherInfo.temperature2mMax} $degreeTxt",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                painter = painterResource(id = dailyWeatherInfo.weatherInfo.icon),
                contentDescription = null
            )
            Text(
                text =
                dailyWeatherInfo.time,
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            
        }
    }
}
