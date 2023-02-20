package com.commcode.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.commcode.a7minutesworkout.databinding.ActivityBmiBinding

class BMIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.tbBMI)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.tbBMI.setNavigationOnClickListener {
            val intent = Intent(this@BMIActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}