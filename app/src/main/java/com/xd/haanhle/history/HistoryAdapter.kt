package com.xd.haanhle.history

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.xd.haanhle.R
import com.xd.haanhle.roomdatabase.Entry

class HistoryAdapter(private val activity: Activity, private val context: Context, private var entryList: List<Entry>) : BaseAdapter(){

    override fun getItem(position: Int): Any {
        return entryList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return entryList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.layout_adapter,null)

        val textFirst = view.findViewById(R.id.first) as TextView
        val textLast = view.findViewById(R.id.last) as TextView

        var first = ""
        when(entryList.get(position).inputType){
            0 -> first="Manual Entry"
            1 -> first="GPS"
            2 -> first="Automatic"
        }
        first+=": "
        when(entryList.get(position).activityInput){
            0-> first+="Running"
            1-> first+="Walking"
            2-> first+="Standing"
            3-> first+="Cycling"
            4-> first+="Hiking"
            5-> first+="Downhill Skiing"
            6-> first+="Cross-Country Skiin"
            7-> first+="Snowboarding"
            8-> first+="Skating"
            9-> first+="Mountain Biking"
            10-> first+="Wheelchair"
            11-> first+="Elliptical"
            12-> first+="Other"
        }

        textFirst.text = first+" "+entryList.get(position).time+" "+entryList.get(position).date

        var last = ""
        val sharedPreference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity.applicationContext)
        if (sharedPreference.getString("list_preference_1"," ").equals("km")){
            val diss = entryList.get(position).distance
            val str = String.format("%.2f",diss)
            last = str+" Kilometters"
        }
        else{
            val diss = entryList.get(position).distance * 0.6214
            val str = String.format("%.2f",diss)
            last= str+" Miles"
        }
        textLast.text = last+", "+entryList.get(position).duration.toString()+" secs"

        view.setOnClickListener{
            var intent = Intent(view.context,ShowEntryActivity::class.java)
            val gson = Gson()
            intent.putExtra("entry",gson.toJson(entryList.get(position)))
            activity.startActivity(intent)
        }

        return view
    }

    fun replace(newEntryList: List<Entry>){
        entryList = newEntryList
    }

}