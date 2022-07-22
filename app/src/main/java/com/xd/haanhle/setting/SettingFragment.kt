package com.xd.haanhle.setting

import android.content.Intent
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.xd.haanhle.R

class SettingFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings);
        setPreferencesFromResource(R.xml.settings, rootKey)

        val preference: Preference? = findPreference("change_user_profile")

        preference?.setOnPreferenceClickListener(object : Preference.OnPreferenceClickListener {
            override fun onPreferenceClick(preference: Preference?): Boolean {
                val intent = Intent(requireContext(), UserProfileActivity::class.java)
                startActivity(intent)
                return true
            }
        })

    }
}