package com.commcode.a7minutesworkout

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var flStart: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        flStart.setOnClickListener {
            Toast.makeText(this, "Start clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViews() {
        flStart = findViewById(R.id.flStart)
    }
}