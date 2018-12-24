package com.kulawu.lilisya.myasycn;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements MyAsyncCallback{


    TextView tvStatus, tvDesc;

    final static String INPUT_STRING = "Halo ini demo asynctask yaaaa!!!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvStatus = findViewById(R.id.tv_status);
        tvDesc = findViewById(R.id.tv_desc);

        DemoAsync demoAsync = new DemoAsync(this);
        demoAsync.execute(INPUT_STRING);

    }

    private static class DemoAsync extends AsyncTask<String, Void, String>{
        static final String LOG_ASYNC = "DemoAsync";
        WeakReference<MyAsyncCallback> myListener;
        DemoAsync(MyAsyncCallback myListener){
            this.myListener = new WeakReference<>(myListener);
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d(LOG_ASYNC, "status: doInBackground: ");
            String output = null;
            try {
                String input = params[0];
                output = input + "Selamat Belajar ya Lilis!!";
                Thread.sleep(5000);
            }catch (Exception e){
                Log.d(LOG_ASYNC, e.getMessage());
            }
            return output;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d(LOG_ASYNC, "onPostExecute: status");
            MyAsyncCallback myListener = this.myListener.get();
            if (myListener != null){
                myListener.onPostExecute(result);
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(LOG_ASYNC, "onPreExecute: status");
            MyAsyncCallback myListener = this.myListener.get();
            if (myListener != null){
                myListener.onPreExecute();
            }
        }
    }

    @Override
    public void onPreExecute() {
        tvStatus.setText(R.string.status);
        tvDesc.setText(INPUT_STRING);
    }

    @Override
    public void onPostExecute(String result) {
        tvStatus.setText(R.string.status);
        if (result != null){
            tvDesc.setText(result);
        }
    }
}
