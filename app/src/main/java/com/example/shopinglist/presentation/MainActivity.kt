package com.example.shopinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shopinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}