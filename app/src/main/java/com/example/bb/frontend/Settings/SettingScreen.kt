package com.example.bb.frontend.Settings

import android.app.LauncherActivity.ListItem
import android.graphics.Color
import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bb.R
import com.example.bb.backend.Budget
import com.example.bb.backend.Category
import com.example.bb.backend.Expense
import com.example.bb.backend.User
import com.example.bb.frontend.NavigationItem
import com.example.bb.frontend.navController
import com.example.bb.frontend.navLogin

@Composable
fun SettingScreen(u: User) {

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Button( onClick = {
            navController.navigate("alert")
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = White,
                contentColor = Black
            )
        ){
            Text("Alert Settings")
        }
        Button( onClick = {
            navLogin.navigate("login")
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = White,
                contentColor = Black
            )
        ){
            Text("Log Out")
        }
    }
}