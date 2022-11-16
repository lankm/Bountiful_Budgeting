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

@Composable
fun SettingScreen(u: User) {
    val listState = rememberLazyListState()
    val options = getOptions()

    LazyColumn(
        state = listState
    ) {
        items(options) { opt ->
            Option.displayButton(opt.name, opt.location, u)
        }
    }
}

fun getOptions(): List<Option> {
    var options = ArrayList<Option>()

    options.add(Option("Alert Settings", 0))


    //add more if needed here.
    //make sure i is defined in the companion object

    return options
}

class Option {
    var name = ""
    var location = -1

    constructor(name: String, i: Int) {
        this.name = name
        location = i
    }

    //defines where each button goes
    companion object {
        @Composable
        fun displayButton(name: String, i: Int, u: User) {
            Button( onClick = {
                    if(i == 0)
                        navController.navigate("alert")
                    else if(i == 1){}
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
                Text(name)
            }
        }
    }
}