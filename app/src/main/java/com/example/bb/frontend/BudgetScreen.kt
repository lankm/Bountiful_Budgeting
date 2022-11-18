package com.example.bb.frontend

import android.os.Build
import android.util.Log

import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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







fun TestClassesDelLater(){

    Log.d("TAG", "TestClassesDelLater:ccccccc ${ Budget.sample().categories} + ${Budget.sample().getExpenses()}")
    Log.d("TAG", "TestClassesDelLater:vvvvvvv ${expenseToBudget} ")
    Log.d("TAG", "TestClassesDelLater:bbbbbbb ${ getExpense} ")
    Log.d("TAG", "TestClassesDelLater:aaaaaaa ${ Category.sample()} ")
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun BudgetScreen() {
    
    MainContent {
        IncomeComponent()





    }

}




@Composable
fun TestView(user: User){
    var budgetCard = user.budgets
    var budgetExpense = user.reports



    Surface() {
        for (x in budgetCard){
            Text(text = "$budgetCard" )
        }
        for (x in budgetExpense){
            Text(text = "$budgetExpense" )
        }


    }

}







@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Boxes(num:Int,budgetCategory: ArrayList<Category>) {
    //default values for testing function is moving to another package
    val expandedState  = remember{
        mutableStateOf(false)
    }

    var expandNum = remember {
        mutableStateOf(60.dp)
    }

    Card(
        modifier = Modifier
            .padding(4.dp)

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
            if (!expandedState.value) {
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

                Text(text = "${budgetCategory[num].name}")
                Spacer(modifier = Modifier.width(150.dp))
                Text(text = "$${budgetCategory[num].cap}")
                Spacer(modifier = Modifier.height(20.dp))







            }else{
                //fades out

                Surface(
                    modifier = Modifier
                        .size(150.dp)
                        .padding(12.dp),
                    elevation = 5.dp
                ) {
                    Column() {
                               //ExpensesShow( num,getExpenses)

                        }
                    }
                }

            }


        }



    }



    




@Composable
fun ExpensesShow(num: Int, budgetCategory: ArrayList<Expense> ){
     var number = num
    number = budgetCategory.size

    for (x in budgetCategory){
        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "${budgetCategory[number].name}")
            Spacer(modifier = Modifier.width(60.dp))
            Text(text = "${budgetCategory[number].cost}")
        }
        number--
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
fun ColumnManager(budgetCategoryView: ArrayList<Category>) {
    var num = budgetCategoryView.size

    Column(
        modifier = Modifier.padding(12.dp)
    ) {



               //Boxes(num,getCategor)

           }

        }


























