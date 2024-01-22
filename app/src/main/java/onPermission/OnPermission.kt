package onPermission

import androidx.activity.result.ActivityResultLauncher
import android.os.Build
import android.Manifest.permission
import android.app.Activity
import androidx.core.content.ContextCompat
import android.content.pm.PackageManager

class OnPermission {

    private val TAG = OnPermission::class.java.simpleName

    constructor()

    constructor(multiplePermissionLauncher: ActivityResultLauncher<Array<String?>?>, opt: String?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            multiplePermissionLauncher.launch(arrayOf(permission.READ_MEDIA_IMAGES,
                permission.READ_MEDIA_AUDIO, permission.READ_MEDIA_VIDEO, permission.CAMERA))
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            multiplePermissionLauncher.launch(arrayOf(permission.WRITE_EXTERNAL_STORAGE,
                permission.READ_EXTERNAL_STORAGE, permission.CAMERA))
        } else {
            multiplePermissionLauncher.launch(arrayOf(permission.WRITE_EXTERNAL_STORAGE,
                permission.READ_EXTERNAL_STORAGE, permission.CAMERA))
        }
    }

    fun checkBool(mActivity: Activity?, opt: String?): Boolean {
        val currentAPIVersion = Build.VERSION.SDK_INT
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            currentAPIVersion <= Build.VERSION_CODES.TIRAMISU &&
                    ContextCompat.checkSelfPermission(mActivity!!, permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mActivity, permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mActivity, permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mActivity, permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED
        else
            currentAPIVersion < Build.VERSION_CODES.M ||
                    ContextCompat.checkSelfPermission(mActivity!!, permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mActivity, permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(mActivity, permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }
}