package com.example.bb.frontend

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bb.R
import com.example.bb.backend.User
import com.example.bb.frontend.Settings.AddEditScreen
import com.example.bb.frontend.Settings.AlertScreen
import com.example.bb.frontend.Settings.SettingScreen

sealed class NavigationItem(var route: String, var icon : ImageVector, var title: String) {
    // four main pages
    object Budget : NavigationItem("budget", Icons.Rounded.LineStyle, "Budget")
    object Calendar : NavigationItem("calendar", Icons.Rounded.CalendarMonth , "Calendar")
    object Report : NavigationItem("reports", Icons.Rounded.AddChart, "Reports")
    object Settings : NavigationItem("setting", Icons.Rounded.Settings, "Settings")

    // any other subpages
    object AlertSettings : NavigationItem("alert", Icons.Rounded.Settings, "Alert")
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem.Budget,
        NavigationItem.Calendar,
        NavigationItem.Report,
        NavigationItem.Settings
    )
    BottomNavigation(
        backgroundColor = Color(0,140,0),
        contentColor = Color.White,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title)},
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
    }
}

lateinit var navController: NavHostController
@Composable
fun MainScreen(u: User) {
    navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar(
            backgroundColor = Color(0,140,0),
            contentColor = Color.White)
        {
            
            Row() {
                Text(
                    text = "Bountiful Budgeting",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                
                Spacer(modifier = Modifier.width(16.dp))

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Search Task",
                        tint = Color.Black
                    )
                }
                
                //(imageVector = Icons.Filled.Add , contentDescription = "Add String")
                
                
            }
            
            
            
        }},
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController, u)
            }
        },
        backgroundColor = Color(50,100,50) // Set background color to avoid the white flashing when you switch between screens
    )
}

@Composable
fun Navigation(navController: NavHostController, u: User) {
    NavHost(navController, startDestination = NavigationItem.Budget.route) {
        // These are the 4 main pages
        composable(NavigationItem.Budget.route) {
            //BudgetScreen()
        }
        composable(NavigationItem.Calendar.route) {
            CalendarScreen(u)
        }
        composable(NavigationItem.Report.route) {
            ReportScreen(u)
        }
        composable(NavigationItem.Settings.route) {
            SettingScreen(u)
        }

        // These are any other pages that we create
        composable(NavigationItem.AlertSettings.route) {
            AlertScreen(u)
        }

        // ADD - EDIT BUDGET
        composable(NavigationItem.AlertSettings.route) {
            AddEditScreen(u)
        }


    }
}
