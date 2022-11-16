package com.example.bb.backend

import com.example.bb.backend.Category
import java.util.*

open class Expense : Comparable<Expense>{
    // variables
    var name:String = ""
    var cost:Double = -1.0
    var date: Date = Calendar.getInstance().time

    lateinit var category: Category

    // constructors
    constructor(name: String, cost: Double) {
        this.name = name
        this.cost = cost
    }

    constructor(name: String, cost: Double, date: Date) {
        this.name = name
        this.cost = cost
        this.date = date
    }
    constructor(cost:Double) {
        this.name = "Expense"
        this.cost = cost
    }
    constructor( cost: Double, date: Date) {
        this.name = "Expense"
        this.cost = cost
        this.date = date
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
        return "$name: $" + String.format("%.2f  ", cost) + date.toString()
    }



    // aka static
    companion object {
        fun sample(): Expense {
            return Expense("Mail", 1.25)
        }
    }

    override fun compareTo(other: Expense): Int {
        return date.compareTo(other.date)
    }
}