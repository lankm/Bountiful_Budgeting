package com.example.bb.frontend.Components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Preview
//@Composable
//fun test(){
//    val a = remember{
//        mutableStateOf("")
//    }
//    val validState = remember(a.value) {
//        a.value.trim().isNotEmpty()
//    }
//
//    Surface(
//        modifier = Modifier
//            .padding(2.dp)
//            .fillMaxWidth(),
//        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
//        border = BorderStroke(width = 1.dp, color = Color.LightGray),
//        elevation = 2.dp
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(6.dp)
//                .animateContentSize(),
//            verticalArrangement = Arrangement.Top,
//            horizontalAlignment = Alignment.CenterHorizontally,
//
//
//            ) {
//            InputField(
//                valueState = a ,
//                labelId = "Enter Bill", //Enter Amount
//                enabled = true ,
//                isSingleLine = true,
//                onAction = KeyboardActions{
//                    if(!validState) return@KeyboardActions
//
////                    onValChange( a.value.trim() ) //why?
////                    keyboardController?.hide()
//
//
//
//                }
//            )
//}


// TO INPUT NUMBERS
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Number, //INPOT NBUMBER ONLY
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value, //Whatever VALUE IS INPUTTED
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        leadingIcon = { Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = "MoneyIcon") },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction
    ) // IS PASSED TO THE VALUE STATE

}

// TO INPUT NUMBERS
@Composable
fun InputFieldText(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine: Boolean,
    keyboardType: KeyboardType = KeyboardType.Text, //INPOT NBUMBER ONLY
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = valueState.value, //Whatever VALUE IS INPUTTED
        onValueChange = { valueState.value = it },
        label = { Text(text = labelId) },
        leadingIcon = { Icon(imageVector = Icons.Rounded.MonetizationOn, contentDescription = "MoneyIcon") },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        ),
        modifier = modifier.padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        enabled = enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = onAction
    ) // IS PASSED TO THE VALUE STATE

}