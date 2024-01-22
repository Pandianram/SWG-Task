package com.swg.task

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import uiHelpher.TouchImageView

class UploadedImageAct : AppCompatActivity(), View.OnClickListener {

    var TAG: String? = "ViewImageAct"
    var imgPath: String? = null
    private var tiv_image: TouchImageView? = null
    private var pb_loading: ProgressBar? = null
    private var tv_percentage: TextView? = null
    private var tv_upload_image: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_uploaded_image)

        tiv_image = findViewById(R.id.tiv_image)
        pb_loading = findViewById(R.id.pb_loading)
        tv_percentage = findViewById(R.id.tv_percentage)
        tv_upload_image = findViewById(R.id.tv_upload_image)

        findViewById<View>(R.id.iv_back).setOnClickListener(this)
        findViewById<View>(R.id.ll_upload).setOnClickListener(this)

        imgPath = intent.getStringExtra("imgPath")

        doImgView()
    }

    private fun doImgView() {
        tiv_image?.let {
            Glide.with(this).load(imgPath).placeholder(R.drawable.ph_loading_small).into(it)
        }
    }

    override fun onClick(view: View) {
        if (view.id == R.id.iv_back) {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}