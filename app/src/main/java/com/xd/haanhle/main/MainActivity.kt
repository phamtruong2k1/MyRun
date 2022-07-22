package com.xd.haanhle.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.xd.haanhle.*
import com.xd.haanhle.history.HistoryFragment
import com.xd.haanhle.setting.SettingFragment
import com.xd.haanhle.start.StartFragment
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var settingFragment: SettingFragment
    private lateinit var startFragment: StartFragment
    private lateinit var historyFragment: HistoryFragment
    private lateinit var fragments: ArrayList<Fragment>
    private lateinit var tab: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var myFragmentStateAdapter: MyFragmentStateAdapter
    private lateinit var tabLayoutMediator: TabLayoutMediator
    private lateinit var tabConfigurationStrategy: TabConfigurationStrategy
    private val TAB_TEXT = arrayOf("Start", "History", "Setting")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Util.checkPermissions(this)

    //    val startFrag = StartFragment()
    //    supportFragmentManager.beginTransaction().add(android.R.id.content, startFrag).commit()

        settingFragment = SettingFragment()
        startFragment = StartFragment()
        historyFragment = HistoryFragment()

        fragments =ArrayList()
        fragments.add(startFragment)
        fragments.add(historyFragment)
        fragments.add(settingFragment)

        tab = findViewById(R.id.tab)
        viewPager = findViewById(R.id.viewpager)
        myFragmentStateAdapter = MyFragmentStateAdapter(this, fragments)
        viewPager.adapter = myFragmentStateAdapter

        tabConfigurationStrategy = TabConfigurationStrategy { tab: TabLayout.Tab, postition: Int ->
            tab.text = TAB_TEXT[postition]
        }


        tabLayoutMediator = TabLayoutMediator(tab, viewPager, tabConfigurationStrategy)
        tabLayoutMediator.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        tabLayoutMediator.detach()
    }


}