package com.example.bb.frontend.Settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
        var budAlrt by remember { mutableStateOf(TextFieldValue(u.AlertSetting.budgetPercent.toString())) }
        var carAlrt by remember { mutableStateOf(TextFieldValue(u.AlertSetting.categoryPercent.toString())) }

        Button(onClick = {
            try {
                u.AlertSetting.budgetPercent = budAlrt.text.toDouble()
                u.AlertSetting.categoryPercent = carAlrt.text.toDouble()
            } catch (e: java.lang.Exception) {}

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

        Text("Overall Budget Alert",
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 0.dp)
                .height(20.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White)
        TextField(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp),
            value = budAlrt,
            onValueChange = { newText ->
                budAlrt = newText
            },
            colors = TextFieldDefaults.textFieldColors(textColor = Color.Black, backgroundColor = Color.White)
        )

        Text("Categorical Alert",
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 0.dp)
                .height(20.dp),
            fontWeight = FontWeight.Bold,
            color = Color.White)
        TextField(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp),
            value = carAlrt,
            onValueChange = { newText ->
                carAlrt = newText
            },
            colors = TextFieldDefaults.textFieldColors(textColor = Color.Black, backgroundColor = Color.White)
        )
    }


}