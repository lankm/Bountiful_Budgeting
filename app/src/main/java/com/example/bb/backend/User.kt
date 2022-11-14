package com.example.bb.backend

class User {
    // variables
    var name = ""
    var password = "" // its cool to be unsecure
    var logged_in = false
    var budgets = ArrayList<Budget>()

    //currently moved to Budget class, might get moved back
    //var reports = ArrayList<Report>()

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
    //for now this is being moved to budget class
    //might get moved back later
    /*
    fun makeReport(b: Budget){
        reports.add(Report(b))

        var str: String = this.toString()
        str = "\nBudget:"
        str += "\n\t\tIncome: ${reports[reports.lastIndex].budgetAmount}"
        str += "\n\t\tSpent: ${reports[reports.lastIndex].budgetSpent}\n"
        //TODO: look into while loop change
        //not sure if this is a necessary change
        var i = 0
        while(i < reports[0].numOfCategories){
            str += "\n${reports[0].categoryName[i]}:"
            str += "\n\t\tCategory limit: ${reports[reports.lastIndex].categoryLimit[i]}"
            str += "\n\t\tCategory spent: ${reports[reports.lastIndex].categorySpent[i++]}\n"
        }
        reports[reports.lastIndex].reportInfo = str
        //testing to make sure reportInfo is correct
        println(reports[reports.lastIndex].reportInfo)
    }
    */

}