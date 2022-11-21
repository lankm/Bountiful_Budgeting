package com.example.bb.backend.budgets.ViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

import com.example.bb.backend.data.CategoryCard
import java.util.EnumSet.range


// VIEWMODEL CLASS


open class BucketViewModel : ViewModel() {

    public val category = mutableStateListOf<CategoryCard>()
    public val testIncome = mutableStateOf<Double>(0.00)

 //Test Change
    public fun getOverAllIncome(): Double {
        return  testIncome.value
    }

    public fun addTemplateCard(){
        //category.add(CategoryCard("Card added ${category.size}" , 5.50))
    }

}
