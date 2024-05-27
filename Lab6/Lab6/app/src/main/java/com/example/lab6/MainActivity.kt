package com.example.lab6

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var car: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val carParam = intent.getParcelableExtra<Car>("car")
        if (carParam != null) {
            binding.nameText.setText(car.name)
            binding.courseText.setText(car.price)
        }

        car = Car("Nissan Terrano", 1450000)
        binding.button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("car", Car(binding.nameText.text.toString(), binding.courseText.text.toString().toInt()))
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("car", car)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        car = savedInstanceState.getParcelable("car")!!
    }
}