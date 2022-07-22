package com.xd.haanhle.start

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.xd.haanhle.R

class StartFragment: Fragment() {
    private lateinit var spinnerInputTyle: Spinner
    private lateinit var spinnerActivityTyle: Spinner
    private lateinit var intent: Intent
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_start, container, false)


        spinnerInputTyle = view.findViewById(R.id.spinnerInputType)
        spinnerActivityTyle = view.findViewById(R.id.spinnerAccType)
        val btnStart = view.findViewById<Button>(R.id.btn_start)

        btnStart.setOnClickListener{
            val item = spinnerInputTyle.selectedItemPosition
            if(item == 0) {
                //Toast.makeText(getActivity(), "Manual Entry Selected", Toast.LENGTH_SHORT).show()
                intent = Intent (getActivity(), ManualEntryActivity::class.java)
            } else if (item == 1) {
                //Toast.makeText(getActivity(), "GPS Selected", Toast.LENGTH_SHORT).show()
                intent = Intent (getActivity(), MapDisplayActivity::class.java)
            } else if (item == 2) {
                //Toast.makeText(getActivity(), "Automatic", Toast.LENGTH_SHORT).show()
                intent = Intent (getActivity(), MapDisplayActivity::class.java)
            }
            intent.putExtra("typleInput",spinnerInputTyle.selectedItemPosition)
            intent.putExtra("activityTyple",spinnerActivityTyle.selectedItemPosition)
            startActivity(intent)


        }

        return view

    }
}