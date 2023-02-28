package com.commcode.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.commcode.a7minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishBinding
    private var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.tbFinish)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val historyDao = (application as WorkoutApp).database.historyDao()
        addDateToDatabase(historyDao)

        binding.tbFinish.setNavigationOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        textToSpeech = TextToSpeech(this) { status ->
            run {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech?.language = Locale.US
                }
            }
        }

        binding.tvFinish.text = getString(R.string.congratulations)
    }

    private fun addDateToDatabase(historyDao: HistoryDao) {
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time

        val simpleDateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = simpleDateFormat.format(dateTime)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
            Log.i("date", "Date: $date")
        }
    }
}
