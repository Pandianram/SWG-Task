package cameraImage;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import cameraImage.imageCompression.ImageCompressionListener;
import cameraImage.imagePicker.ImagePicker;
import onPermission.OnPermission;

public class ImgSelectAct extends AppCompatActivity {

    private String TAG = ImgSelectAct.class.getSimpleName();
    private static final int EXTERNAL_PERMISSION_CODE = 1234;
    private ImagePicker imagePicker;
    private boolean isCompress = true, isCamera = true, isGallery = true, isCameraYesNo = true;
    public static final String FLAG_COMPRESS = "flag_compress";
    public static final String FLAG_CAMERA = "flag_camera";
    public static final String FLAG_GALLERY = "flag_gallery";
    public static final String FLAG_CAMERA_YES_NO = "FLAG_CAMERA_YES_NO";
    public static final String RESULT_FILE_PATH = "result_file_path";

    private ActivityResultContracts.RequestMultiplePermissions multiplePermissionsContract;
    private ActivityResultLauncher<String[]> multiplePermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(com.swg.task.R.layout.act_image_capture);

        if (getIntent() != null) {
            isCompress = getIntent().getBooleanExtra(FLAG_COMPRESS, true);
            isCamera = getIntent().getBooleanExtra(FLAG_CAMERA, true);
            isGallery = getIntent().getBooleanExtra(FLAG_GALLERY, true);
            isCameraYesNo = getIntent().getBooleanExtra(FLAG_CAMERA_YES_NO, true);
        }

        imagePicker = new ImagePicker();

        multiplePermissionsContract = new ActivityResultContracts.RequestMultiplePermissions();
        multiplePermissionLauncher = registerForActivityResult(multiplePermissionsContract, isGranted -> {
            if (isGranted.containsValue(true) && new OnPermission().checkBool(this, "STORAGE")) {
                imagePicker.withActivity(this).chooseFromGallery(isGallery).chooseFromCamera(isCamera, isCameraYesNo, false)
                        .withCompression(isCompress).start();
            } else {
                Toast.makeText(this, "Permission declined", Toast.LENGTH_SHORT).show();
            }
        });

        if (new OnPermission().checkBool(this, "STORAGE")) {
            imagePicker.withActivity(this).chooseFromGallery(isGallery).chooseFromCamera(isCamera, isCameraYesNo, false)
                    .withCompression(isCompress).start();
        } else {
            new OnPermission(multiplePermissionLauncher, "STORAGE");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == EXTERNAL_PERMISSION_CODE) {
            if (grantResults.length == 2 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                imagePicker.withActivity(this).chooseFromGallery(isGallery).chooseFromCamera(isCamera, isCameraYesNo, false)
                        .withCompression(isCompress).start();
            } else {
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagePicker.SELECT_IMAGE) {
            if (resultCode == RESULT_OK) {
                imagePicker.addOnCompressListener(new ImageCompressionListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onCompressed(String filePath) {
                        if (filePath != null && isCompress) {
                            // return filepath
                            Intent intent = new Intent();
                            intent.putExtra(RESULT_FILE_PATH, filePath);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }
                });
                String filePath = imagePicker.getImageFilePath(data);
                if (filePath != null && !isCompress) {
                    Intent intent = new Intent();
                    intent.putExtra(RESULT_FILE_PATH, filePath);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            } else {
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    }

}