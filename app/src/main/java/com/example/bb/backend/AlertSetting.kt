package com.example.bb.backend

class AlertSetting {
    var budgetPercent = 25.0
    var categoryPercent = 25.0

    constructor() {

    }
    constructor( bud: Double, cat: Double) {
        this.budgetPercent = bud
        this.categoryPercent = cat
    }
}