package com.example.helloworld.backend

import android.text.TextUtils

open class Expense {
    // variables
    var name:String = ""
    var cost:Double = -1.0
    lateinit var category:Category

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
        this.cost = cost
    }

    override fun toString() : String {
        return "$name: $$cost"
    }

    // aka static
    companion object {

    }
}