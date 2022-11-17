package com.example.bb.frontend

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bb.backend.User

sealed class NavigationLogin(var route: String, var title: String) {
    // four main pages
    object LoginPage : NavigationLogin("login", "Logged Out")
    object MainPage : NavigationLogin("main", "Logged In")
}

lateinit var navLogin: NavHostController

@Composable
fun Login() {
    navLogin = rememberNavController()

    var u = User.sample()
    NavHost(navLogin, startDestination = NavigationLogin.LoginPage.route) {
        // These are the 4 main pages
        composable(NavigationLogin.LoginPage.route) {
            LoginScreen()
        }
        composable(NavigationLogin.MainPage.route) {
            MainScreen(u)
        }
    }
}

@Composable
fun LoginScreen() {
    Button(
        onClick = {
            navLogin.navigate("main")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Text("Log In")
    }
}