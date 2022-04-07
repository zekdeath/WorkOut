package com.example.belorussianworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<LinearLayout>(R.id.llStart).setOnClickListener { Toast.makeText(this, "Here we will start...", Toast.LENGTH_SHORT).show() }
    }


}