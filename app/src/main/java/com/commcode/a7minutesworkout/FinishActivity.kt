package com.commcode.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import com.commcode.a7minutesworkout.databinding.ActivityFinishBinding
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
}
