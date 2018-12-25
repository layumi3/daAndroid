package com.kulawu.lilisya.myasycn;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainService extends AppCompatActivity implements View.OnClickListener {

    Button btnStartServices, btnStartIntentServices, btnStartBoundService, btnStopBoundServices;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);

        btnStartServices = findViewById(R.id.btn_start_service);
        btnStartServices.setOnClickListener(this);

        btnStartIntentServices = findViewById(R.id.btn_start_intent_service);
        btnStartIntentServices.setOnClickListener(this);

        btnStartBoundService = findViewById(R.id.btn_start_bound_service);
        btnStartBoundService.setOnClickListener(this);

        btnStopBoundServices = findViewById(R.id.btn_stop_bound_service);
        btnStopBoundServices.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_service:
                Intent mStartServiceIntent = new Intent(MainService.this, OriginService.class);
                startService(mStartServiceIntent);
                break;

                case R.id.btn_start_intent_service:
                    Intent mStartIntentService = new Intent(MainService.this, DicodingIntentService.class);
                    mStartIntentService.putExtra(DicodingIntentService.EXTRA_DURATION,5000);
                    startService(mStartIntentService);
                break;

                 case R.id.btn_start_bound_service:
                     Intent mBoundServiceIntent = new Intent(MainService.this, BoundService.class);
                     bindService(mBoundServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
                break;
                 case R.id.btn_stop_bound_service:
                     unbindService(mServiceConnection);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mServiceBound){
            unbindService(mServiceConnection);
        }
    }
    boolean mServiceBound = false;
    BoundService mBoundService;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyBinder myBinder = (BoundService.MyBinder) service;
            mBoundService = myBinder.getService();
            mServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }
    };


}
