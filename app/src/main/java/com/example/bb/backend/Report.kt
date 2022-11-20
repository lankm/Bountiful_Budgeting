package com.example.bb.backend

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Report {
    //user has multiple reports
    //income
    //total amount spent
    //total spent for each category
    //any over-spending
    var date: String = ""
    var budgetAmount = -1.0
    var budgetSpent = -1.0
    var categoryName = ArrayList<String>()
    var categoryLimit = ArrayList<Double>()
    var categorySpent = ArrayList<Double>()
    var categoryPercentage = ArrayList<Double>()
    var numOfCategories = 0

    @RequiresApi(Build.VERSION_CODES.O)
    constructor(budget: Budget){

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM yyyy")

        this.date = current.format(formatter)
        this.budgetAmount = budget.income
        this.budgetSpent = budget.total()

        for(c in budget.categories) {
            categoryName.add(c.name)
            categoryLimit.add(c.cap)
            categorySpent.add(c.total())
            categoryPercentage.add(c.percent())
            numOfCategories++
        }
    }

    fun makeReport():String{
        var reportText:String = "Date: ${date}"
        reportText += "\nBudget amount: $${this.budgetAmount}"
        reportText += "\nTotal spent: $${this.budgetSpent}"
        reportText += "\nTotal categories: ${this.numOfCategories}\n"
        val loopNum = this.categoryName.size

        for(i in 0 until this.numOfCategories){
            reportText += "\n${this.categoryName[i]}"

            //formats the doubles to only print two decimal points
            reportText += "\n\t\tLimit: $${String.format("%.2f",this.categoryLimit[i])}"
            reportText += "\n\t\tSpent: $${String.format("%.2f",this.categorySpent[i])}"
            reportText += "\n\t\tPercentage spent:${String.format("%.2f",this.categoryPercentage[i])}%"
            reportText += "\n"
        }
        return reportText
    }
}

