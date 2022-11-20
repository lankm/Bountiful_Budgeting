package com.example.bb.frontend

import android.app.DatePickerDialog
import android.graphics.Color
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bb.R
import com.example.bb.backend.Expense
import com.example.bb.backend.User
import com.example.bb.frontend.Calendar.curExpenseEdit
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun CalendarScreen(u: User) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        // Fetching the Local Context
        val mContext = LocalContext.current

        // Declaring integer values
        // for year, month and day
        val mYear: Int
        val mMonth: Int
        val mDay: Int

        // Initializing a Calendar
        val mCalendar = Calendar.getInstance()

        // Fetching current year, month and day
        mYear = mCalendar.get(Calendar.YEAR)
        mMonth = mCalendar.get(Calendar.MONTH)
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

        mCalendar.time = Date()

        // Declaring a string value to
        // store date in string format
        val mDate = remember { mutableStateOf("N/A") }

        var initExpense = ArrayList<Expense>()
        for(e in u.budgets[u.activeBud].getExpenses()) {
            initExpense.add(e)
        }
        var dayExpenses = remember {initExpense}


        // Declaring DatePickerDialog and setting
        // initial values as current values (present year, month and day)
        val mDatePickerDialog = DatePickerDialog(
            mContext,
            { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"

                dayExpenses.removeAll(dayExpenses.toSet())  //remove last days expenses
                for(e in u.budgets[u.activeBud].getExpenses()) {    //reinit
                    if(e.date.toString().substring(8,10).toInt() ==
                        mDate.value.substring(0,mDate.value.indexOf("/")).toInt())//if on selected day
                        dayExpenses.add(e)
                }
            }, mYear, mMonth, mDay
        )

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

            // Creating a button that on
            // click displays/shows the DatePickerDialog
            Button(onClick = {
                mDatePickerDialog.show()
            }, colors = ButtonDefaults.buttonColors(backgroundColor = White) ) {
                Text(text = "Open Date Picker", color = Black)
            }

            // Adding a space of 100dp height
            Spacer(modifier = Modifier.size(100.dp))

            // Displaying the mDate value in the Text
            Text(text = "Selected Date: ${mDate.value}", fontSize = 30.sp, textAlign = TextAlign.Center, color = White)
        }

        //********************************************************
        //displaying
        for(e in dayExpenses) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    //text = "${e.date}  ${mDate.value}",
                    text = "${e.category.name} Day: ${e.date.toString().substring(8,10)}\n${e.name} $${String.format("%.2f  ", e.cost)}",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start,
                    color = androidx.compose.ui.graphics.Color.White
                )

                Button(onClick = { curExpenseEdit = e
                    navController.navigate("editExpense")},
                    colors = ButtonDefaults.buttonColors(backgroundColor = androidx.compose.ui.graphics.Color.White)){
                    Text(text = "Edit", color = androidx.compose.ui.graphics.Color.Black)
                }
            }
        }
    }
}