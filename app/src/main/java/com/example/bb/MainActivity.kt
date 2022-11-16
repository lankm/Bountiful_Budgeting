package com.example.bb

import android.os.Build
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
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
import com.example.bb.frontend.BottomNavigationBar
import com.example.bb.frontend.MainScreen
import com.example.bb.frontend.NavigationItem
import com.example.bb.ui.theme.BountifulBudgetingTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.math.exp

open class MainActivity : ComponentActivity() {
    var user: User = User.sample()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(user)
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


@RequiresApi(Build.VERSION_CODES.O) //this needs to change it came from some calendar API i found
@Preview(showBackground = true)
@Composable

fun IncomeComponent () { //THIS COMPOSABLE IS GOING TO MOVE LOCATIONS //Proper NAME CHANGE LATER
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.Red),
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(bottomStartPercent = 15, bottomEndPercent = 15)
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
                    MaterialTheme.typography.body1
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
                    text = Budget.sample().income.toString(), //this will change dynamically later
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


        }



    }
}
@Preview
@Composable
fun View(){
    Categories(modifier = Modifier , name = "Bills" , categoryColor = Color.Green, expense = "800")
}

@Composable
//default values for testing function is moving to another package
fun Categories(
    name: String = "Category Name",
    categoryColor:Color = Color.Red,
    modifier: Modifier,
    expense: String = "750",
    extraCategories: String?= null

){ //name change later
    val valid = remember{
        mutableStateOf(false)
    }
   Card(
       modifier = Modifier
           .fillMaxWidth()
           .height(60.dp)
           .padding(horizontal = 5.dp),
       shape = CircleShape
   ) {
       Row(verticalAlignment = Alignment.CenterVertically) {

           Row(
               modifier = Modifier.padding(horizontal = 12.dp),
               horizontalArrangement = Arrangement.Start,
               verticalAlignment = Alignment.CenterVertically
           ) {
               Card( //make button from scratch later //this whole thing into a new function
                   modifier = Modifier
                       .size(45.dp),
                   backgroundColor = categoryColor,
                   shape = CircleShape
               ) {
               }
               Spacer(modifier = Modifier.width(20.dp))
               Text(text = "${name}")
               Spacer(modifier = Modifier.width(60.dp))

               Text(text = "$${expense}")


               //this circle will be clickable


           }

       }


   }
}

