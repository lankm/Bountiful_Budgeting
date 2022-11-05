package com.example.bb.backend

class User {
    // variables
    var name = ""
    var password = "" // its cool to be unsecure
    var logged_in = false
    var budgets = ArrayList<Budget>()

    // constructors
    constructor(name: String, password: String) {
        this.name = name
        this.password = password
    }

    fun login(entered: String): Boolean {
        if(password.compareTo(entered)==0)
            logged_in = true

        return logged_in
    }
    // adding/removing
    fun addBudget(b: Budget) {
        budgets.add(b)
    }

    // debug/toString
    fun showBudgets(): String {
        var str: String = this.toString()

        for(b in budgets) {
            str += "\n  $b"
        }

        return str
    }
    override fun toString(): String {
        return "$name: ........"
    }

    // aka static
    companion object {
        fun sample(): User {
            val u = User("Michael Siok", "password")
            val b = Budget.sample()

            u.addBudget(b)

            return u
        }
    }
}