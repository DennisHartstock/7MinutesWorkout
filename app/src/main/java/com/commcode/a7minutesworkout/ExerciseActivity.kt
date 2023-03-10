package com.commcode.a7minutesworkout

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.commcode.a7minutesworkout.databinding.ActivityExerciseBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.util.*

class ExerciseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExerciseBinding
    private var textToSpeech: TextToSpeech? = null
    private var restTimer: CountDownTimer? = null
    private var exerciseTimer: CountDownTimer? = null
    private var restTimerDurationInSeconds: Long = 60
    private var exerciseTimerDurationInSeconds: Long = 30
    private var restProgress = 0
    private var exerciseProgress = 0
    private lateinit var exerciseList: ArrayList<Exercise>
    private var currentExercise = 0
    private lateinit var restPlayer: MediaPlayer
    private lateinit var exercisePlayer: MediaPlayer
    private lateinit var exerciseAdapter: ExerciseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.tbExercise)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        exerciseList = Constants.defaultExerciseList()
        binding.tbExercise.setNavigationOnClickListener { cancelWorkoutDialog() }

        setRestProgressBar()
        setupExerciseRecyclerView()

        textToSpeech = TextToSpeech(this) { status ->
            run {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech?.language = Locale.US
                }
            }
        }
    }

    private fun setupExerciseRecyclerView() {
        exerciseAdapter = ExerciseAdapter(exerciseList)
        binding.rvExercises.adapter = exerciseAdapter
    }

    private fun speakOut(text: String) {
        textToSpeech?.speak(text, TextToSpeech.QUEUE_ADD, null, null)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        cancelWorkoutDialog()
    }

    private fun cancelWorkoutDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Warning")
            .setMessage("Do you really go back? It will cancel the workout")
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
        val text = buildString {
            append("Do ")
            append(exerciseList[currentExercise].name)
        }
        speakOut(text)
        binding.pbExercise.progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(exerciseTimerDurationInSeconds * 1_000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding.ivExercise.setImageResource(exerciseList[currentExercise].image)
                binding.ivExercise.visibility = View.VISIBLE
                binding.tvExercise.text = text
                binding.pbExercise.max = exerciseTimerDurationInSeconds.toInt()
                binding.pbExercise.progress = (millisUntilFinished / 1_000).toInt()
                binding.tvTimer.text = (millisUntilFinished / 1_000).toString()
            }

            override fun onFinish() {
                exerciseList[currentExercise].isSelected = false
                exerciseList[currentExercise].isCompleted = true
                currentExercise++

                try {
                    exercisePlayer = MediaPlayer.create(applicationContext, R.raw.tick)
                    exercisePlayer.start()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                if (currentExercise < exerciseList.size) {
                    setRestProgressBar()
                    exerciseAdapter.notifyDataSetChanged()
                } else {
                    val congratulations = getString(R.string.congratulations)
                    speakOut(congratulations)
                    Thread.sleep(2_000)
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()
    }

    private fun setRestProgressBar() {
        val text = buildString {
            append("Get ready for ")
            append(exerciseList[currentExercise].name)
        }
        speakOut(text)
        binding.pbExercise.progress = restProgress
        restTimer = object : CountDownTimer(restTimerDurationInSeconds * 1_000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding.ivExercise.visibility = View.INVISIBLE
                binding.tvExercise.text = text
                binding.pbExercise.max = restTimerDurationInSeconds.toInt()
                binding.pbExercise.progress = (millisUntilFinished / 1_000).toInt()
                binding.tvTimer.text = (millisUntilFinished / 1_000).toString()
            }

            override fun onFinish() {
                exerciseList[currentExercise].isSelected = true
                setExerciseProgressBar()
                exerciseAdapter.notifyDataSetChanged()
                try {
                    restPlayer = MediaPlayer.create(applicationContext, R.raw.start)
                    restPlayer.start()
                } catch (e: Exception) {
                    e.printStackTrace()
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
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        exercisePlayer.stop()
        restPlayer.stop()
    }
}