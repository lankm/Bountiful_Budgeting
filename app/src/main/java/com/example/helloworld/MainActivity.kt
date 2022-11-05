package com.example.helloworld

import com.example.helloworld.backend.*

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var u: User = User("Landon", "password")

        var b: Budget = Budget("Personal", 2700.0)
        var c: Category = Category("Living Expenses", 12.12)
        var e: Expense = Expense("Rent", 12.12)

        b.addCategory(c)
        b.addExpense(e, 0)
        b.addExpense(e)

        u.addBudget(b)


        binding.textView.text = u.showBudgets()

    }
}
