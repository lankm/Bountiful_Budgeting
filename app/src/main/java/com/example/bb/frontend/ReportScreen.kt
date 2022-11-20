
package com.example.bb.frontend

import android.graphics.Color
import android.graphics.fonts.FontFamily
import android.os.Build
import android.util.Size
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle.Companion.Default
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.bb.backend.*
import androidx.compose.ui.window.PopupProperties
import com.example.bb.MainActivity
import com.example.bb.R
import java.time.format.TextStyle


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ReportScreen(u: User) {
    //remove this once we can grab the users data from anywhere
    var text:String by remember { mutableStateOf("Select a budget to generate a report")}
    var reportText: String by remember { mutableStateOf("Select a report to view")}

    var budgetExpanded by remember { mutableStateOf(false) }
    var reportExpanded by remember { mutableStateOf(false) }
    var selectedBudgetText by remember { mutableStateOf(u.budgets[0].name) }
    var selectedReportText by remember { mutableStateOf(u.budgets[0].reports[0].date) }
    var currentBudget = u.budgets[0]
    var currentReport = u.budgets[0].reports[0]


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
            textAlign = TextAlign.Start,
            fontSize = 20.sp
        )

        //select budget text
        ExposedDropdownMenuBox(
            expanded = budgetExpanded,
            onExpandedChange = {
                budgetExpanded = !budgetExpanded
            }
        ) {
            TextField(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                readOnly = true,
                value = selectedBudgetText,
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = budgetExpanded
                    )
                },
                colors = TextFieldDefaults.textFieldColors(textColor = androidx.compose.ui.graphics.Color.Black, backgroundColor = androidx.compose.ui.graphics.Color.White)
            )
            ExposedDropdownMenu(
                expanded = budgetExpanded,
                onDismissRequest = {
                    budgetExpanded = false
                }
            ) {
                u.budgets.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            currentBudget = selectionOption
                            selectedBudgetText = selectionOption.name
                            budgetExpanded = false
                        }
                    ) {
                        Text(text = selectionOption.name)
                    }
                }
            }
        }

        //select report
        ExposedDropdownMenuBox(
            expanded = reportExpanded,
            onExpandedChange = {
                reportExpanded = !reportExpanded
            }
        ) {
            TextField(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp),
                readOnly = true,
                    value = selectedReportText,
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = reportExpanded
                    )
                },
                colors = TextFieldDefaults.textFieldColors(textColor = androidx.compose.ui.graphics.Color.Black, backgroundColor = androidx.compose.ui.graphics.Color.White)
            )
            ExposedDropdownMenu(
                expanded = reportExpanded,
                onDismissRequest = {
                    reportExpanded = false
                }
            ) {
                currentBudget.reports.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            currentReport = selectionOption
                            selectedReportText = selectionOption.date
                            reportText = selectionOption.makeReport()
                            reportExpanded = false
                        }
                    ) {
                        Text(text = selectionOption.date)
                    }
                }
            }
        }

        //generate a report for current budget
        Button(onClick = {
            val generatedReport = Report(currentBudget)
            currentBudget.reports.add(generatedReport)
        }){
            Text("Generate report for selected budget")
        }

        Box(
            modifier = Modifier
                .background(androidx.compose.ui.graphics.Color.White,
                            shape = RoundedCornerShape(6.dp))
                .align(Alignment.CenterHorizontally)
                .padding(all = 10.dp)

        ) {
            Text(
                text = reportText,
                //fontWeight = FontWeight.Bold,
                color = androidx.compose.ui.graphics.Color.Black,
                //modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Start,
                fontSize = 20.sp
            )
        }

    }
}


