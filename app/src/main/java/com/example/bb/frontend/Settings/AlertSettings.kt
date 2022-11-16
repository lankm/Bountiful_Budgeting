package com.example.bb.frontend.Settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.bb.backend.User
import com.example.bb.frontend.navController

@Composable
fun AlertScreen(u: User) {
    Column() {
        var budAlrt by remember { mutableStateOf(TextFieldValue("")) }
        var carAlrt by remember { mutableStateOf(TextFieldValue("")) }

        Button(onClick = {



            navController.navigate("setting")
        },
            modifier = Modifier
                .padding(16.dp)
                .height(35.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        ){
            Text("Save & Exit")
        }

        Text("Overall Budget Alert")
        TextField(
            value = budAlrt,
            onValueChange = { newText ->
                budAlrt = newText
            }
        )

        Text("Categorical Alert")
        TextField(
            value = budAlrt,
            onValueChange = { newText ->
                budAlrt = newText
            }
        )
    }


}