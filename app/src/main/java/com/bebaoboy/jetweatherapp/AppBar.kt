import android.widget.TextClock
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bebaoboy.jetweatherapp.ui.home.HomeScreen
import com.bebaoboy.jetweatherapp.ui.home.HomeState
import com.bebaoboy.jetweatherapp.ui.theme.JetWeatherAppTheme
import com.bebaoboy.jetweatherapp.ui.theme.Purple80
import com.bebaoboy.jetweatherapp.utils.Util

val COLLAPSED_TOP_BAR_HEIGHT = 100.dp
val EXPANDED_TOP_BAR_HEIGHT = 200.dp


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar(
    modifier: Modifier = Modifier,
    isCollapsed: Boolean,
    homeViewModelState: HomeState? = null
) {
    var isUpdatedDateShown by remember { mutableStateOf(false) }
    val color: Color by animateColorAsState(
        targetValue =
        if (isCollapsed)
            Purple80
        else
            MaterialTheme.colorScheme.primaryContainer, label = ""
    
    )
    Card(
        modifier
            .fillMaxWidth()
            .heightIn(max = COLLAPSED_TOP_BAR_HEIGHT + 20.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = (-1).dp)
    ) {
        Row(
            modifier
                .padding(16.dp)
                .height(COLLAPSED_TOP_BAR_HEIGHT),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            var text by remember { mutableStateOf("") }
            AnimatedVisibility(visible = isCollapsed) {
                TextField(
                    
                    placeholder = { Text("Search...") },
                    value = text,
                    onValueChange = { text = it },
                    //            label = { Text(text = "Search....", fontSize = 12.sp) },
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "Search"
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,                        //setting the text field background when it is focused
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        
                        //setting the text field background when it is unfocused or initial state
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                        
                        //                //setting the text field background when it is disabled
                        //                disabledContainerColor = MaterialTheme.colorScheme.d,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .height((COLLAPSED_TOP_BAR_HEIGHT))
                        .padding(top = 20.dp)
                )
            }
            if (isCollapsed) Spacer(modifier = Modifier.width(8.dp))
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder, contentDescription = "",
                    tint = Color.Black
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Outlined.Notifications, contentDescription = "",
                    tint = Color.Black
                )
            }
            if (!isCollapsed) Spacer(modifier = Modifier.weight(1f))
            
            AnimatedVisibility(visible = !isCollapsed && homeViewModelState?.weather?.currentWeatherModel?.time != null) {
                Button(
//                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    onClick = {
                        isUpdatedDateShown = !isUpdatedDateShown
                    }, modifier = Modifier
                        .weight(2f)
                        .align(Alignment.CenterVertically)
                ) {
                    if (isUpdatedDateShown) Text(
//                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        text = "Updated: ${
                            Util.formatNormalDate(
                                "MMM, dd HH:mm",
                                
                                homeViewModelState?.weather?.currentWeatherModel?.time!!,
                                unix = true
                            )
                        }"
                    )
                    else
                        AndroidView(factory = { context ->
                            TextClock(context).apply {
                                format12Hour?.let {
                                    this.format12Hour = "MMMM, dd HH:mm:ss"
                                }
                                format24Hour?.let {
                                    this.format24Hour = "MMMM, dd HH:mm:ss"
                                }
                                timeZone?.let { this.timeZone = it }
//                                textSize.let { this.textSize = 24f }
                            }
                        })
                }
                
            }
            
        }
        
        
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetWeatherAppTheme {
        AppBar(isCollapsed = true)
    }
}

@Composable
fun ExpandedTopBar(modifier: Modifier = Modifier, homeViewModelState: HomeState) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.BottomStart
    ) {
//        Text(
//            modifier = Modifier.padding(16.dp),
//            text = "Library",
//            style = MaterialTheme.typography.headlineLarge.copy(fontSize = 48.sp),
//        )
        HomeScreen(homeViewModelState = homeViewModelState)
    }
}