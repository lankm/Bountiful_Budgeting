package com.example.bb

import android.os.Build
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi

import androidx.compose.foundation.layout.*

import androidx.compose.material.*



import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier



import com.example.bb.backend.*

import com.example.bb.frontend.MainScreen

import com.example.bb.ui.theme.BountifulBudgetingTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "$name")
}

//@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BountifulBudgetingTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

            var b = Budget.sample() //showAll()
            var c = Category.sample() //showAll()
            var e = Expense.sample() // just toString()

            Column() {
                Greeting(b.toString())
                Greeting(c.toString())
                Greeting(e.toString())


            }
        }
    }
}


