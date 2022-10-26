package com.example.helloworld.backend

class Budget {
    var name:String = ""
    var categories = ArrayList<Category>()
    var income:Double = -1.0
    var history = ArrayList<Expense>()
    var subs = ArrayList<Expense>()

    fun applyExpense(e:Expense) {

    }
    fun revert(e:Expense) {

    }
    fun edit(n:String, cat:ArrayList<Category>, income:Double) {

    }

}