package com.example.helloworld.backend

import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class Budget {
    var name: String = ""
    var income: Double = -1.0

    var categories = ArrayList<Category>()
    var total: Double = -1.0



    constructor(name: String, income: Double) {
        this.income = income
        this.name = name

        // every budget has uncategorized
        val uncategorized: Category = Category()
        this.categories.add(uncategorized);
    }

    //don't know where files are supposed to be
    constructor(filePath: String, fileName: String) {
        try {
            val sc: Scanner = Scanner(File("$filePath/$fileName"))
            sc.useDelimiter("\\s,")

            name = sc.next()
            income = sc.nextDouble()

            val cats: Int = sc.nextInt()
            for (i in 0..cats) {
                val c: Category = Category(sc.next(), sc.nextDouble())

                val exps: Int = sc.nextInt()
                for (i in 0..exps) {
                    val e: Expense = Expense(sc.next(), sc.nextDouble(), c)
                    c.addExpense(e)
                }
                addCategory(c)
            }
            sc.close()


        } catch(e: IOException) {
            e.printStackTrace()
        }
    }
    fun saveBudget(filePath: String, fileName: String) {
        try {
            val out = PrintStream("$filePath/$fileName")

            out.printf("%s,%f\n", name, income)
            out.printf("%d\n", categories.size)

            for (c in categories) {
                out.printf("%s,%f\n", c.name, c.cap)
                out.printf("%d\n", categories.size)

                for (e in c.expenses) {
                    out.printf("%s,%f\n", e.name, e.cost)
                }
            }
        } catch(e: IOException) {
            e.printStackTrace()
        }
    }

    // add/remove categories
    fun addCategory(c:Category) {
        categories.add(c)
    }
    fun removeCategory(c:Category) {
        categories.remove(c)
    }

    // add/remove expenses
    fun addExpense(e:Expense, index: Int) {
        categories[index].addExpense(e)
    }
    fun removeExpense(e:Expense) {
        e.category.revert(e)
    }




    override fun toString() : String {
        return name
    }

    // aka static
    companion object {
        fun availableBudgets(): ArrayList<String> {
            var names: ArrayList<String> = ArrayList()

            File("com.example.helloworld.backend.budgets").walk().forEach {
                names.add(it.toString())
            }

            return names
        }

        fun deleteBudget(fileName: String) {
            File(fileName).delete()
        }
    }
}