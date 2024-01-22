package cameraImage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.swg.task.R;

public class OnPicker implements GlobalData, View.OnClickListener {

    private String TAG = OnPicker.class.getSimpleName();

    private Activity mActivity;
    TextView tv_header,tv_camera,tv_gallery,tv_cancel;
    BottomSheetDialog dialog;
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    // Dashboard
    public OnPicker(Activity mActivity, ActivityResultLauncher<Intent> someActivityResultLauncher) {
        this.mActivity = mActivity;
        this.someActivityResultLauncher = someActivityResultLauncher;

        onDlg();
    }

    protected void onDlg() {

        dialog = new BottomSheetDialog(mActivity);
        LayoutInflater inflater = mActivity.getLayoutInflater();
        View convertView = inflater.inflate(R.layout.dlg_pic_opt, null);

        tv_header = convertView.findViewById(R.id.tv_header);
        tv_camera = convertView.findViewById(R.id.tv_camera);
        tv_gallery = convertView.findViewById(R.id.tv_gallery);
        tv_cancel = convertView.findViewById(R.id.tv_cancel);

        tv_header.setText(TAG_SELECT_SOURCE_ENG);
        tv_camera.setText(TAG_CAMERA_ENG);
        tv_gallery.setText(TAG_GALLERY_ENG);
        tv_cancel.setText(TAG_CANCEL_ENG);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            convertView.findViewById(R.id.cv_bg).setBackground(mActivity.getResources().getDrawable(R.drawable.bg_cv_dlg));
        }else {
            convertView.findViewById(R.id.cv_bg).setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.bg_cv_dlg));
        }

        convertView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        convertView.findViewById(R.id.tv_cancel).setOnClickListener(this);
        convertView.findViewById(R.id.ll_camera).setOnClickListener(this);
        convertView.findViewById(R.id.ll_gallery).setOnClickListener(this);
        convertView.findViewById(R.id.ll_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, ImgSelectAct.class);
                intent.putExtra(ImgSelectAct.FLAG_COMPRESS, false);
                intent.putExtra(ImgSelectAct.FLAG_CAMERA, true);
                intent.putExtra(ImgSelectAct.FLAG_GALLERY, true);
                intent.putExtra(ImgSelectAct.FLAG_CAMERA_YES_NO, false);
                someActivityResultLauncher.launch(intent);
                dialog.dismiss();
            }
        });
        convertView.findViewById(R.id.ll_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, ImgSelectAct.class);
                intent.putExtra(ImgSelectAct.FLAG_COMPRESS, false);
                intent.putExtra(ImgSelectAct.FLAG_CAMERA, true);
                intent.putExtra(ImgSelectAct.FLAG_GALLERY, true);
                intent.putExtra(ImgSelectAct.FLAG_CAMERA_YES_NO, true);
                someActivityResultLauncher.launch(intent);
                dialog.dismiss();
            }
        });

        onShowDlg(dialog,convertView);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_cancel) {
            dialog.dismiss();
        }else if (view.getId() == R.id.ll_camera) {
            Intent intent = new Intent(mActivity, ImgSelectAct.class);
            intent.putExtra(ImgSelectAct.FLAG_COMPRESS, false);
            intent.putExtra(ImgSelectAct.FLAG_CAMERA, true);
            intent.putExtra(ImgSelectAct.FLAG_GALLERY, true);
            intent.putExtra(ImgSelectAct.FLAG_CAMERA_YES_NO, true);
            someActivityResultLauncher.launch(intent);
            dialog.dismiss();
        }else if (view.getId() == R.id.ll_gallery) {
            Intent intent = new Intent(mActivity, ImgSelectAct.class);
            intent.putExtra(ImgSelectAct.FLAG_COMPRESS, false);
            intent.putExtra(ImgSelectAct.FLAG_CAMERA, true);
            intent.putExtra(ImgSelectAct.FLAG_GALLERY, true);
            intent.putExtra(ImgSelectAct.FLAG_CAMERA_YES_NO, false);
            someActivityResultLauncher.launch(intent);
            dialog.dismiss();
        }
    }
    private void onShowDlg(BottomSheetDialog dialog,View convertView) {
        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        lp.copyFrom(window.getAttributes());

        Display display = mActivity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        lp.width = size.x;
        lp.height = size.y;
        // This makes the dialog take up the full width
        window.setAttributes(lp);
        dialog.setCancelable(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(convertView);
        dialog.setTitle("");
        dialog.getWindow().getAttributes().windowAnimations = R.style.BottomDialogsAnimation; //style id
        dialog.show();
    }

}
