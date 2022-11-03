package com.example.helloworld.backend

class Subscription : Expense {

    constructor(name: String, cost:Double, category: Category) {
        this.name = name
        this.cost = cost
        this.cat = category
    }
    constructor() {

    }
}