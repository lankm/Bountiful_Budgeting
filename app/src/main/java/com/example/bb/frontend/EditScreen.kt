package com.example.bb.frontend

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bb.backend.User

@Preview
@Composable
fun PreviewEditScreen(){
    EditScreen(User.sample())
}

@Composable
fun EditScreen(u: User){

    Surface(
        modifier = Modifier.fillMaxWidth(),

    ) {
        Card (
            modifier = Modifier.height(300.dp),
            backgroundColor = Color.White
                ){

            Column() {
                Text(text = "Budget Selected Name")


            Row( ) {
                Card( modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp),
                    backgroundColor = Color.Red,) {
                    ColorPickerPopUp( onValueChange ={},onColorClicked = {})
                }
Spacer(modifier = Modifier.width(5.dp))
                Column(){
                    Text(text = "Name of Category ")
                    Text(text = "Income")
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "Name of Expense ")
                    Text(text = "Expense")

                    Text(text = "Name of Expense ")
                    Text(text = "Expense")

                    Text(text = "Name of Expense ")
                    Text(text = "Expense")

                    
                    Button(onClick = { navController.popBackStack(NavigationItem.Budget.route, false) }) {
                        
                    }
                }




            }



            }


        }

    }
}