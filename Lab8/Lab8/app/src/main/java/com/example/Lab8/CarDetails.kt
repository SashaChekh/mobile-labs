package com.example.Lab8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.Lab8.databinding.CountryDetailsBinding

class CountryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: CountryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CountryDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val carName = intent.getStringExtra("countryName")
        binding.countryButton.text = carName
    }
}
