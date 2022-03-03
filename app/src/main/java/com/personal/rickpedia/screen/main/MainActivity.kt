package com.personal.rickpedia.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.personal.rickpedia.R
import com.personal.rickpedia.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.bind(findViewById(R.id.splashLayout))

    }
}