package com.example.bb.frontend.Calendar

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.bb.backend.Category
import com.example.bb.backend.Expense
import com.example.bb.backend.User
import com.example.bb.frontend.currentUser
import com.example.bb.frontend.navController


lateinit var curExpenseEdit: Expense
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun editExpense(u: User) {

    var cat by remember { mutableStateOf(curExpenseEdit.category) }
    var name by remember { mutableStateOf(curExpenseEdit.name) }
    var cost by remember { mutableStateOf(curExpenseEdit.cost.toString()) }


    Column() {
        Button(onClick = {
            try {
                var e = Expense(name, cost.toDouble(), curExpenseEdit.date)
                e.category = cat

                cat.addExpense(e)
                u.budgets[u.activeBud].removeExpense(curExpenseEdit)
            } catch (e: java.lang.Exception) {}
            //change page
            navController.navigate("calendar")
        },
            modifier = Modifier
                .padding(16.dp)
                .height(35.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        ){
            Text("Save & Exit")
        }
        //******************************************************************************************
        Column(modifier = Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.size(50.dp))
            Text(
                "Category",
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 0.dp)
                    .height(20.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )

            val catOptions = u.budgets[u.activeBud].categories

            var catExpanded by remember { mutableStateOf(false) }
            var catSelectedOptionText by remember { mutableStateOf(cat.name) }

            ExposedDropdownMenuBox(
                expanded = catExpanded,
                onExpandedChange = {
                    catExpanded = !catExpanded
                }
            ) {
                TextField(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp),
                    readOnly = true,
                    value = catSelectedOptionText,
                    onValueChange = { },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = catExpanded
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        backgroundColor = Color.White
                    )
                )
                ExposedDropdownMenu(
                    expanded = catExpanded,
                    onDismissRequest = {
                        catExpanded = false
                    }
                ) {
                    catOptions.forEach { selectionOption ->
                        DropdownMenuItem(
                            onClick = {
                                cat = selectionOption
                                catSelectedOptionText = selectionOption.name
                                catExpanded = false
                            }
                        ) {
                            Text(text = selectionOption.name)
                        }
                    }
                }
            }
            //**************************************************************************************
            Text(
                "Name",
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 0.dp)
                    .height(20.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            TextField(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp),
                value = name,
                onValueChange = { newText ->
                    name = newText
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White
                )
            )
            //**************************************************************************************
            Text(
                "Amount",
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 0.dp)
                    .height(20.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            TextField(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 0.dp),
                value = cost,
                onValueChange = { newText ->
                    cost = newText
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    backgroundColor = Color.White
                )
            )
            //**************************************************************
            Button(onClick = {
                u.budgets[u.activeBud].removeExpense(curExpenseEdit)
                //change page
                navController.navigate("calendar")
            },
                modifier = Modifier
                    .padding(16.dp)
                    .height(35.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                )
            ){
                Text("Delete")
            }
        }
    }
}