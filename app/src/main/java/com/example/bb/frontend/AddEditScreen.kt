package com.example.bb.frontend

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.RestoreFromTrash
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bb.backend.Expense
import com.example.bb.backend.User
import com.example.bb.frontend.Components.InputField
import com.example.bb.frontend.Components.InputFieldText

//Holding data
data class OutputClass(
    var categoryname: String?,
    var categoryvalueMoney: String? ,
    var expensename: String? ,
    var expensevalueMoney: String? ,
)

//also we can refactor the variables later
var categoryname: String? = null
var categoryvalueMoney: String? = null
var expensename: String? = null
var expensevalueMoney: String? = null

//LOGIC, this can be refactor to another page but im lazy
// all this pain could of been done by a Database ;-;



var indexValue:Int = 0

@Preview
@Composable
fun Preview(){
    val u = User.sample()

    Column() {
        AddBudgets(u)
    }


}





@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddBudgets(u:User){
    val outputClass = OutputClass(categoryname,categoryvalueMoney,expensename,expensevalueMoney)


    val list = remember {
        mutableStateListOf<Expense>()
    }

    val valueName = remember{
        mutableStateOf("")
    }

    categoryname=valueName.value

    val valueCap = remember{
        mutableStateOf("")
    }
    categoryvalueMoney= valueCap.value
    val valueExpense = remember{
        mutableStateOf("")
    }


    val valueExpenseAmount = remember{
        mutableStateOf("")
    }


    //todo: sucks
    val validState = remember(valueName.value) {
        valueName.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val numTest = remember{
        mutableStateOf(0)
    }



    Surface(


    ) {
        Card () {
            Column(modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
                    Row() {
                        Box(modifier = Modifier.width(200.dp)) {

                            AddCategoryName(outputClass,valueName,validState,keyboardController) {


                            }


                        }

                        Box(modifier = Modifier.width(150.dp)) {
                            AddCategoryNum(outputClass,valueCap,validState,keyboardController) {

                            }
                        }



                    }
                Spacer(modifier = Modifier.height(16.dp))

                Divider(modifier=
                Modifier.padding(horizontal = 16.dp) ,
                    color = Color(0xFF959595),
                    thickness = 1.dp)



                Spacer(modifier = Modifier.width(10.dp))
                LazyColumn {
                    // Add a single item

                    // Add 4 items

                    //Test for now
                    items(numTest.value) {index->
                        ExpenseCard(list,index,outputClass,valueExpense,valueExpenseAmount,validState,keyboardController){


                        }


                    }

                }



                Spacer(modifier = Modifier.height(16.dp))
Row() {
    FinishButton(){
        navController.popBackStack()

    }
Spacer(modifier = Modifier.width(16.dp))
    ExpenseAddButton(onClick = {
       numTest.value++

    })
}
            }
        }
    }
}




@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ExpenseCard(list: SnapshotStateList<Expense>,
                indexValue:Int,
                outputClass:OutputClass,
                valueExpense: MutableState<String>,
                valueExpenseAmount: MutableState<String>,
                validState:Boolean, keyboardController: SoftwareKeyboardController?, onClick: () -> Unit){




    Surface(modifier = Modifier.padding(5.dp),) {

}
    Row() {
        Box(modifier = Modifier.width(200.dp)) {
            AddExpenseName(valueExpense,validState,keyboardController, onValChange = {})
        }

        Box(modifier = Modifier.width(150.dp)) {
            AddExpense(valueExpenseAmount,validState,keyboardController, onValChange = {})
        }


        DeleteButton(){



        }


    }


}




@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddCategoryName(outputClass:OutputClass,value: MutableState<String>,validState :Boolean, keyboardController: SoftwareKeyboardController?, onValChange: (String) -> Unit = {}) {





    InputFieldText(
            valueState = value ,
            labelId = "Category Name", //Enter Amount
            enabled = true ,
            isSingleLine = true,
            onAction = KeyboardActions{
                if(!validState) return@KeyboardActions

                onValChange( value.value.trim() ) //why?
                keyboardController?.hide()



            }
        )



}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddCategoryNum(outputClass:OutputClass, value: MutableState<String>,validState :Boolean, keyboardController: SoftwareKeyboardController?, onValChange: (String) -> Unit = {}) {





    InputFieldText(
        valueState = value ,
        labelId = "Category Name", //Enter Amount
        enabled = true ,
        isSingleLine = true,
        onAction = KeyboardActions{
            if(!validState) return@KeyboardActions

            onValChange( value.value.trim() ) //why?
            keyboardController?.hide()



        }
    )



}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddExpenseName(value: MutableState<String>,validState :Boolean, keyboardController: SoftwareKeyboardController?, onValChange: (String) -> Unit = {}){




    InputFieldText(
        valueState = value ,
        labelId = "Expense Name", //Enter Amount
        enabled = true ,
        isSingleLine = true,
        onAction = KeyboardActions{
            if(!validState) return@KeyboardActions

            onValChange( value.value.trim() ) //why?
            keyboardController?.hide()



        }
    )



}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddExpense(value: MutableState<String>,validState :Boolean, keyboardController: SoftwareKeyboardController?, onValChange: (String) -> Unit = {}){



    InputField(
        valueState = value ,
        labelId = "Expense Amount", //Enter Amount
        enabled = true ,
        isSingleLine = false,
        onAction = KeyboardActions{
            if(!validState) return@KeyboardActions

            onValChange( value.value.trim() ) //why?
            keyboardController?.hide()



        }
    )

}




@SuppressLint("InvalidColorHexValue")
@Composable
fun FinishButton(onClick: () -> Unit){
    Button(modifier= Modifier
        .clip(shape = CircleShape)
        .background(Color(0xFFD9D9D9)),
        onClick = onClick) {
        Text(text = "Finalize Budget")
        //Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Button", tint = Color(0xFF9898FF))
    }
}


@SuppressLint("InvalidColorHexValue")
@Composable
fun ExpenseAddButton(onClick: () -> Unit){
    Button(modifier= Modifier
        .clip(shape = CircleShape)
        .background(Color(0xFFD9D9D9)),
        onClick = onClick) {
        Text(text = "Add Expense")
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Button", tint = Color(0xFF9898FF))
    }
}

@SuppressLint("InvalidColorHexValue")
@Composable
fun DeleteButton(onClick: () -> Unit){
    IconButton(modifier= Modifier
        .clip(shape = CircleShape)
        .background(Color(0xFFD9D9D9)),
        onClick = onClick) {

        Icon(imageVector = Icons.Filled.RestoreFromTrash, contentDescription = "RestoreFromTrash", tint = Color(0xFF9898FF))
    }
}