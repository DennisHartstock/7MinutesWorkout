package com.commcode.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.commcode.a7minutesworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.tbHistory)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tbHistory.setNavigationOnClickListener {
            val intent = Intent(this@HistoryActivity, MainActivity::class.java)
            startActivity(intent)
        }

        val historyDao = (application as WorkoutApp).database.historyDao()
        getAllDates(historyDao)
    }

    private fun getAllDates(historyDao: HistoryDao) {
        lifecycleScope.launch {
            historyDao.getAllDates().collect {
                if (it.isNotEmpty()) {
                    binding.rvHistory.visibility = View.VISIBLE
                    binding.tvNoData.visibility = View.GONE

                    val dates = ArrayList<String>()
                    for (i in it) {
                        dates.add(i.date)
                    }

                    val historyAdapter = HistoryAdapter(dates)
                    binding.rvHistory.adapter = historyAdapter
                } else {
                    binding.rvHistory.visibility = View.GONE
                    binding.tvNoData.visibility = View.VISIBLE
                }
            }
        }
    }
}