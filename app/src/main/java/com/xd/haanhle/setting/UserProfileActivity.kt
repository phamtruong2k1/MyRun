package com.xd.haanhle.setting

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import com.xd.haanhle.MyRunsDialogFragment
import com.xd.haanhle.R

class UserProfileActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var tempImgUri: Uri
    private val tempImgFileName = "xd_temp_img.jpg"
    private lateinit var cameraResult: ActivityResultLauncher<Intent>
    private lateinit var myViewModel: MyViewModel

    private lateinit var input_name: EditText
    private lateinit var input_email: EditText
    private lateinit var input_phone: EditText
    private lateinit var input_class: EditText
    private lateinit var input_major: EditText
    private lateinit var input_gender: RadioGroup

    private val PREFERENCE_NAME = "my preference name"
    private val NAME_KEY = "Name key"
    private val EMAIL_KEY = "Email key"
    private val PHONE_KEY = "Phone key"
    private val CLASS_KEY = "Class key"
    private val MAJOR_KEY = "Major key"
    private val GENDER_KEY = "Gender key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile)
        imageView = findViewById(R.id.imageProfile)
        textView = findViewById(R.id.text_view)

        loadData()
    }


    fun onChangePhotoClicked(view: View) {
        val myDialog = MyRunsDialogFragment()
        val bundle = Bundle()
        bundle.putInt(MyRunsDialogFragment.DIALOG_KEY, MyRunsDialogFragment.SETPROFILE_DIALOG)
        myDialog.arguments = bundle
        myDialog.show(supportFragmentManager, "my dialog")


    }

    fun onSaveClicked(view: View) {
        val savedName:String = input_name.text.toString()
        val savedEmail:String = input_email.text.toString()
        val savedPhone:String = input_phone.text.toString()
        val savedClass:String = input_class.text.toString()
        val savedMajor:String = input_major.text.toString()

        val checkedRadioButtonID: Int = input_gender.checkedRadioButtonId
        val sharedPreferences: SharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.clear()
        editor.putString(NAME_KEY, savedName)
        editor.putString(EMAIL_KEY, savedEmail)
        editor.putString(PHONE_KEY, savedPhone)
        editor.putString(CLASS_KEY, savedClass)
        editor.putString(MAJOR_KEY, savedMajor)
        editor.putInt(GENDER_KEY, checkedRadioButtonID)
        editor.commit()
        finish()
    }



    //Load method
    fun loadData() {
        input_name = findViewById(R.id.input_name)
        input_email = findViewById(R.id.input_email)
        input_phone = findViewById(R.id.input_phone)
        input_class = findViewById(R.id.input_class)
        input_major = findViewById(R.id.input_major)
        input_gender = findViewById(R.id.input_gender)

        val sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        val savedName: String? = sharedPreferences.getString(NAME_KEY, "")
        val savedEmail: String? = sharedPreferences.getString(EMAIL_KEY, "")
        val savedPhone: String? = sharedPreferences.getString(PHONE_KEY, "")
        val savedClass: String? = sharedPreferences.getString(CLASS_KEY, "")
        val savedMajor: String? = sharedPreferences.getString(MAJOR_KEY, "")

        val savedGender: Int = sharedPreferences.getInt(GENDER_KEY, -1)

        input_name.setText(savedName)
        input_email.setText(savedEmail)
        input_phone.setText(savedPhone)
        input_class.setText(savedClass)
        input_major.setText(savedMajor)

        val radioButton: RadioButton? = findViewById(savedGender) //XD: findViewById() could return null so radioButton has to be decleared as a nullable using "?".
        if (radioButton != null)
            radioButton.isChecked = true
    }

    fun onCancelClicked(view: View) {
        finish()
    }

}