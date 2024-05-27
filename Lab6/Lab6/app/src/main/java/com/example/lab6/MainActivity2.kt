package com.example.lab6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val car = intent.getParcelableExtra<Car>("car")

        if (car != null) {
            binding.textView.setText("Name: ${car.name}\nPrice: ${car.price}")
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("car", car)
            startActivity(intent)
        }
    }
}