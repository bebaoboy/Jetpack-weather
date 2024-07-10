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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.bebaoboy.jetweatherapp.ui.daily.DailyScreen
import com.bebaoboy.jetweatherapp.ui.home.HomeViewModel
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
    listState: LazyListState = rememberLazyListState(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val isCollapsed: Boolean by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    
    
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            JetWeatherBottomNavigationBar(
                tabs = Tabs.entries,
                selectedIndex = selectedTabIndex
            ) {
                selectedTabIndex = it
            }
        },
        topBar = {
            AppBar(
                modifier = modifier,
                isCollapsed = isCollapsed,
                homeViewModelState = homeViewModel.homeState
            )
        },
    ) { innerPadding ->
        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val boxWithConstraintsScope = this
                when (selectedTabIndex) {
                    0 -> {
                        LazyColumn(
                            modifier = modifier
                                .padding(innerPadding),
                            state = listState,
                            
                            ) {
                            item {
                                ExpandedTopBar(
                                    modifier = modifier.height(boxWithConstraintsScope.maxHeight - 100.dp),
                                    homeViewModelState = homeViewModel.homeState
                                )
                            }
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
                                            text = stringResource(R.string.app_name) + i.toString(),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                        )
                                    },
                                )
                                
                                
                            }
                            
                        }
                    }
                    
                    1 -> {
                        DailyScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
                
            }
        }
    }
}


@Composable
fun JetWeatherBottomNavigationBar(
    modifier: Modifier = Modifier,
    tabs: List<Tabs>,
    selectedIndex: Int,
    onSelectedChange: (Int) -> Unit
) {
    NavigationBar(modifier = modifier) {
        tabs.forEachIndexed { index, tabs ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { onSelectedChange(index) },
                icon = { Icon(imageVector = tabs.icon, contentDescription = tabs.title) },
                label = {
                    Text(text = tabs.title)
                }
            )
        }
    }
}


enum class Tabs(
    val title: String,
    val icon: ImageVector
) {
    HOME(
        title = "Home",
        icon = Icons.Default.Home
    ),
    DAILY(
        title = "Daily",
        icon = Icons.Default.DateRange
    )
}
