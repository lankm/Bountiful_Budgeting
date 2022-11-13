package com.example.bb.frontend

import android.os.Build

import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.rounded.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bb.backend.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle






@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun BudgetScreen() {
    MainContent {
        IncomeComponent()
        ColumnManager(user = User.sample())



    }

}








@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Boxes(budget: Budget) {
    //default values for testing function is moving to another package
    val expandedState  = remember{
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .height(60.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    expandedState.value = !expandedState.value
                    //EXPAND WINDOW
                },
                onLongClick = {
                    //POP UP WINDOWS TO EDIT
                },
            ),



        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        elevation = 6.dp
    ) {


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {

                Surface(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(12.dp),
                    elevation = 5.dp
                ) {
                    Card(
                        modifier = Modifier.clickable {

                        },
                        backgroundColor = Color.Red,
                        shape = CircleShape
                    ) {

                    }

                }

                Text(text = "${budget.name}")
                Spacer(modifier = Modifier.width(150.dp))
                Text(text = "$${budget.income}")
                Spacer(modifier = Modifier.height(20.dp))
            }

        }

    if (expandedState.value) {

        Column() {
            //LazyColumn(content = )
            Text(text = "Test")
            Text(text = "Test")
            Text(text = "Test")


        }
    }else{
        //fades out
        Box(){

        }
    }

}








@RequiresApi(Build.VERSION_CODES.O) //this needs to change it came from some calendar API i found
//@Preview(showBackground = true)
@Composable

fun IncomeComponent () { //THIS COMPOSABLE IS GOING TO MOVE LOCATIONS //Proper NAME CHANGE LATER
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        color = MaterialTheme.colors.surface,
    ) {
        Column(
            // verticalArrangement = Arrangement.Center,
            modifier =  Modifier.padding(top = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Expenses", //change dynamically
                style =
                MaterialTheme.typography.h5,
                fontWeight = FontWeight.ExtraBold
            )

            Text(
                text = Budget.sample().name, //change dynamically
                style =
                MaterialTheme.typography.subtitle2
            )
            //Local Date Format better or change later
            //ALL OF THIS IS GOING TO A FUNCTION LATER SOMWHERE ELSE
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            val formatted = current.format(formatter)
            Text(text = "${formatted}")


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(45.dp),
                    imageVector = Icons.Rounded.Money,
                    contentDescription = "MoneyIcon")

                Spacer(modifier = Modifier.width(10.dp))



                Text(
                    text = "$${Budget.sample().income}", //this will change dynamically later
                    style =
                    MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )

            }
            Spacer(modifier = Modifier.height(10.dp))

            Card(
                modifier= Modifier
                    .fillMaxWidth()
                    .height(7.dp)
                    .padding(horizontal = 30.dp),
                shape = CircleShape.copy(CornerSize(16.dp)),
                backgroundColor = Color.LightGray,
                elevation = 16.dp,
                border = BorderStroke(1.dp, Color.Black)

            )
            {
                Row(horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(1.dp))
                    Card(  //This will change according to the number of categories, this will change later
                        modifier = Modifier //You may try to implement logic to divide and add colors, or ima do it later for now it for looks
                            .width(16.dp)
                            .height(3.dp),
                        shape = RectangleShape,
                        backgroundColor = Color.Green) {


                    }

                    Card(
                        modifier = Modifier
                            .width(16.dp)
                            .height(3.dp),
                        shape = RectangleShape,
                        backgroundColor = Color.Red) {


                    }

                    Card(
                        modifier = Modifier
                            .width(16.dp)
                            .height(3.dp),
                        shape = RectangleShape,
                        backgroundColor = Color.Blue) {


                    }
                }

            }
            Spacer(modifier = Modifier.height(25.dp))

        }



    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainContent(content: @Composable () -> Unit){
    Column() {
        content()

    }
}


@Composable
fun ColumnManager( user: User) {
    Column(
        modifier = Modifier.padding(12.dp)
    ) {
        LazyColumn {
            items(user.budgets) {
                item ->
                Boxes(item)




            }
        }

    }
}

@Composable
fun Categories(category: Category){
    Column() {
        Row() {
            Text(text = "category")
        }
    }

}



@Composable
fun CategoriesColumns(budget: Budget){
    Column() {

    }

}



















