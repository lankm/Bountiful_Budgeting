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

        val b: Budget = Budget.sample()
        val e = Expense("Power", 20.2)
        b.addExpense(e, 0)


        binding.textView.text = b.showAll()

    }
}
