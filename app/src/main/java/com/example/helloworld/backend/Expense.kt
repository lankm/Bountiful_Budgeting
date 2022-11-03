package com.example.helloworld.backend

import android.text.TextUtils

open class Expense {
    // variables
    var name:String = ""
    var cost:Double = -1.0
    var category:Category = Category()

    // constructors
    constructor(name: String, cost:Double, category: Category) {
        this.name = name
        this.cost = cost
        this.category = category
    }
    constructor(cost:Double, category: Category) {
        this.name = "Expense"
        this.cost = cost
        this.category = category
    }
    constructor(cost:Double) {
        this.name = "Expense"
        this.cost = cost
        this.category = Category()
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



}