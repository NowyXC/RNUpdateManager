package com.nowy.RNUpdateManager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.nowy.RNUpdateManagerLib.net.download.DownloadManager;
import com.nowy.RNUpdateManagerLib.utils.FileUtil;

import java.io.File;

public class DownloadSampleAty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_download_sample);

//        https://github.com/NowyXC/RNUpdateManager/raw/master/raw/version0.2.zip


        final ProgressBar progressBar = findViewById(R.id.download_sample_ProgressBar);

//        DownloadManager.getInstance().setProgressListener(new DownloadManager.ProgressListener() {
//            @Override
//            public void progressChanged(long read, long contentLength, boolean done) {
//                final int progress = (int) (100 * read / contentLength);
//                progressBar.setProgress(progress);
//            }
//        });

        findViewById(R.id.download_sample_BtnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DownloadManager.getInstance()
//                        .start("http://imtt.dd.qq.com/16891/89E1C87A75EB3E1221F2CDE47A60824A.apk",
//                                new File(FileUtil.getCacheDir(DownloadSampleAty.this),"test.apk").getAbsolutePath());
            }
        });
        findViewById(R.id.download_sample_BtnPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        findViewById(R.id.download_sample_BtnReStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }
}
