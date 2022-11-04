package com.example.helloworld.backend

import java.io.*
import kotlin.collections.ArrayList


class Budget {
    var name: String = ""
    var income: Double = -1.0

    var categories = ArrayList<Category>()
    var total: Double = -1.0


    constructor(name: String, income: Double) {
        this.income = income
        this.name = name
        this.total = 0.0

        // every budget has uncategorized
        val uncategorized: Category = Category()
        this.categories.add(uncategorized)
    }

    // add/remove categories
    fun addCategory(c:Category) {
        // adding the category
        categories.add(c)

        // sorting categories by the size of categories
        categories = ArrayList(categories.sortedWith(compareBy { -it.cap }))
    }
    fun removeCategory(c:Category) {
        categories.remove(c)
    }

    // add/remove expenses
    fun addExpense(e:Expense) {
        // adding to uncategorized
        categories[categories.size-1].addExpense(e)
    }
    fun addExpense(e:Expense, index: Int) {
        categories[index].addExpense(e)
    }
    fun removeExpense(e:Expense) {
        e.category.revert(e)
    }

    // toString methods
    fun showAll(): String {
        var str: String = this.toString()

        for(c in categories) {
            str += "\n  $c"

            for(e in c.expenses) {
                str += "\n    $e"
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
        return "$name $$income"
    }

    // aka static
    companion object {
        fun sample(): Budget {
            var bud: Budget = Budget("Sample", 2400.0)

            var cat1: Category = Category("Living Expenses", 700.0)
            bud.addCategory(cat1)
            cat1.addExpense(Expense("Rent",675.0))

            var cat2: Category = Category("Food", 200.0)
            bud.addCategory(cat2)
            cat2.addExpense(Expense("Walmart",85.43))
            cat2.addExpense(Expense("Taco Bell",15.12))

            return bud
        }
    }
}