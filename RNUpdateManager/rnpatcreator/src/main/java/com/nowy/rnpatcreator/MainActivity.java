package com.nowy.rnpatcreator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.nowy.RNUpdateManagerLib.diffPatch.PatchUtil;
import com.nowy.RNUpdateManagerLib.utils.AssetsUtil;
import com.nowy.RNUpdateManagerLib.utils.FileUtil;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etPath = findViewById(R.id.main_EtText);

        findViewById(R.id.main_BtnCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etPath.setText("pat文件生成中...");
                String originStr = AssetsUtil.getFromAssets(MainActivity.this,"v1.bundle");
                String newStr = AssetsUtil.getFromAssets(MainActivity.this,"v3.bundle");

                String patStr = PatchUtil.creatPatStr(originStr,newStr);

                File dir = new File(FileUtil.getRootPath(MainActivity.this,"JSBundle"));
                if(!dir.exists()){
                    dir.mkdirs();
                }
                File file = new File(dir,"RNUpdate.pat");
                FileUtil.writeFile(file,patStr);

                etPath.setText("保存地址："+file.getAbsolutePath());

            }
        });

    }
}
