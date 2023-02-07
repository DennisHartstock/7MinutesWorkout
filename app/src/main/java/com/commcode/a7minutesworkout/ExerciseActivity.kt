package com.commcode.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.commcode.a7minutesworkout.databinding.ActivityExerciseBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.tbExercise)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding.tbExercise.setNavigationOnClickListener { endWorkoutDialog() }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        endWorkoutDialog()
    }

    private fun endWorkoutDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Warning")
            .setMessage("Do you really go back? It will abort the workout")
            .setPositiveButton("Yes") { _, _ ->
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}