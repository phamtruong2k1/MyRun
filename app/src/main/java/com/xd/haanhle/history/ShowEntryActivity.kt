package com.xd.haanhle.history

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.xd.haanhle.R
import com.xd.haanhle.roomdatabase.*


class ShowEntryActivity : AppCompatActivity() {

    private lateinit var inputTyple : TextView
    private lateinit var activityTyple : TextView
    private lateinit var dateTime : TextView
    private lateinit var duration : TextView
    private lateinit var distance : TextView
    private lateinit var calories : TextView
    private lateinit var rate : TextView
    private lateinit var comment : TextView
    private lateinit var delete : TextView

    private lateinit var database: EntryDatabase
    private lateinit var databaseDao: EntryDatabaseDao
    private lateinit var repository: EntryRepository
    private lateinit var viewModelFactory: EntryViewModelFactory
    private lateinit var entryViewModel: EntryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_entry)

        val gson = Gson()
        val entry = gson.fromJson<Entry>(intent.getStringExtra("entry"), Entry::class.java)

        inputTyple = findViewById(R.id.inputTyple)
        activityTyple = findViewById(R.id.activityTyple)
        dateTime = findViewById(R.id.dateTime)
        duration = findViewById(R.id.duration)
        distance = findViewById(R.id.distance)
        calories = findViewById(R.id.calories)
        rate = findViewById(R.id.rate)
        comment = findViewById(R.id.comment)
        delete = findViewById(R.id.delete)

        when(entry.inputType){
            0 -> inputTyple.text="Manual Entry"
            1 -> inputTyple.text="GPS"
            2 -> inputTyple.text="Automatic"
        }

        when(entry.activityInput){
            0-> activityTyple.text="Running"
            1-> activityTyple.text="Walking"
            2-> activityTyple.text="Standing"
            3-> activityTyple.text="Cycling"
            4-> activityTyple.text="Hiking"
            5-> activityTyple.text="Downhill Skiing"
            6-> activityTyple.text="Cross-Country Skiin"
            7-> activityTyple.text="Snowboarding"
            8-> activityTyple.text="Skating"
            9-> activityTyple.text="Mountain Biking"
            10-> activityTyple.text="Wheelchair"
            11-> activityTyple.text="Elliptical"
            12-> activityTyple.text="Other"
        }
        val sharedPreference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        dateTime.text= entry.time.toString()+entry.date.toString()
        duration.text= entry.duration.toString() +" secs"
        if (sharedPreference.getString("list_preference_1"," ").equals("km")){
            val diss = entry.distance
            val str = String.format("%.2f",diss)
            distance.text= str+" Kilometters"
        }
        else{
            val diss = entry.distance * 0.6214
            val str = String.format("%.2f",diss)
            distance.text= str+" Miles"
        }
        calories.text= entry.calories.toString() +" cals"
        rate.text= entry.heartRate.toString() +" bpm"
        comment.text = entry.comment

        database = EntryDatabase.getInstance(this)
        databaseDao = database.entryDatabaseDao
        repository = EntryRepository(databaseDao)
        viewModelFactory = EntryViewModelFactory(repository)
        entryViewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        delete.setOnClickListener{
            entryViewModel.deleteEntry(entry.id)
            finish()
        }
    }


}