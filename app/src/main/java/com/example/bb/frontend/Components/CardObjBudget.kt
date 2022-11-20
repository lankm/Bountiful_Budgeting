package com.example.bb.frontend.Components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bb.backend.Category
import com.example.bb.frontend.*
import com.example.bb.ui.theme.Shapes


@ExperimentalMaterialApi
@Composable
fun Boxes(

    name:String,income:String,index:Int,
    titleFontSize: TextUnit = MaterialTheme.typography.h6.fontSize,
    titleFontWeight: FontWeight = FontWeight.Medium,
    shape: Shape = CircleShape.copy(CornerSize(20.dp)),
    padding: Dp = 12.dp,

) {

    var colorsChoices = ColorsChoices.color1
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .animateContentSize(
                animationSpec =
                tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        onClick = {
            //navController.navigate("edit_screen/${index}")
            //expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                //ColorAction(onValueChange = {colorsChoices = colorSelected },onclicked = {})
                Card( modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp),
                backgroundColor = Color.Red,) {
                    //ColorPickerPopUp( onValueChange ={},onColorClicked = {})
                }
                Spacer(modifier = Modifier.width(10.dp))
                val total = "%.2f".format(income)
                Text(
                    modifier = Modifier
                        .weight(6f,true),
                    text = "${name}",
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    overflow= TextOverflow.Ellipsis,
                    )
                Spacer(modifier = Modifier.width(25.dp))



                    Text(
                        modifier = Modifier
                            .weight(2f,false),
                        text = "$${income}",
                        fontSize = titleFontSize,
                        fontWeight = FontWeight.Medium, //THIS CAN BE LIVE UPDATED LATER
                        maxLines = 1,
                        color = Color.Red
                        )


                Spacer(modifier = Modifier.width(5.dp))

            }


//            if (expandedState) {
//                Column() {
//                    Spacer(modifier = Modifier.height(15.dp))
//
//
//                    Text(text = "---------Test----------")
//                    Row (
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.End,
//
//                            ){
//
//                        }
//                    }
//                }



            }
        }
    }




