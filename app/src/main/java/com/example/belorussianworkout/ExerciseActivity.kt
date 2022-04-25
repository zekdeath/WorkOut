package com.example.belorussianworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModule>? = null
    private var currentExercisePosition = -1

    private var tts: TextToSpeech? = null
    private var player: MediaPlayer? = null

    private var exerciseAdapter: ExerciseStatusAdaptor? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excercise)

        setSupportActionBar(findViewById(R.id.toolbar_exercise_activity))
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
        }
        findViewById<Toolbar>(R.id.toolbar_exercise_activity).setNavigationOnClickListener {customDialogForBackButton()}


        tts = TextToSpeech(this, this)

        exerciseList = Constants.defaultExerciseList()
        setupRestView()

        setupExerciseStatusRecyclerView()




    }

    override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player!=null){
            player!!.stop(
            )
        }

        super.onDestroy()
    }

    private fun setRestProgressBar() {

        findViewById<ProgressBar>(R.id.progressBar).progress = restProgress
        restTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(p0: Long) {
                restProgress++
                findViewById<ProgressBar>(R.id.progressBar).progress = 10 - restProgress
                findViewById<TextView>(R.id.tvTimer).text = (10 - restProgress).toString()
            }

            override fun onFinish() {

                currentExercisePosition++
                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setupExerciseView()
            }
        }.start()


    }

    private fun setupRestView() {
        try{
            player = MediaPlayer.create(applicationContext, R.raw.start_button)
            player!!.isLooping = false
            player!!.start()
        }catch(e: Exception){
            e.printStackTrace()
        }



        findViewById<LinearLayout>(R.id.llRestView).visibility = View.VISIBLE
        findViewById<LinearLayout>(R.id.llExerciseView).visibility = View.INVISIBLE

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0

        }

        speakOut(exerciseList!![currentExercisePosition+1].getName())
        findViewById<TextView>(R.id.tv_pre_exercise).text = exerciseList!![currentExercisePosition+1].getName()
        setRestProgressBar()



    }

    private fun setExerciseProgressBar() {
        findViewById<ProgressBar>(R.id.progressExerciseBar).progress = exerciseProgress
        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                exerciseProgress++
                findViewById<ProgressBar>(R.id.progressExerciseBar).progress = 30 - exerciseProgress
                findViewById<TextView>(R.id.tvExerciseTimer).text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition <exerciseList?.size!! - 1){
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)
                }

            }
        }.start()



    }

    private fun setupExerciseView() {
        findViewById<LinearLayout>(R.id.llRestView).visibility = View.INVISIBLE
        findViewById<LinearLayout>(R.id.llExerciseView).visibility = View.VISIBLE

        if(exerciseTimer!=null){
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }



        setExerciseProgressBar()

        findViewById<ImageView>(R.id.iv_image).setImageResource(exerciseList!![currentExercisePosition].getImage())
        findViewById<TextView>(R.id.tvExerciseName).setText(exerciseList!![currentExercisePosition].getName())


    }

    override fun onInit(p0: Int) {
        if(p0 == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "The language specified is not supported")
            }
        }else {
            Log.e("TTS", "Initialization failed")
        }
    }

    private fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")

    }

    private fun setupExerciseStatusRecyclerView(){
        findViewById<RecyclerView>(R.id.rvExerciseStatus).layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdaptor(exerciseList!!, this)
        findViewById<RecyclerView>(R.id.rvExerciseStatus).adapter = exerciseAdapter
    }

    private fun customDialogForBackButton(){
        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.back_confirmation)
        customDialog.findViewById<TextView>(R.id.tvYes).setOnClickListener{
            finish()
            customDialog.dismiss()

        }
        customDialog.findViewById<TextView>(R.id.tvNo).setOnClickListener{customDialog.dismiss()}


        customDialog.show()
    }
}