package com.example.bb.backend

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
    var numOfCategories = 0

    constructor(budget: Budget){
        //this.date =       //get the time
        this.budgetAmount = budget.income
        this.budgetSpent = budget.total()

        for(c in budget.categories) {
            categoryName.add(c.name)
            categoryLimit.add(c.cap)
            categorySpent.add(c.total())
            numOfCategories++
        }



    }




}
