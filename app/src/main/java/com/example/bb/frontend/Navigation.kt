package com.example.bb.frontend

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bb.backend.User
import com.example.bb.frontend.Settings.AlertScreen
//import com.example.bb.frontend.Settings.AlertScreen
import com.example.bb.frontend.Settings.SettingScreen

sealed class NavigationItem(var route: String, var icon : ImageVector, var title: String) {
    object Budget : NavigationItem("budget", Icons.Rounded.LineStyle, "Budget")
    object Calendar : NavigationItem("calendar", Icons.Rounded.CalendarMonth , "Calendar")
    object Report : NavigationItem("reports", Icons.Rounded.AddChart, "Reports")
    object Settings : NavigationItem("setting", Icons.Rounded.Settings, "Settings")

    object AlertSettings : NavigationItem("alert", Icons.Rounded.Settings, "Alert")
    object AddBudgetScreen : NavigationItem("addBudget", Icons.Rounded.Add, "AddBudget")
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
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(u: User) {
    navController = rememberNavController()

    Scaffold(
        topBar = { DefaultListAppBar(){ navController.navigate("addBudget")} },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController, u)
            }
        },
        backgroundColor = Color(50,100,50) // Set background color to avoid the white flashing when you switch between screens
    )




}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController, u: User) {
    NavHost(navController, startDestination = NavigationItem.Budget.route) {
        composable(NavigationItem.Budget.route) {
            bool= true
            BudgetScreen(navController, bucketViewModel)
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

        composable(NavigationItem.AlertSettings.route) {
            AlertScreen(u)
        }

        composable(NavigationItem.AddBudgetScreen.route) {
            AddBudgets(u)
        }

        //passing index to find data
        //allows us to edit too
        //if -1 = NEW_PAGE
        composable("edit_screen/{index}", arguments = listOf(navArgument("index")
        {type = NavType.IntType})) {
            backStackEntry ->
            bool= true
            EditScreen(u , navController, bucketViewModel, backStackEntry.arguments?.getInt("index"))
        }

    }
}





@Composable
fun DefaultListAppBar(
    onAddClicked: () -> Unit){
    TopAppBar(

        backgroundColor = Color.White,

        ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "List", color = Color.White)
            Spacer(modifier = Modifier.width(300.dp))
            ListAppBarActions( onAddClicked = onAddClicked)

        }

    }
}

@Composable
fun ListAppBarActions(
    onAddClicked:()  -> Unit
){

   AddAction(onAddClicked = onAddClicked)

}

@Composable
fun AddAction(
    onAddClicked:()  -> Unit
){
    IconButton(onClick = { onAddClicked() }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Button",
            tint = Color.Black
        )
    }
}