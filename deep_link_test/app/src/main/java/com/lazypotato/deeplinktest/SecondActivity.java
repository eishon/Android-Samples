package com.lazypotato.deeplinktest;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Uri uri = getIntent().getData();
        if(uri != null) {
            String path = uri.toString();

            Toast.makeText(SecondActivity.this, "Path: " + path, Toast.LENGTH_SHORT).show();
        }
    }
}