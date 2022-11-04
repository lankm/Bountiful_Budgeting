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
        e.category = this
        expenses.add(e)
    }
    fun removeExpense(e:Expense) {
        try {
            expenses.remove(e)
        } catch(e: Exception) {

        }
    }

    override fun toString() : String {
        if(cap == -1.0)
            return "$name: ____"
        else
            return "$name: $$cap"
    }






}