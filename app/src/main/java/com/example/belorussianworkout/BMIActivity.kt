package com.example.belorussianworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.math.BigDecimal
import java.math.RoundingMode
import androidx.appcompat.widget.Toolbar


class BMIActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)

        setSupportActionBar(findViewById(R.id.toolbar_bmi_activity))

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "CALCULATE BMI"
        }
        findViewById<Toolbar>(R.id.toolbar_bmi_activity).setNavigationOnClickListener {
            onBackPressed()
        }

        findViewById<Button>(R.id.btnCalculateUnits).setOnClickListener{
            if (validateMetricUnits()){
                val heightValue: Float = findViewById<EditText>(R.id.etUnitHeight).text.toString().toFloat() /100
                val weightValue: Float = findViewById<EditText>(R.id.etUnitWeight).text.toString().toFloat()

                val bmi = weightValue / (heightValue*heightValue)

                displayBMIResult(bmi)
            }else {
                Toast.makeText(this@BMIActivity, "Please enter valid values.", Toast.LENGTH_SHORT)
                    .show()
            }

        }

    }


    private fun displayBMIResult(bmi: Float){
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        findViewById<TextView>(R.id.tvYourBMI).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvBMIValue).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvBMIType).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvBMIDescription).visibility = View.VISIBLE

        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()


        findViewById<TextView>(R.id.tvBMIValue).text = bmiValue
        findViewById<TextView>(R.id.tvBMIType).text = bmiLabel
        findViewById<TextView>(R.id.tvBMIDescription).text = bmiDescription
    }

    private fun validateMetricUnits(): Boolean{
        var isValid = true
        if(findViewById<EditText>(R.id.etUnitWeight).text.toString().isEmpty())
            isValid = false
        else if (findViewById<EditText>(R.id.etUnitHeight).text.toString().isEmpty())
            isValid = false

        return isValid

    }

}