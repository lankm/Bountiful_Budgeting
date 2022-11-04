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

        var context = applicationContext
        var f = context.filesDir

        val b: Budget = Budget.sample()
        val e = Expense("Power", 20.2)
        b.removeExpense(e)


        binding.textView.text = b.showAll()

    }
}
