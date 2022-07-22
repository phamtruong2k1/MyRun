package com.xd.haanhle.start

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.xd.haanhle.R

class MapDisplayActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapdisplay)

    }
    fun onSaveClicked(v: View?) {
        finish()
    }

    fun onCancelClicked(v: View?) {
        finish()
    }
}