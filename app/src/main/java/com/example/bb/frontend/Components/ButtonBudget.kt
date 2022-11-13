package com.example.bb.frontend.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape

@Composable
fun AddBudget(onItemClick: () -> Unit = {}){
    Card(
        modifier = Modifier.clickable {

        },
        shape = RectangleShape


    ) {
        Box(){
            Icons.Rounded.Add
        }

    }
}