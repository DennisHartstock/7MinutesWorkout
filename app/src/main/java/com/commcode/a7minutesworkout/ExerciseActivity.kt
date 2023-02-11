package com.commcode.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.commcode.a7minutesworkout.databinding.ActivityExerciseBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseProgress = 0
    private lateinit var exerciseList: ArrayList<Exercise>
    private var currentExercise = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.tbExercise)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        exerciseList = Constants.defaultExerciseList()
        binding.tbExercise.setNavigationOnClickListener { endWorkoutDialog() }
        setRestProgressBar()
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
                restTimer?.cancel()
                exerciseTimer?.cancel()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun setExerciseProgressBar() {
        binding.pbExercise.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(30_000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding.ivExercise.setImageResource(exerciseList[currentExercise].image)
                binding.ivExercise.visibility = View.VISIBLE
                binding.tvExercise.text = buildString {
                    append("DO ")
                    append(exerciseList[currentExercise].name)
                    append(" FOR")
                }
                binding.pbExercise.max = 30
                binding.pbExercise.progress = (millisUntilFinished / 1_000).toInt()
                binding.tvTimer.text = (millisUntilFinished / 1_000).toString()
            }

            override fun onFinish() {
                currentExercise++
                setRestProgressBar()
            }

        }.start()
    }

    private fun setRestProgressBar() {
        binding.pbExercise.progress = restProgress
        restTimer = object : CountDownTimer(10_000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding.ivExercise.visibility = View.INVISIBLE
                binding.tvExercise.text = getString(R.string.tv_rest)
                binding.pbExercise.max = 10
                binding.pbExercise.progress = (millisUntilFinished / 1_000).toInt()
                binding.tvTimer.text = (millisUntilFinished / 1_000).toString()
            }

            override fun onFinish() {
                if (currentExercise < exerciseList.size) {
                    setExerciseProgressBar()
                } else {
                    binding.tvExercise.text = getString(R.string.cogratulations)
                }
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        restTimer?.cancel()
        exerciseTimer?.cancel()
        restProgress = 0
        exerciseProgress = 0
    }
}