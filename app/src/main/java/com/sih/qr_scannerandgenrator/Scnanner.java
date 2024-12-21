package com.sih.qr_scannerandgenrator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scnanner extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scnanner);

        TextView resultText = findViewById(R.id.resultText);


        IntentIntegrator scanner = new IntentIntegrator(this);
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        scanner.setPrompt("Scanning QR Code...");
        scanner.setCameraId(0); // Use the back camera
        scanner.setBeepEnabled(true);
        scanner.setBarcodeImageEnabled(true);
        scanner.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        TextView resultText = findViewById(R.id.resultText);

        if (result != null) {
            if (result.getContents() != null) {
                resultText.setText("Scanned Result: " + result.getContents());
            } else {
                Toast.makeText(this, "No QR code found", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
