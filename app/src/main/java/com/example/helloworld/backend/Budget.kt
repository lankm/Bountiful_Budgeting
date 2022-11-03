package com.example.helloworld.backend

import java.io.File
import java.util.concurrent.ArrayBlockingQueue


class Budget {
    var name: String = ""
    var income: Double = -1.0

    var categories = ArrayList<Category>()
    var total: Double = -1.0



    constructor(name: String, income: Double, startDay: Int) {
        this.income = income
        this.name = name

        // every budget has uncategorized
        val uncategorized: Category = Category()
        this.categories.add(uncategorized);
    }

    constructor(fileName: String) {
        //todo: make a file structure
        //todo
    }
    fun saveBudget(fileName: String) {

    }

    // add/remove categories
    fun addCategory(c:Category) {
        categories.add(c)
    }
    fun removeCategory(c:Category) {
        categories.add(c)
    }

    // add/remove expenses
    fun addExpense(e:Expense, index: Int) {
        categories[index].addExpense(e)
    }
    fun removeExpense(e:Expense) {
        e.category.revert(e)
    }







    // aka static
    companion object {
        fun availableBudgets(): ArrayList<String> {
            var names: ArrayList<String> = ArrayList()

            File("com.example.helloworld.backend.budgets").walk().forEach {
                names.add(it.toString())
            }

            return names
        }

        fun deleteBudget(fileName: String) {
            File(fileName).delete()
        }
    }
}