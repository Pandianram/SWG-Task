package com.swg.task.adpt

import android.app.Activity
import com.swg.task.response.ImageResponse
import androidx.activity.result.ActivityResultLauncher
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.swg.task.adpt.ListImageAdapter.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isInvisible
import com.swg.task.R
import com.bumptech.glide.Glide
import cameraImage.OnPicker
import com.swg.task.ViewImageAct
import java.util.ArrayList

class ListImageAdapter(var mActivity: Activity, listImages: List<ImageResponse>,
    someActivityResultLauncher: ActivityResultLauncher<Intent>)
    : RecyclerView.Adapter<MyViewHolder>() {

    var listImages: List<ImageResponse> = ArrayList()
    var someActivityResultLauncher: ActivityResultLauncher<Intent>

    init {
        this.listImages = listImages
        this.someActivityResultLauncher = someActivityResultLauncher
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val mView = LayoutInflater.from(mActivity).inflate(R.layout.adpt_image, parent, false)
        return MyViewHolder(mView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (listImages[position].imageOption == "UPLOAD_IMAGE") {
            Glide.with(mActivity).load(R.drawable.ic_upload_img)
                .placeholder(R.drawable.ph_loading_small).into(holder.iv_camera_gallery)
            holder.tv_upload_image.visibility = View.VISIBLE
        } else {
            Glide.with(mActivity).load(listImages[position].imagePath)
                .centerCrop().placeholder(R.drawable.ph_loading_small).into(holder.iv_camera_gallery)
            holder.tv_upload_image.visibility = View.GONE
        }
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemCount(): Int {
        return listImages.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var iv_camera_gallery: ImageView
        var ll_camera_gallery: LinearLayout
        var tv_upload_image: TextView

        init {
            iv_camera_gallery = itemView.findViewById(R.id.iv_camera_gallery)
            ll_camera_gallery = itemView.findViewById(R.id.ll_camera_gallery)
            tv_upload_image = itemView.findViewById(R.id.tv_upload_image)

            ll_camera_gallery.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (view.id == R.id.ll_camera_gallery) {
                if (listImages[adapterPosition].imageOption == "UPLOAD_IMAGE") {
                    OnPicker(mActivity, someActivityResultLauncher)
                } else {
                    val i = Intent(mActivity, ViewImageAct::class.java)
                    i.putExtra("imgPath", listImages[adapterPosition].imagePath)
                    mActivity.startActivity(i)
                }
            }
        }
    }
}