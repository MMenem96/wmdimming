package com.eng.wmdim.wmdimming.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.eng.wmdim.wmdimming.R;
import com.eng.wmdim.wmdimming.home.HomeActivity;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                openHomeActivity();
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void openHomeActivity() {

        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
    }
}
