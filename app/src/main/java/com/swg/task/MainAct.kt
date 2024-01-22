package com.swg.task

import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
import android.os.Bundle
import cameraImage.OnPicker
import android.content.Intent
import cameraImage.ImgSelectAct
import android.os.Build
import android.os.Build.VERSION_CODES
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import cameraImage.SaveBitmap
import uiHelpher.OnSnackBar
import java.io.File
import java.lang.Exception
import java.lang.NullPointerException
import java.lang.NumberFormatException

class MainAct : AppCompatActivity(), View.OnClickListener {

    private val TAG = MainAct::class.java.simpleName
    var mActivity: Activity? = null

    private var lastBackPressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_main)

        mActivity = this

        findViewById<View>(R.id.ll_camera_gallery).setOnClickListener(this)

    }

    override fun onClick(view: View) {
        OnPicker(mActivity, someActivityResultLauncher)
    }

    private var someActivityResultLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()) {
            result -> if (result?.resultCode == RESULT_OK) {
        val data = result.data
        try {
            assert(data != null)
            val filePath = data!!.getStringExtra(ImgSelectAct.RESULT_FILE_PATH)
            var imageUri = Uri.parse("")
            imageUri = if (Build.VERSION.SDK_INT >= VERSION_CODES.M) {
                Uri.fromFile(File(filePath))
            } else {
                val selectedImage = BitmapFactory.decodeFile(filePath)
                Uri.fromFile(SaveBitmap.save(selectedImage))
            }
            val i = Intent(mActivity, ViewImageAct::class.java)
            i.putExtra("imgPath", imageUri.toString())
            mActivity!!.startActivity(i)

        } catch (e: NumberFormatException) {
            Log.e(TAG, "NumberFormatException " + e.message)
        } catch (e: NullPointerException) {
            Log.e(TAG, "NumberFormatException " + e.message)
        } catch (e: Exception) {
            Log.e(TAG, "Exception " + e.message)
        }
    }
    }

    override fun onBackPressed() {
        if (lastBackPressTime < System.currentTimeMillis() - 4000) {
            OnSnackBar(this, findViewById(R.id.ll_camera_gallery), "Press back again to close this app")
            lastBackPressTime = System.currentTimeMillis()
        } else {
            super.onBackPressed()
            finishAffinity()
        }
    }
}