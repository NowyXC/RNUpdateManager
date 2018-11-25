package com.nowy.RNUpdateManager;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nowy.RNUpdateManagerLib.OnUpdateListener;
import com.nowy.RNUpdateManagerLib.RNUpdateManager;
import com.nowy.RNUpdateManagerLib.UpdateInfo;
import com.nowy.RNUpdateManagerLib.utils.FileUtil;
import com.nowy.RNUpdateManagerLib.zip.ZipUtil;

import java.io.File;

public class ZipSampleAty extends AppCompatActivity {
    private static final int REQ_CODE_PERMISSION = 13;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_zip_sample);


        final String path = FileUtil.getCacheDir(this)+"/version0.2.zip";

        TextView tvPath = findViewById(R.id.zip_sample_TvPath);
        tvPath.setText(path);
        findViewById(R.id.zip_sample_BtnUnZip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XPermissionUtils.requestPermissions(
                        ZipSampleAty.this,
                        REQ_CODE_PERMISSION, new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                        }, new XPermissionUtils.OnPermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                RNUpdateManager.update(ZipSampleAty.this, new UpdateInfo.Builder(ZipSampleAty.this)
                                                .downUrl("https://github.com/NowyXC/RNUpdateManager/raw/master/raw/version0.3.zip")
                                                .isPat()
                                                .build(),
                                        new OnUpdateListener() {
                                            @Override
                                            public void onError(int code, String msg) {
                                                Log.e("test","错误信息:"+msg);
                                            }

                                            @Override
                                            public void onDeCompressFinish(String outPath) {

                                            }

                                            @Override
                                            public void onMergeFinish(String bundlePath) {

                                            }

                                            @Override
                                            public void onFinish(String bundlePath) {
                                                Log.e("test","完成:"+bundlePath);
                                            }
                                        });
                            }

                            @Override
                            public void onPermissionDenied(String[] deniedPermissions, boolean alwaysDenied) {

                            }
                        });


            }
        });


        TextView tvInfo = findViewById(R.id.zip_sample_TvInfo);
        UpdateInfo updateInfo = new UpdateInfo.Builder(this)
                .downUrl("https://github.com/NowyXC/RNUpdateManager/raw/master/raw/version0.2.zip")

                .build();
        tvInfo.setText(updateInfo.toString());

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        XPermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
