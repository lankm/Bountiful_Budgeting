package com.example.bb.frontend

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bb.R

@Preview
@Composable
fun ReportScreen() {
    //look into makinga "top of app bar"
    /*
    Scaffold(){
        topBar = { TopAppBar(title = { Text("QQQ | Report Menu",
            color = androidx.compose.ui.graphics.Color.White) },
            backgroundColor = androidx.compose.ui.graphics.Color.Green) },
    }*/

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Select a Budget to generate a report",
            fontWeight = FontWeight.Bold,
            color = androidx.compose.ui.graphics.Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

        ButtonTest(        )
    }

}

@Composable
fun ButtonTest() {
    Button(onClick = {    }) {
        Text(text = "Generate Report",
        ) }
}

@Composable
fun DropdownMenu(){


}
