package com.example.helloworld

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.helloworld.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonL.setOnClickListener {
            binding.buttonL.text = "Left"
        }
        binding.buttonLM.setOnClickListener {
            binding.buttonLM.text = "LMid"
        }
        binding.buttonRM.setOnClickListener {
            binding.buttonRM.text = "RMid"
        }
        binding.buttonR.setOnClickListener {
            binding.buttonR.text = "Right"
        }

    }
}
