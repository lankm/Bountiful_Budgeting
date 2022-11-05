package com.example.bb.backend

import com.example.bb.backend.Category

open class Expense {
    // variables
    var name:String = ""
    var cost:Double = -1.0

    lateinit var category: Category

    // constructors
    constructor(name: String, cost: Double) {
        this.name = name
        this.cost = cost
    }
    constructor(cost:Double) {
        this.name = "Expense"
        this.cost = cost
    }
    constructor(fileLine: String) {
        //todo
    }

    // editing variables
    fun edit(name: String, cost:Double, category: Category) {
        this.name = name
        this.cost = cost
        this.category = category
    }
    fun edit(cost:Double) {
        edit(name, cost, category)
    }

    // debug/toString
    override fun toString() : String {
        return "$name: $" + String.format("%.2f", cost)
    }

    // aka static
    companion object {
        fun sample(): Expense {
            return Expense("Mail", 1.25)
        }
    }
}