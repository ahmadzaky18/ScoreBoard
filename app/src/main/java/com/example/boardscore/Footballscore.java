package com.example.boardscore;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class Footballscore extends Application {
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
