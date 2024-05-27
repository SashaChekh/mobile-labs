package com.example.Lab9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.Lab9.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        lateinit var database: HealthTrackerDB
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext,
            HealthTrackerDB::class.java, "tracker_database"
        ).build()
        val trackerDao = database.trackerDao()

        binding.addBtn.setOnClickListener{
            val date = binding.date.text.toString()
            val steps = binding.steps.text.toString().toInt()
            val calories = binding.calories.text.toString().toInt()
            val healthTracker = HealthTracker(date = date, steps = steps, calories = calories)
            GlobalScope.launch {
                trackerDao.insertAll(healthTracker)
            }
            Toast.makeText( applicationContext, "Created", Toast.LENGTH_LONG).show()

            GlobalScope.launch {
                val trackers = trackerDao.getAll()
                var trackersInfo = ""
                trackers.forEach{
                    trackersInfo += "${it.id}: ${it.date} ${it.steps} ${it.calories}\n"
                }
                runOnUiThread{
                    binding.textView.text = trackersInfo
                }
            }
        }

        binding.allTrackers.setOnClickListener{
            GlobalScope.launch {
                val trackers = trackerDao.getAll()
                var trackersInfo = ""
                trackers.forEach{
                    trackersInfo += "${it.id}: ${it.date} ${it.steps} ${it.calories}\n"
                }
                runOnUiThread{
                    binding.textView.text = trackersInfo
                }
            }
        }

        binding.deleteButton.setOnClickListener{
            val trackerId = binding.idText.text.toString().toIntOrNull()

            if (trackerId == null || trackerId < 0) {
                Toast.makeText(this, "Invalid index.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                trackerDao.deleteById(trackerId)

                GlobalScope.launch {
                    val trackers = trackerDao.getAll()
                    var trackersInfo = ""
                    trackers.forEach{
                        trackersInfo += "${it.id}: ${it.date} ${it.steps} ${it.calories}\n"
                    }
                    runOnUiThread{
                        binding.textView.text = trackersInfo
                    }
                }
            }
        }

        GlobalScope.launch {
            val trackers = trackerDao.getAll()
            var trackersInfo = ""
            trackers.forEach{
                trackersInfo += "${it.id}: ${it.date} ${it.steps} ${it.calories}\n"
            }
            runOnUiThread{
                binding.textView.text = trackersInfo
            }
        }
    }
}

