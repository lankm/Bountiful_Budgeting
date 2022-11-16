package com.example.bb.backend

import com.example.bb.Categories
import java.io.*
import java.util.*
import java.util.concurrent.Flow.Subscription
import kotlin.collections.ArrayList


open class Budget {
    var name: String = ""
    var income: Double = -1.0

    var categories = ArrayList<Category>()
    var Subs = ArrayList<Expense>()

    var reports = ArrayList<Report>()

    constructor(name: String, income: Double) {
        this.income = income
        this.name = name

        // every budget has uncategorized
        val uncategorized = Category()
        addCategory(uncategorized)
    }
    constructor(b: Budget) {
        this.name = b.name
        this.income = b.income

        //copying data over
        for (c in b.categories) {
            var nc = Category(c.name, c.cap)

            for (e in c.expenses) {
                nc.addExpense(Expense(e.name, e.cost))
            }

            addCategory(nc)
        }

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
    fun addReport(r: Report) {
        reports.add(r)
    }

    // utility
    fun total(): Double {
        var total = 0.0

        for(c in categories) {
            total+=c.total()
        }

        return total
    }
    fun alertNeeded(AS: AlertSetting) : Boolean {
        return total()/income >= (1-AS.budgetPercent)
    }
    fun generateReport() : Report {
        var r = Report(this)

        reports.add(r)

        return r
    }
    fun reset() {   // usually done after generating a report
        for(c in categories) {
            c.reset()
        }
    }
    fun percent() : Double{
        return (total()/income) * 100
    }
    fun getExpenses() : List<Expense> {
        var exp = ArrayList<Expense>()

        for (c in categories) {
            for (e in c.expenses) {
                exp.add(e)
            }
        }
        exp.sort()

        return exp.reversed()
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
        return String.format("%s", name) + String.format("%8.2f%%", percent())
    }

    // aka static
    companion object {
        fun sample(): Budget {
            val bud = Budget("Sample Budget", 2400.00)
            val cat1 = Category("Living Expenses", 700.00)
            bud.addCategory(cat1)
            cat1.addExpense(Expense("Rent",675.00, Date(122, 9,12)))

            val cat2 = Category("Food", 200.00)
            bud.addCategory(cat2)
            cat2.addExpense(Expense("Walmart",85.43, Date(122, 9,20)))
            cat2.addExpense(Expense("Taco Bell",15.12, Date(122, 9,10)))
            cat2.addExpense(Expense("Snack",2.50, Date(122, 9,15)))

            bud.addExpense(Expense("Gift",25.00, Date(122, 9,13)))
            bud.addExpense(Expense(19.23, Date(122, 9,12)))

            // adding a single report
            bud.generateReport()

            return bud
        }
    }
}