package com.sih.qr_scannerandgenrator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputText = findViewById(R.id.inputText);
        Button generateButton = findViewById(R.id.generateButton);
        ImageView qrCodeImage = findViewById(R.id.qrCodeImage);
        Button scanButton = findViewById(R.id.scanButton);

        // Generate QR Code
        generateButton.setOnClickListener(v -> {
            String text = inputText.getText().toString();
            if (!text.isEmpty()) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(
                            text, BarcodeFormat.QR_CODE, 300, 300
                    );
                    qrCodeImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    Toast.makeText(this, "Error generating QR code", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

        // Open Scanner Activity
        scanButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Scnanner.class);
            startActivity(intent);
        });
    }
}
