package com.example.Lab8

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.Lab8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.recyclerViewVert.layoutManager = LinearLayoutManager(this)
        val countries = listOf(
            Pair("USA", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Flag_of_the_United_States.png/1200px-Flag_of_the_United_States.png"),
            Pair("UK", "https://cdn.britannica.com/25/4825-004-F1975B92/Flag-United-Kingdom.jpg"),
            Pair("Ukraine", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/49/Flag_of_Ukraine.svg/2000px-Flag_of_Ukraine.svg.png"),
            Pair("France", "https://cdn11.bigcommerce.com/s-2lbnjvmw4d/images/stencil/1280x1280/products/4410/5333/francesleeve__90485.1619695878.jpg"),
            Pair("Germany", "https://cdn11.bigcommerce.com/s-2lbnjvmw4d/images/stencil/1280x1280/products/2930/5201/germanyflag__35169.1614269120.jpg"),
        )
        val adapter = CountryAdapter(countries){ position: Int ->
            val intent = Intent(this, CountryDetailsActivity::class.java)
            intent.putExtra("countryName", countries[position].first)
            startActivity(intent)
        }
        binding.recyclerViewVert.adapter = adapter

        binding.recyclerViewHor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val names = listOf(
            "USA",
            "UK",
            "Ukraine",
            "France",
            "Germany",
        )
        val adapterSecond = HorizontalViewAdapter(names)
        binding.recyclerViewHor.adapter = adapterSecond

    }
}