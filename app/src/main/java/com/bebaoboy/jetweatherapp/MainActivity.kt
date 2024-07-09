package com.bebaoboy.jetweatherapp

import AppBar
import ExpandedTopBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.bebaoboy.jetweatherapp.ui.theme.JetWeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge(
        
        )
        setContent {
            JetWeatherAppTheme {
                ScaffoldLibrary()
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JetWeatherAppTheme {
        ScaffoldLibrary()
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScaffoldLibrary(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState()
) {
    val isCollapsed: Boolean = remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }.value
    
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            AppBar(modifier = modifier, isCollapsed = isCollapsed)
        },
    ) { innerPadding ->
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val boxWithConstraintsScope = this
                LazyColumn(
                    modifier = modifier
                        .padding(innerPadding),
                    state = listState,
                    
                    ) {
                    item { ExpandedTopBar(modifier = modifier.height(boxWithConstraintsScope.maxHeight - 100.dp)) }
                    items(count = 50) { i ->
                        ListItem(
                            
                            leadingContent = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Outlined.FavoriteBorder,
                                        contentDescription = "",
                                        tint = Color.Black
                                    )
                                }
                            },
                            modifier = Modifier
                                .clickable {
                                    // no way to get headlineContent and supportingContent here
                                }
                                .padding(vertical = 20.dp),
                            headlineContent = {
                                Text(
                                    text = stringResource(id = R.string.tab_home),
                                    style = TextStyle(fontSize = 16.sp)
                                )
                            },
                            supportingContent = {
                                Text(
                                    text = LocalContext.current.getString(R.string.app_name) + i.toString(),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                            },
                        )
                        
                        
                    }
                    
                }
            }
        }
    }
}

