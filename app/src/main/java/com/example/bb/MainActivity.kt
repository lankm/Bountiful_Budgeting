package com.example.bb

import android.os.Build
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bb.backend.*
import com.example.bb.backend.budgets.ViewModel.BucketViewModel
import com.example.bb.frontend.Login
import com.example.bb.frontend.LoginScreen
import com.example.bb.frontend.MainScreen
import com.example.bb.frontend.ViewModelTest
import com.example.bb.ui.theme.BountifulBudgetingTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.math.exp

// don't change MainActivity.
/* If you change something it should be in:
 * -BudgetScreen(),
 * -CalendarScreen(),
 * -topbar section of MainScreen(),
 * -or polishing up the ReportScreen()
 * Everything else works properly and looks 'good enough'
 */


class MainActivity : ComponentActivity() {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          Login()







        }
    }
}



