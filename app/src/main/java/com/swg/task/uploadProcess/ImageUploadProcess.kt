package com.swg.task.uploadProcess

import android.app.Activity
import android.net.Uri
import uiHelpher.OnUploadPercentage
import okhttp3.MultipartBody
import android.os.Build
import android.util.Log
import android.view.View
import cameraImage.SaveBitmap
import okhttp3.RequestBody
import uiHelpher.CountingFileRequestBody
import com.swg.task.response.ImageUploadResponse
import com.swg.task.retrofit.ApiClient
import cameraImage.GlobalData
import com.swg.task.retrofit.ApiInterface
import okhttp3.MediaType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uiHelpher.OnSnackBar
import java.io.File
import java.util.*

class ImageUploadProcess(private val mActivity: Activity,
    private val mView: View, private val profileImage: String,
    var onUploadPercentage: OnUploadPercentage) {

    private val TAG = ImageUploadProcess::class.java.simpleName

    // Pass Parameter Array
    var headerMap: MutableMap<String, String> = HashMap()
    var builder = MultipartBody.Builder()

    init {
        doUpload()
    }

    private fun doUpload() {

        headerMap["Accesskey"] = "679371055206025694186470289166"
        builder.setType(MultipartBody.FORM)

        val file_one: File = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val uri = Uri.parse(profileImage)
            File(Objects.requireNonNull(uri.path))
        } else {
            File(SaveBitmap.getRealPathFromURI(mActivity, profileImage))
        }

        builder.addFormDataPart("userimage", file_one.name,
            RequestBody.create(MediaType.parse("multipart/form-data"), file_one)
        )

        val requestBody = builder.build()
        val requestBody1 =
            CountingFileRequestBody(requestBody, "files") { key, num ->
                mActivity.runOnUiThread {
                    onUploadPercentage.onProgress(num, "", "PROCESS")
                }
            }
        val doLoginReg = ApiClient.getClient(GlobalData.BASE_URL).create(ApiInterface::class.java)
            .doUploadImages(headerMap, requestBody1)
        doLoginReg?.enqueue(object : Callback<ImageUploadResponse?> {
            override fun onResponse(call: Call<ImageUploadResponse?>,
                response: Response<ImageUploadResponse?>) {
                if (response.code() == 200) {
                    assert(response.body() != null)
                    if (response.body()!!.status != null && response.body()!!.status == "true") {
                        onUploadPercentage.onProgress(100, response.body()!!.user.userImage, "DONE")
                    }
                } else {
                    OnSnackBar(mActivity, mView, "Server Error")
                    onUploadPercentage.onProgress(0, "", "SERVER_ERROR")
                }
            }

            override fun onFailure(call: Call<ImageUploadResponse?>, t: Throwable) {
                OnSnackBar(mActivity, mView, "Network or Server Error")
                Log.e(TAG, "Throwable " + t.message)
                onUploadPercentage.onProgress(0, "", "NETWORK_OR_SERVER_ERROR")
            }
        })
    }
}