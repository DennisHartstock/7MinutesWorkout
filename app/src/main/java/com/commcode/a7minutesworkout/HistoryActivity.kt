package com.commcode.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.commcode.a7minutesworkout.databinding.ActivityHistoryBinding

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
    }
}