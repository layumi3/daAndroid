package com.kulawu.lilisya.myasycn;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.Permission;

public class MainBroadcastActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDownload, btnCheckPermission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_broadcast);
        btnCheckPermission = findViewById(R.id.btn_permission);
        btnCheckPermission.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_permission){
            PermissionManager.check(this, Manifest.permission.RECEIVE_SMS,SMS_REQUEST_CODE);
        }
    }

    final int SMS_REQUEST_CODE = 101;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SMS_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"SMS receiver permission diterima", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"SMS receiver permission ditolak", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
