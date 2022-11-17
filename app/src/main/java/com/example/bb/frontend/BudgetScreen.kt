package com.example.bb.frontend

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.bb.DefaultPreview
import com.example.bb.IncomeComponent
import com.example.bb.R
import com.example.bb.backend.User

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BudgetScreen() {
    IncomeComponent()


}


@Composable
fun CardBudget(u: User){

}


