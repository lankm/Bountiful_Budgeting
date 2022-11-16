package com.example.bb.backend

class User {
    // variables
    var name = ""
    var password = "" // its cool to be unsecure
    var logged_in = false
    var budgets = ArrayList<Budget>()
    var reports = ArrayList<Report>()

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

    //generate report
    fun makeReport(b: Budget): String{
        reports.add(Report(b))

        var str: String = this.toString()
        //TODO: figure out how to report for the most recent report, instead of using static arrays
        str = "\nBudget:"
        str += "\n\t\tIncome: ${reports[0].budgetAmount}"
        str += "\n\t\tSpent: ${reports[0].budgetSpent}\n"
        //TODO: look into while loop change
        var i = 0
        while(i < reports[0].numOfCategories){
            str += "\n${reports[0].categoryName[i]}:"
            str += "\n\t\tCategory limit: ${reports[0].categoryLimit[i]}"
            str += "\n\t\tCategory spent: ${reports[0].categorySpent[i++]}\n"
        }

        return str
    }
}