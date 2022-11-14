package com.example.bb.backend

import java.io.*
import kotlin.collections.ArrayList


class Budget {
    var name: String = ""
    var income: Double = -1.0

    var categories = ArrayList<Category>()
    var reports = ArrayList<Report>()

    constructor(name: String, income: Double) {
        this.income = income
        this.name = name

        // every budget has uncategorized
        val uncategorized = Category()
        addCategory(uncategorized)
    }
    constructor(path: File, name: String) {
        //todo
    }

    // add/remove categories
    fun addCategory(c: Category) {
        // adding the category
        categories.add(c)
        c.bud = this

        // sorting categories by the size of categories
        categories = ArrayList(categories.sortedWith(compareBy { -it.cap }))
    }
    fun removeCategory(c: Category) {
        categories.remove(c)
    }

    // add/remove expenses
    fun addExpense(e: Expense) {
        addExpense(e, categories.size-1)
    }
    fun addExpense(e: Expense, index: Int) {
        // linking
        categories[index].addExpense(e)
    }
    fun removeExpense(e: Expense) {
        try {
            // removing from array
            e.category.removeExpense(e)
        } catch (e: Exception) {}
    }

    // utility
    fun total(): Double {
        var total = 0.0

        for(c in categories) {
            for(e in c.expenses) {
                total+=e.cost
            }
        }

        return total
    }


    // toString methods
    fun showAll(): String {
        var str: String = this.toString()

        for(c in categories) {
            str += "\n   $c"

            for(e in c.expenses) {
                str += "\n      $e"
            }
        }

        return str
    }
    fun showCategories(): String {
        var str: String = this.toString()

        for(c in categories) {
            str += "\n  $c"
        }

        return str
    }
    override fun toString(): String {
        return "$name $"  + String.format("%.2f", income) + ", $" + String.format("%.2f", total())
    }

    // aka static
    companion object {
        fun sample(): Budget {
            val bud = Budget("Sample Budget", 2400.00)
            val cat1 = Category("Living Expenses", 700.00)
            bud.addCategory(cat1)
            cat1.addExpense(Expense("Rent",675.00))

            val cat2 = Category("Food", 200.00)
            bud.addCategory(cat2)
            cat2.addExpense(Expense("Walmart",85.43))
            cat2.addExpense(Expense("Taco Bell",15.12))
            cat2.addExpense(Expense("Snack",2.50))

            bud.addExpense(Expense("Gift",25.00))
            bud.addExpense(Expense(19.23))

            return bud
        }
    }

    //only use this one on the report page, for the dropdown menu
    fun toString(a:Int): String{
        return name
    }

    //generate report
    fun makeReport(b: Budget){
        reports.add(Report(b))

        //var str: String
        var str = "\nBudget:"
        str += "\n\t\tIncome: ${reports[reports.lastIndex].budgetAmount}"
        str += "\n\t\tSpent: ${reports[reports.lastIndex].budgetSpent}\n"
        //TODO: look into while loop change
        var i = 0
        while(i < reports[0].numOfCategories){
            str += "\n${reports[0].categoryName[i]}:"
            str += "\n\t\tCategory limit: ${reports[reports.lastIndex].categoryLimit[i]}"
            str += "\n\t\tCategory spent: ${reports[reports.lastIndex].categorySpent[i++]}\n"
        }
        reports[reports.lastIndex].reportInfo = str
        //testing to make sure reportInfo is correct
        println(reports[reports.lastIndex].reportInfo)
    }

}