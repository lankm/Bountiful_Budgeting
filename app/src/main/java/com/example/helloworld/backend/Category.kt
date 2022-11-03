package com.example.helloworld.backend

import java.util.*
import kotlin.collections.ArrayList

open class Category {
    var name:String = ""
    var cap:Double = -1.0
    var expenses = ArrayList<Expense>()
    //var subs = ArrayList<Expense>()

    constructor(name: String, cap: Double) {
        this.name = name
        this.cap = cap
    }
    constructor() {
        this.name = "Uncategorized"
    }

    fun addExpense(e:Expense) {
        expenses.add(e)
    }
    fun revert(e:Expense) {
        expenses.remove(e)
    }


    fun edit(n:String, cat:ArrayList<Category>, income:Double) {

    }





}