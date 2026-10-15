package com.sysupd;

import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SuccessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_success);
        new Handler().postDelayed(new Runnable() {
            public void run() { finishAffinity(); }
        }, 4000);
    }
}
