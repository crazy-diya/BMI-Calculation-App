package com.ktl1.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightET = findViewById<EditText>(R.id.etWeight)
        val heightET = findViewById<EditText>(R.id.etHeight)


        val button = findViewById<Button>(R.id.calculate)

        button.setOnClickListener {
            val weight = weightET.text.toString()
            val height = heightET.text.toString()

            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() * height.toFloat()) / 10000)

                val bmi2Digits = String.format("%2f", bmi).toFloat()

                displayResult(bmi2Digits)
            }


        }


    }

    private fun validateInput(weight: String?, height: String?): Boolean {
        return when {
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Height is Empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }

        }

    }

    private fun displayResult(bmi2Digits: Float) {
        val indexT = findViewById<TextView>(R.id.tvIndex)
        val resultT = findViewById<TextView>(R.id.tvResult)
        val infoT = findViewById<TextView>(R.id.tvInfo)

        indexT.text = bmi2Digits.toString()
        infoT.text = "(normal range is 18,5 - 15.5)"

        var resultText = ""
        var color = 0

        when {
            bmi2Digits < 18.50 -> {
                resultText = "Under Weight"
                color = R.color.purple_500
            }
            bmi2Digits in 18.50..24.99 -> {
                resultText = "Healthy"
                color = R.color.teal_700
            }
            bmi2Digits in 25.00..29.99 -> {
                resultText = "Over Weight"
                color = R.color.white
            }
            bmi2Digits > 29.99 -> {
                resultText = "Obese"
                color = R.color.black
            }

        }

        resultT.setTextColor(ContextCompat.getColor(this, color))
        resultT.text = resultText


    }
}