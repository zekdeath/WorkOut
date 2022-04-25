package com.example.belorussianworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(findViewById(R.id.toolbar_history_activity))

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = "HISTORY"
        }
        findViewById<Toolbar>(R.id.toolbar_history_activity).setNavigationOnClickListener {
            onBackPressed()
        }

        getAllCompletedDates()





    }

    private fun getAllCompletedDates() {
        val dbHandler = SqliteOpenHelper(this, null)
        val allCompletedDatesList = dbHandler.getAllCompletedDatesList()

        if(allCompletedDatesList.size > 0){
            findViewById<TextView>(R.id.tvHistory).visibility = View.VISIBLE
            findViewById<RecyclerView>(R.id.rvHistory).visibility = View.VISIBLE
            findViewById<TextView>(R.id.tvNoDataAvailable).visibility = View.GONE

            findViewById<RecyclerView>(R.id.rvHistory).layoutManager = LinearLayoutManager(this)
            val historyAdapter = HistoryAdapter(this, allCompletedDatesList)
            findViewById<RecyclerView>(R.id.rvHistory).adapter = historyAdapter

        }else{
            findViewById<TextView>(R.id.tvHistory).visibility = View.GONE
            findViewById<RecyclerView>(R.id.rvHistory).visibility = View.GONE
            findViewById<TextView>(R.id.tvNoDataAvailable).visibility = View.VISIBLE
        }

    }


}