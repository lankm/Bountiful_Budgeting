package com.example.helloworld.backend

import android.text.TextUtils

open class Expense {
    var name:String = ""
    var cat:Category = Category()
    var cost:Double = -1.0
    //date

    constructor(cost:Double) {
        this.cost = cost
    }


}