package com.example.bb.frontend

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Queue
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


sealed class NavigationItem(var route: String, var icon : ImageVector, var title: String) {
    object SplashScreen : NavigationItem(
        "splashScreen",
        Icons.Rounded.LineStyle,
        "splashScreen")

    object Budget : NavigationItem(
        "budget",
        Icons.Rounded.LineStyle.apply { tintColor.red },
        "Budget")

    object Calendar : NavigationItem(
        "calendar",
        Icons.Rounded.CalendarMonth ,
        "Calendar")

    object Report : NavigationItem(
        "reports",
        Icons.Rounded.AddChart,
        "Reports")

    object Settings : NavigationItem(
        "setting",
        Icons.Rounded.Settings,
        "Settings")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Budget,
        NavigationItem.Calendar,
        NavigationItem.Report,
        NavigationItem.Settings
    )

    val showBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in items.map { it.route }


    if(showBottomBar){
        BottomNavigation(
            //modifier = Modifier.clip(RoundedCornerShape(15.dp,15.dp,0.dp,0.dp)),
            backgroundColor = Color(0xFF006443),
            contentColor = Color(0xFFFFFFFF),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route



                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(text = item.title) },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(1f),
                        alwaysShowLabel = true,
                        selected = false,
                        onClick = {
                            navController.navigate(item.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                navController.graph.startDestinationRoute?.let { route ->
                                    popUpTo(route) {
                                        saveState = true
                                    }
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }

    }}


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    val navController = rememberNavController()


    Scaffold(
        // top bar

        bottomBar = { BottomNavigationBar(navController) },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController)
            }
        },
        backgroundColor = Color(180,180,180) // Set background color to avoid the white flashing when you switch between screens
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.SplashScreen.route) {



        composable(NavigationItem.SplashScreen.route) {
            AnimatedSplashBackGround(navController)
        }

        composable(NavigationItem.Budget.route) {
            BudgetScreen()
        }
        composable(NavigationItem.Calendar.route) {
            CalendarScreen()
        }
        composable(NavigationItem.Report.route) {
            ReportScreen()
        }
        composable(NavigationItem.Settings.route) {
            SettingScreen()
        }
    }
}


