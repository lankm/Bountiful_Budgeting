package com.example.bb.frontend

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import android.os.Build
import android.util.Log

import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.rounded.Money
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bb.backend.*
import com.example.bb.frontend.Components.Boxes
import com.example.bb.ui.theme.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


fun TestClassesDelLater(){


}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun BudgetScreen() {
    //SELECTED FUNCTION NOT MADE YET, MAYBE IF WE GET TIME\
    // val colorPick = mutableListOf()

    var u = User.users()
    var selectedBudget by remember{ mutableStateOf(0)}
    var categoryList = mutableListOf( u[0].budgets[selectedBudget].categories )


    //TOP IS FOR TESTING
    
    
    var b = currentUser
    
    MainContent {
        IncomeComponent(u[0])
        ColumnManager(categoryList[0])
        Spacer(modifier = Modifier.height(10.dp))






    }

}











@Composable
fun ColorAction(
    onValueChange : () -> Unit,
    onclicked: (ColorsChoices) -> Unit
){
    ColorPickerPopUp(onValueChange = onValueChange ,onColorClicked = onclicked)
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

    }



}




@RequiresApi(Build.VERSION_CODES.O) //this needs to change it came from some calendar API i found
//@Preview(showBackground = true)
@Composable

fun IncomeComponent (u:User) { //THIS COMPOSABLE IS GOING TO MOVE LOCATIONS //Proper NAME CHANGE LATER
    

    
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



@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnManager(budgetCategoryView: ArrayList<Category>) {
    var num = 0

    Column(
        modifier = Modifier.padding(12.dp)
    ) {




        LazyColumn{

            items(budgetCategoryView){ index ->
                //Boxes(index)
                Boxes(index, onItemClick = {})
                Log.d("TAG", "ColumnManager: ${index}")
            }
        }

        

           }

        }


























