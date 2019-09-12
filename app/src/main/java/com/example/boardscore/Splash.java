package com.example.boardscore;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {
    private static int Splash_time_out = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent LoginIntent = new Intent(Splash.this, Login.class);
                startActivity(LoginIntent);
                finish();
            }
        }, Splash_time_out);
    }
}

