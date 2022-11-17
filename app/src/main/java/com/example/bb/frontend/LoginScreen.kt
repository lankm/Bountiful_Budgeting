package com.example.bb.frontend

import android.graphics.Paint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
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
var currentUser = User.users()[0]

// just navigates between being logged in or not
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Login() {
    navLogin = rememberNavController()

    //start as logged out
    NavHost(navLogin, startDestination = NavigationLogin.LoginPage.route) {
        // These are the 4 main pages
        composable(NavigationLogin.LoginPage.route) {
            LoginScreen()
        }
        composable(NavigationLogin.MainPage.route) {
            MainScreen(currentUser)
        }
    }
}


// This is the login page. Change this to actually do stuff
// Sorry juan for this abomination
// redo later if time permits but its good enough for now. probably start from scratch
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen() {
    Scaffold(topBar = { TopAppBar(
        backgroundColor = Color(0,140,0),
        contentColor = Color.White) {
        Text(
            text = "Bountiful Budgeting",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )}},
        backgroundColor = Color(50,100,50))
        {

        //************************************************
        Column(modifier = Modifier.fillMaxWidth()) {
            //just spacing lol
            Text("", modifier = Modifier.height(50.dp))
            //******
            Text(
                "User",
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 0.dp)
                    .height(20.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )

            val options = User.users()

            var expanded by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf(options[0].name) }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                TextField(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp),
                    readOnly = true,
                    value = selectedOptionText,
                    onValueChange = { },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(textColor = Color.Black, backgroundColor = Color.White)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                currentUser = selectionOption
                                selectedOptionText = selectionOption.name
                                expanded = false
                            }
                        ) {
                            Text(text = selectionOption.name)
                        }
                    }
                }
            }
            //*******************************************************************
            var passwordText by remember { mutableStateOf(TextFieldValue()) }
            Text(
                "Password",
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 0.dp)
                    .height(20.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            TextField(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp),
                value = passwordText,
                onValueChange = { newText ->
                    passwordText = newText
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White
                )
            )
            //***********************************************************************
            //just spacing lol
            Text("", modifier = Modifier.height(50.dp))
            //********
            Button(
                onClick = {
                    if (currentUser.login(passwordText.text))
                        navLogin.navigate("main")
                    else {
                        //something
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
                    .width(100.dp)
                    .align(Alignment.End),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                )
            ) {
                Text("Log In")
            }
        }
    }
}

