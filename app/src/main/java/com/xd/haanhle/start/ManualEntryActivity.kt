package com.xd.haanhle.start

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.xd.haanhle.MyRunsDialogFragment
import com.xd.haanhle.R
import com.xd.haanhle.history.HistoryFragment
import com.xd.haanhle.roomdatabase.*
import java.util.*

class ManualEntryActivity: AppCompatActivity(), DialogInterface.OnClickListener , DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{
    private val MANUAL_INPUT = arrayOf(
        "Date", "Time", "Duration", "Distance", "Calories", "Heart rate", "Comment"
    )

    private lateinit var manualListView: ListView
    private lateinit var database: EntryDatabase
    private lateinit var databaseDao: EntryDatabaseDao
    private lateinit var repository: EntryRepository
    private lateinit var viewModelFactory: EntryViewModelFactory
    private lateinit var entryViewModel: EntryViewModel
    private lateinit var viewa : View

    var date = ""
    var time = ""
    var duration = 0.0
    var distance = 0.0
    var calo = 0.0
    var rate = 0.0
    var typleInput = -1
    var activityTyple = -1
    var comment = ""
    var yy = 0
    var dd= 0
    var mm=0;
    var hh = 0
    var mi = 0
    var ss =0;
    var title = ""
    var desception = ""
    val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manualentry)

        typleInput = intent.getIntExtra("typleInput",0)
        activityTyple = intent.getIntExtra("activityTyple",0)

        yy = calendar.get(Calendar.YEAR)
        mm = calendar.get(Calendar.MONTH)
        dd = calendar.get(Calendar.DAY_OF_MONTH)

        hh = calendar.get(Calendar.HOUR)
        mi = calendar.get(Calendar.MINUTE)
        ss = calendar.get(Calendar.SECOND)

        database = EntryDatabase.getInstance(this)
        databaseDao = database.entryDatabaseDao
        repository = EntryRepository(databaseDao)
        viewModelFactory = EntryViewModelFactory(repository)
        entryViewModel = ViewModelProvider(this, viewModelFactory).get(EntryViewModel::class.java)

        manualListView = findViewById(R.id.manualListView)

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, MANUAL_INPUT
        )
        manualListView.adapter = arrayAdapter

        val myDialog = MyRunsDialogFragment()
        val bundle = Bundle()

        manualListView.setOnItemClickListener() { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            Toast.makeText(this, "Clicked item : $position", Toast.LENGTH_SHORT).show()
            if (position == 0) {
                choiseDate()

            } else if (position == 1) {
                choiseTime()

            } else if (position == 2) {
                //duration
                title = "Duration"
                selectOb("Duration")
            } else if (position == 3) {
                //distance
                title = "Distance"
                selectOb("Distance")
            } else if (position == 4) {
                //calories
                title = "Calories"
                selectOb("Calories")
            } else if (position == 5) {
                //heart rate
                title = "Heart rate"
                selectOb("Heart rate")
            } else if (position == 6) {
                //comment
                title = "Comment"
                selectOb("Comment")
            }
        }
    }

    private fun selectOb(s: String) {
        val builder = AlertDialog.Builder(this)
        viewa = layoutInflater.inflate(R.layout.dialog_comment, null)
        builder.setView(viewa)
        builder.setTitle(s)
        builder.setPositiveButton("Ok", this )
        builder.setNegativeButton("Cancel", this)
        desception = viewa.findViewById<EditText>(R.id.txt_comment).text.toString()
        builder.create().show()

    }

    override fun onClick(dialog: DialogInterface, item: Int) {
        if (item == DialogInterface.BUTTON_POSITIVE) {
            desception = viewa.findViewById<EditText>(R.id.txt_comment).text.toString()
            Toast.makeText(this,desception,Toast.LENGTH_SHORT).show()
            when(title){
                "Duration" -> duration = desception.toDouble()
                "Distance" -> distance = desception.toDouble()
                "Calories" -> calo = desception.toDouble()
                "Heart rate" -> rate = desception.toDouble()
                "Comment" -> comment = desception

            }
        } else if (item == DialogInterface.BUTTON_NEGATIVE) {

        }
    }

    private fun choiseDate(){
        DatePickerDialog(this,this,yy,mm,dd).show()
    }
    private fun choiseTime(){
        TimePickerDialog(this,this,yy,mm,true).show()
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        yy=year
        mm=monthOfYear
        dd = dayOfMonth
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        hh=hourOfDay
        mi=minute
    }


    fun btnManualSave(v: View?) {
        //Toast.makeText(this, "Clicked item : Zoey", Toast.LENGTH_SHORT).show()
        saveToDatabase()
        finish()
    }

    fun saveToDatabase() {
        date =  "thg "+mm+" "+dd+" "+yy
        time =  ""+hh+":"+mi+":"+ss+" "
        var entry : Entry = Entry(0, typleInput ,activityTyple,time,date,duration,distance,calo,rate, comment)
        entryViewModel.insert(entry)

    }

    fun btnManualCancel(v: View?) {
        //Toast.makeText(this, "Clicked item : Zoey", Toast.LENGTH_SHORT).show()
        finish()
    }



}
