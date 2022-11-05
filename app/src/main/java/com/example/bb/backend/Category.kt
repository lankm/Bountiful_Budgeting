package com.example.bb.backend

import kotlin.collections.ArrayList

open class Category {
    var name:String = ""
    var cap:Double = -1.0

    var expenses = ArrayList<Expense>()
    //var subs = ArrayList<Expense>()

    lateinit var bud: Budget

    //constructors
    constructor(name: String, cap: Double) {
        this.name = name
        this.cap = cap
    }
    constructor() {
        this.name = "Uncategorized"
    }

    // adding/removing
    fun addExpense(e: Expense) {
        // links the expense to this category
        e.category = this

        expenses.add(e)
    }
    fun removeExpense(e: Expense) {
        try {
            expenses.remove(e)
        } catch(e: Exception) {}
    }

    // utility
    fun total(): Double {
        var total = 0.0

        for(e in expenses) {
            total+=e.cost
        }

        return total
    }

    // debug/toString
    fun showAll() : String {
        var str: String = this.toString()

        for(e in expenses) {
            str += "\n  $e"
        }

        return str
    }
    override fun toString() : String {
        if(cap == -1.0)
            return "$name: ____" + ", $" + String.format("%.2f", total())
        else
            return "$name: $" + String.format("%.2f", cap) + ", $" + String.format("%.2f", total())
    }

    // aka static
    companion object {
        fun sample(): Category {
            val cat = Category("Games", 60.00)

            cat.addExpense(Expense("The Last of Us",25.00))
            cat.addExpense(Expense("Destiny 2",25.00))

            return cat
        }
    }
}
