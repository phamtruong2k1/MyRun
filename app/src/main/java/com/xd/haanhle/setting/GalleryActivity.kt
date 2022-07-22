package com.xd.haanhle.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xd.haanhle.R

class GalleryActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userprofile)


//        onRequestPermissionsResult() {requestCode: Int, permissions: String, grantResults: Int ->
//            super.onRequestPermissionsResult(requestCode, permissions,grantResults)
//
//            if (requestCode == REQUEST_CODE_STORAGE_PERMISSION && grantResults > 0){
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    selectImage();
//                }else {
//                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }

//    fun openCamera() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, tempImgUri)
//        cameraResult.launch(intent)
////
//        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        activityResultLauncher.launch(intent)
//
//
    }

}