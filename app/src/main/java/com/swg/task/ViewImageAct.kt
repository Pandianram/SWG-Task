package com.swg.task

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.swg.task.uploadProcess.ImageUploadProcess
import uiHelpher.OnSnackBar
import uiHelpher.OnUploadPercentage
import uiHelpher.TouchImageView

class ViewImageAct : AppCompatActivity(), View.OnClickListener, OnUploadPercentage {

    var TAG: String? = "ViewImageAct"
    var imgPath: String? = null
    private var tiv_image: TouchImageView? = null
    private var pb_loading: ProgressBar? = null
    private var tv_percentage: TextView? = null
    private var tv_upload_image: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_view_image)

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
        } else if (view.id == R.id.ll_upload) {

            tv_upload_image!!.visibility = View.GONE
            pb_loading!!.visibility = View.VISIBLE
            tv_percentage!!.visibility = View.VISIBLE

            tv_percentage?.text = "0 %"

            tiv_image?.let { imgPath?.let { it1 -> ImageUploadProcess(this, it, it1, this) } }
        }
    }

    override fun onProgress(percentage: Int,imgUrl: String,opt: String) {
        Log.e(TAG,"percentage======"+percentage);
        if (opt == "PROCESS") {
            tv_percentage?.text = "$percentage %"

            pb_loading!!.progress = percentage

            Log.e(TAG,"percentage==rttytytyt===="+percentage);

            tv_upload_image!!.visibility = View.GONE
            pb_loading!!.visibility = View.VISIBLE
            tv_percentage!!.visibility = View.VISIBLE
        } else if (opt == "NETWORK_OR_SERVER_ERROR" || opt == "SERVER_ERROR") {

            tv_upload_image!!.visibility = View.VISIBLE
            pb_loading!!.visibility = View.GONE
            tv_percentage!!.visibility = View.GONE

            OnSnackBar(this, tv_upload_image, "Please Re-upload this Image")

        } else {
            tv_percentage?.text = "Uploaded Successfully"

            pb_loading!!.progress = percentage

            Log.e(TAG,"percentage==rttytytyt=34567890==="+percentage);

            tv_upload_image!!.visibility = View.GONE
            pb_loading!!.visibility = View.GONE
            tv_percentage!!.visibility = View.VISIBLE

            Handler().postDelayed( {
                val i = Intent(this, UploadedImageAct::class.java)
                i.putExtra("imgPath", imgUrl)
                startActivity(i)

                this.finish()

            }, 300)
        }

    }
}