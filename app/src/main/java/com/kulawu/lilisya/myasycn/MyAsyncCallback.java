package com.kulawu.lilisya.myasycn;

public interface MyAsyncCallback {
    void onPreExecute();
    void onPostExecute(String text);
}
