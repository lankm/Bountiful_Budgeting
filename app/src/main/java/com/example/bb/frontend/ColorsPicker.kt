package com.example.bb.frontend

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bb.ui.theme.*

// THIS IS FOR DROPDOWN LATER IMPLEMENTATION

enum class ColorsChoices(val color: Color) {

    color1(RedOrange),
    color2(RedPink),
    color3(BabyBlue),
    color4(Violet),

    color7(red),
    color8(green),
    color9(yellow),

}
var colorSelected: ColorsChoices = ColorsChoices.color7


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColorPickerPopUp(
    onValueChange : () -> Unit,
    onColorClicked :  (ColorsChoices) -> Unit
){
   var colorSelected by remember {
       mutableStateOf(ColorsChoices.color2)
   }
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = Modifier.clip(CircleShape),backgroundColor = colorSelected.color, onClick =  { expanded = true}) {

    }

//    IconButton(onClick = { expanded = true}) {
//        Icon(
//            imageVector = Icons.Filled.Circle,
//            contentDescription = "",
//            tint = colorSelected.color
//        )
//    }



    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }) {

        DropdownMenuItem(

            onClick = {
                onColorClicked(ColorsChoices.color2)
                expanded = false }) {
            ColorItem(colorPicked = ColorsChoices.color2)
            colorSelected=ColorsChoices.color2
        }
        DropdownMenuItem(
            onClick = {
                onColorClicked(ColorsChoices.color3)
                expanded = false }) {
            ColorItem(colorPicked = ColorsChoices.color3)
            colorSelected=ColorsChoices.color3
        }

        DropdownMenuItem(
            onClick = {
                onColorClicked(ColorsChoices.color4)
                expanded = false }) {
            ColorItem(colorPicked = ColorsChoices.color4)
            colorSelected=ColorsChoices.color4
        }

        DropdownMenuItem(
            onClick = {
                onColorClicked(ColorsChoices.color9)
                expanded = false }) {
            ColorItem(colorPicked = ColorsChoices.color9)
            colorSelected = ColorsChoices.color9
        }
    }

}



@Composable
fun ColorItem(colorPicked: ColorsChoices) {
    Row (

        verticalAlignment = Alignment.CenterVertically
    ){
        Canvas(modifier = Modifier.size(25.dp)){
            drawCircle(color = colorPicked.color)
        }
        Text(
            modifier = Modifier.padding(start =16.dp ),
            text = colorPicked.name,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSurface)
    }
}

@Preview
@Composable
fun ColorItemPreview(){
    Column() {
        ColorItem(colorPicked = ColorsChoices.color2)
        ColorItem(colorPicked = ColorsChoices.color3)
        //ColorItem(colorPicked = ColorsChoices.color5)
    }

}


