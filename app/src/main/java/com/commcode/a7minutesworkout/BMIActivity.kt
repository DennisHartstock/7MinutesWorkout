package com.commcode.a7minutesworkout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
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

        binding.rgUnits.setOnCheckedChangeListener { _, checkedId ->
            changeUnits(checkedId)
        }

        binding.btnCalculate.setOnClickListener {
            if (!validateInputs()) {
                Toast.makeText(this, "Enter valid data", Toast.LENGTH_SHORT).show()
            } else {
                val height = binding.etHeight.text.toString().toFloat()
                val weight = binding.etWeight.text.toString().toFloat()
                val bmi = (weight / height / height) * 10_000
                displayBMI(bmi)
            }
        }
    }

    private fun changeUnits(checkedId: Int) {
        if (checkedId == R.id.rbMetricUnits) {
            binding.tilHeight.hint = "Height in cm"
            binding.tilWeight.hint = "Weight in kg"
        }
        if (checkedId == R.id.rbUsUnits) {
            binding.tilHeight.hint = "Height in feet"
            binding.tilWeight.hint = "Weight in pounds"
        }
        binding.etHeight.text?.clear()
        binding.etWeight.text?.clear()
        binding.llBMIResult.visibility = View.INVISIBLE
    }

    private fun displayBMI(bmi: Float) {
        var bmiType = ""
        var bmiDescription = ""

        when {
            bmi <= 15f -> {
                bmiType = "Very severely underweight"
                bmiDescription = "You really need to take better care of yourself! Eat more!"
            }
            bmi > 15f && bmi <= 16f -> {
                bmiType = "Severely underweight"
                bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
            }
            bmi > 16f && bmi <= 18.5 -> {
                bmiType = "Underweight"
                bmiDescription = "You really need to take better care of yourself! Eat more!"
            }
            bmi > 18.5 && bmi <= 25f -> {
                bmiType = "Normal"
                bmiDescription = "Congratulations! You are in a good shape!"
            }
            bmi > 25f && bmi <= 30f -> {
                bmiType = "Overweight"
                bmiDescription = "You really need to take care of your yourself! Workout maybe!"
            }
            bmi > 30f && bmi <= 35f -> {
                bmiType = "Moderately obese"
                bmiDescription = "You really need to take care of your yourself! Workout maybe!"
            }
            bmi > 35f && bmi <= 40f -> {
                bmiType = "Severely obese"
                bmiDescription = "You are in a very dangerous condition! Act now!"
            }
            bmi > 40f -> {
                bmiType = "Very severely obese"
                bmiDescription = "You are in a very dangerous condition! Act now!"
            }
        }

        binding.llBMIResult.visibility = View.VISIBLE
        binding.tvBMIValue.text = String.format("%.2f", bmi)
        binding.tvBMIType.text = bmiType
        binding.tvBMIDescription.text = bmiDescription
    }

    private fun validateInputs(): Boolean {
        return (binding.etHeight.text.toString().isNotEmpty() && binding.etWeight.text.toString()
            .isNotEmpty())
    }
}