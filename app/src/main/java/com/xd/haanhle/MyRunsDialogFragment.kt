package com.xd.haanhle

import android.app.*
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.DialogFragment
import com.xd.haanhle.setting.CameraActivity
import com.xd.haanhle.setting.MyViewModel
import java.util.*


class MyRunsDialogFragment : DialogFragment(), DialogInterface.OnClickListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener   {
    companion object{
        const val DIALOG_KEY = "dialog"
        const val DATE_DIALOG = 0
        const val TIME_DIALOG = 1
        const val DURATION_DIALOG = 2
        const val DISTANCE_DIALOG = 3
        const val CALORIES_DIALOG = 4
        const val HEART_RATE_DIALOG = 5
        const val COMMENT_DIALOG = 6
        const val SETPROFILE_DIALOG = 7

    }

    private val calendar = Calendar.getInstance()

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView
    private lateinit var tempImgUri: Uri
    private val tempImgFileName = "xd_temp_img.jpg"
    private lateinit var cameraResult: ActivityResultLauncher<Intent>
    private lateinit var myViewModel: MyViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        lateinit var ret: Dialog
        val bundle = arguments
        val dialogId = bundle?.getInt(DIALOG_KEY)
        val builder = AlertDialog.Builder(requireActivity())

        if (dialogId == DATE_DIALOG) {
            val datePickerDialog = DatePickerDialog(
                requireActivity(), this,calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            ret = datePickerDialog
        }
        else if (dialogId == TIME_DIALOG) {
            val timePickerDialog = TimePickerDialog(
                requireActivity(), this,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true
            )

            ret = timePickerDialog

        }
        else if (dialogId == DURATION_DIALOG) {
            val view: View = requireActivity().layoutInflater.inflate(R.layout.dialog_comment, null)

            builder.setView(view)
            builder.setTitle("Distance")
            builder.setPositiveButton("ok", this)
            builder.setNegativeButton("cancel", this)
            ret = builder.create()

        }
        else if (dialogId == DISTANCE_DIALOG) {
            val view: View = requireActivity().layoutInflater.inflate(R.layout.dialog_comment, null)

            builder.setView(view)
            builder.setTitle("Distance")
            builder.setPositiveButton("ok", this)
            builder.setNegativeButton("cancel", this)
            ret = builder.create()

        }
        else if (dialogId == CALORIES_DIALOG) {
            val view: View = requireActivity().layoutInflater.inflate(R.layout.dialog_comment, null)

            builder.setView(view)
            builder.setTitle("Calories")
            builder.setPositiveButton("ok", this)
            builder.setNegativeButton("cancel", this)
            ret = builder.create()

        }
        else if (dialogId == HEART_RATE_DIALOG) {
            val view: View = requireActivity().layoutInflater.inflate(R.layout.dialog_comment, null)

            builder.setView(view)
            builder.setTitle("Heart rate")
            builder.setPositiveButton("ok", this)
            builder.setNegativeButton("cancel", this)
            ret = builder.create()

        }else if (dialogId == COMMENT_DIALOG) {
                ret.findViewById<EditText>(R.id.txt_comment).setHint("HINT")

                val view: View = requireActivity().layoutInflater.inflate(R.layout.dialog_comment, null)

            builder.setView(view)
                builder.setTitle("Comment")
                builder.setPositiveButton("ok", this)
                builder.setNegativeButton("cancel", this)
                ret = builder.create()

        } else if (dialogId == SETPROFILE_DIALOG) {
            val view: View = requireActivity().layoutInflater.inflate(R.layout.dialog_changepicture, null)

            builder.setView(view)
            builder.setTitle("Pick Profile Picture")
            ret = builder.create()

            val btnCamera: TextView = view.findViewById(R.id.btnCamera)
            val btnGallery: TextView = view.findViewById(R.id.btnGallery)

            btnCamera.setOnClickListener {
                val intent = Intent(context, CameraActivity::class.java)
                startActivity(intent)
            }
            btnGallery.setOnClickListener{
                val intent = Intent(context, CameraActivity::class.java)
                startActivity(intent)
            }
        }
        return ret
    }

    override fun onClick(dialog: DialogInterface, item: Int) {
        if (item == DialogInterface.BUTTON_POSITIVE) {
            Toast.makeText(activity, "ok clicked", Toast.LENGTH_LONG).show()

        } else if (item == DialogInterface.BUTTON_NEGATIVE) {
            Toast.makeText(activity, "cancel clicked", Toast.LENGTH_LONG).show()
        }
    }
    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        //#4
        Toast.makeText(activity, "ok clicked", Toast.LENGTH_LONG).show()
    }

    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        //6
        Toast.makeText(activity, "ok clicked", Toast.LENGTH_LONG).show()
    }

}