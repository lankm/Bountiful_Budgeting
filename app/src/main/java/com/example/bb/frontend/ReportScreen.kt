package com.example.bb.frontend

import android.graphics.Color
import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius.Companion.Zero
import androidx.compose.ui.geometry.Size.Companion.Zero
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.bb.backend.*
import androidx.compose.ui.window.PopupProperties
import com.example.bb.R


@Preview
@Composable
fun ReportScreen() {

    //remove this once we can grab the users data from anywhere
    val u = User.sample()
    var text:String by remember { mutableStateOf("Select a budget to generate a report")}
    //holds the currently selected budget
    val testBudgetArray = ArrayList<Budget>(1)

    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .verticalScroll(state = scrollState)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = androidx.compose.ui.graphics.Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )

        //dropdown menu
        BudgetSpinner(u.budgets,testBudgetArray)

        //displays the report for the selected budget
        Button(onClick = {
            if(testBudgetArray.isEmpty()){
                text = "No budget selected"
            }
            else {
                //generate report and save to text
                //consider changing return value of 'make report' to a string, so you can combine these lines
                testBudgetArray[0].makeReport(testBudgetArray[0])
                text = testBudgetArray[0].reports[u.budgets[0].reports.lastIndex].reportInfo
            }
        }){
            Text("Generate Report")
        }

    }
}

//dropdown menu
@Composable
fun BudgetSpinner (budgets: List<Budget>, selectedBudget: ArrayList<Budget>) {
    var budgetText by remember {mutableStateOf("Select Report")}
    var expanded by remember { mutableStateOf(false)}
    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(Modifier
            .padding(24.dp)
            .clickable {
                expanded = !expanded
            }
            .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = budgetText, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
            Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
            DropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                budgets.forEach {
                        budget: Budget -> DropdownMenuItem(onClick = {
                    expanded = false

                    //if selectedBudget has a budget in it, drop it
                    if(selectedBudget.isNotEmpty()) {
                        selectedBudget.clear()
                    }
                    budgetText = budget.toString(1)
                    selectedBudget.add(budget)
                }) {
                    Text(text = budget.toString(1))
                }
                }
            }
        }
    }
}

