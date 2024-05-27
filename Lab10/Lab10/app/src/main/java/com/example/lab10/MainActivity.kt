package com.example.lab10

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.lab10.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getPosts()

        binding.button.setOnClickListener {
            addPost()
        }
    }

    private fun getPosts(){
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launch {
            val response = apiInterface.getPost(1)
            binding.textView.text = "${response.body()?.title}\n\n\n${response.body()?.body}"
        }
    }

    private fun addPost(){
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        lifecycleScope.launch {
            try {
                val post = Post(
                    id = 0,
                    userId = 0,
                    title = binding.postTitle.text.toString(),
                    body = binding.postBody.text.toString(),
                )
                val response = apiInterface.addPost(post)
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(applicationContext, "new post added, id: [${response.body()?.id}]", Toast.LENGTH_SHORT).show()
                }
            } catch (Ex: Exception) {
                Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}