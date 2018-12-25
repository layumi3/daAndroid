package com.kulawu.lilisya.myasycn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SmsReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvSmsFrom, tvSmsMessage;
    Button btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);

        setTitle("Incomming Message");

        tvSmsFrom = findViewById(R.id.tv_no);
        tvSmsMessage = findViewById(R.id.tv_message);
        btnClose = findViewById(R.id.btn_close);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() ==R.id.btn_close){
            finish();
        }
    }
}
