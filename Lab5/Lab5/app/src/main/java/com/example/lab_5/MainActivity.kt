package com.example.lab_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.lab_5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var btn1Text = "Button 1 has been clicked"
    private var btn2Text = "Button 2 has been clicked"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            showText(btn1Text)
        }

        binding.button2.setOnClickListener {
            showText(btn2Text)
        }

        binding.editText.setOnFocusChangeListener { v, hasFocus ->
            showText(if (hasFocus) "EditText focused" else "EditText lost focus")
        }

        binding.editText2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                showText("Before text changed")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showText("Text changed")
            }

            override fun afterTextChanged(s: Editable?) {
                showText("After text changed")
                binding.editText.setText(s)
            }
        })
    }

    private fun showText(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}