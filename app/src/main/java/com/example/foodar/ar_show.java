package com.example.foodar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class ar_show extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar_show);
        Toast t=Toast.makeText(getApplicationContext(),getIntent().getStringExtra("title"),Toast.LENGTH_SHORT);
        t.show();
    }
}